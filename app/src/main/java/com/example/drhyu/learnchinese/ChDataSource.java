package com.example.drhyu.learnchinese;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jaume on 18/10/2014.
 */
public class ChDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumnsCharacter = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CHARACTER,
            MySQLiteHelper.COLUMN_PINYIN,
            MySQLiteHelper.COLUMN_ENGLISH,
            MySQLiteHelper.COLUMN_DATE_ADDED};

    private String[] allColumnsCharacterHistory = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CHARACTER,
            MySQLiteHelper.COLUMN_PINYIN,
            MySQLiteHelper.COLUMN_ENGLISH,
            MySQLiteHelper.COLUMN_DATE_ADDED,
            MySQLiteHelper.COLUMN_TIMES_STUDIED,
            MySQLiteHelper.COLUMN_TIMES_FAILED,
            MySQLiteHelper.COLUMN_AVERAGE_TIME,
            MySQLiteHelper.COLUMN_LAST_TIME,
            MySQLiteHelper.COLUMN_LISTS_PRESENT};

    public enum TableType {CHARACTER,CHARACTER_HISTORY};

    public ChDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    ///////////////////////////////
    /*      GENERAL  METHODS     */
    ///////////////////////////////

    public void open() throws SQLException {
        //database = dbHelper.getWritableDatabase();

        String DB_PATH = "/data/data/com.example.drhyu.learnchinese/";
        SQLiteDatabase.CursorFactory t = new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                Log.d(this.toString(),query.toString());
                return new SQLiteCursor(db,masterQuery,editTable,query);
            }
        };
        database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + MySQLiteHelper.DATABASE_NAME,t);
    }

    public void close() {
        database.close();
        //dbHelper.close();
    }

    public void createTable (String tableName, TableType type){

        if(type == TableType.CHARACTER){
            database.execSQL(dbHelper.getCreateStringCharacterTable(tableName));
        }
        if(type == TableType.CHARACTER_HISTORY){
            database.execSQL(dbHelper.getCreateStringCharacterHistoryTable(tableName));
        }
    }

    public void removeTable (String tableName){

        database.execSQL(dbHelper.getDropString(tableName));
    }

    public List<TableInfo> getAllTableNames(){

        ArrayList<TableInfo> tables = new ArrayList<TableInfo>();
        Cursor c = database.rawQuery(MySQLiteHelper.GET_TABLE_NAMES, null);

        String s = "";
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                s = c.getString(c.getColumnIndex("name"));
                if( !s.equals("android_metadata") && !s.equals("sqlite_sequence") ) {
                    int numRows = (int)DatabaseUtils.longForQuery(database,
                            "SELECT COUNT(*) FROM " + s, null);
                    tables.add(new TableInfo(s, numRows));
                }
                c.moveToNext();
            }
        }

        return  tables;
    }

    ///////////////////////////////
    /*     CHARACTER  METHODS    */
    ///////////////////////////////

    public Character createCharacter(String tableName, String character, String pinyin, String english) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_CHARACTER, character);
        values.put(MySQLiteHelper.COLUMN_PINYIN, pinyin);
        values.put(MySQLiteHelper.COLUMN_ENGLISH, english);
        values.put(MySQLiteHelper.COLUMN_DATE_ADDED, System.currentTimeMillis());

        long insertId = database.insert(tableName, null,values);

        Cursor cursor = database.query(tableName,
                allColumnsCharacter, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Character newCharacter = cursorToCharacter(cursor);
        cursor.close();

        CharacterStatistics cs = getSingleCharacterStatistics(character);

        // If there is no previous history of this character
        if(cs == null){
            cs = new CharacterStatistics();
            cs.setCharacter(character);
            cs.setPinyin(pinyin);
            cs.setEnglish(english);
            cs.setDate_added(newCharacter.getDate_added());
            cs.setTimes_studied(0);
            cs.setTimes_failed(0);
            cs.setAverage_time(0);
            cs.setLast_time(0);
            cs.setLists_present(tableName+",");
            putCharacterStatistics(cs);
        // If there is history, but this table is not linked to that character
        } else if (!cs.getLists_present().matches(tableName+",")) {
            cs.setLists_present(cs.getLists_present()+tableName+",");
            putCharacterStatistics(cs);
        }
        // Else - Doesn't ned updating

        return newCharacter;
    }

    public void deleteCharacter(String tableName, Character character) {
        long id = character.getId();
        System.out.println("Character deleted with id: " + id);
        database.delete(tableName, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Character> getAllCharacters(String tableName) {
        List<Character> characters = new ArrayList<Character>();

        Cursor cursor = database.query(tableName,
                allColumnsCharacter, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Character character = cursorToCharacter(cursor);
            characters.add(character);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return characters;
    }

    private Character cursorToCharacter(Cursor cursor) {

        Character character = new Character();

        character.setId(cursor.getLong(0));
        character.setCharacter(cursor.getString(1));
        character.setPinyin(cursor.getString(2));
        character.setEnglish(cursor.getString(3));
        character.setDate_added(cursor.getLong(4));

        return character;
    }

    //////////////////////////////////
    /* CHARACTER STATISTICS METHODS */
    //////////////////////////////////

    public CharacterStatistics createCharacterStatistics(String character, String pinyin, String english) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_CHARACTER, character);
        values.put(MySQLiteHelper.COLUMN_PINYIN, pinyin);
        values.put(MySQLiteHelper.COLUMN_ENGLISH, english);
        values.put(MySQLiteHelper.COLUMN_DATE_ADDED, System.currentTimeMillis());
        values.put(MySQLiteHelper.COLUMN_TIMES_STUDIED, 0);
        values.put(MySQLiteHelper.COLUMN_TIMES_FAILED, 0);
        values.put(MySQLiteHelper.COLUMN_AVERAGE_TIME, 0);
        values.put(MySQLiteHelper.COLUMN_LAST_TIME, 0);
        values.put(MySQLiteHelper.COLUMN_LISTS_PRESENT, "HSK1");

        long insertId = database.insert(MySQLiteHelper.TABLE_CS_DB,null,values);

        Cursor cursor = database.rawQuery(String.format("SELECT * FROM %s where %s = %d", MySQLiteHelper.TABLE_CS_DB, MySQLiteHelper.COLUMN_ID,insertId) , null);
        CharacterStatistics newCharacter;
        if(cursor.getCount() >0) {
            cursor.moveToFirst();
            newCharacter = cursorToCharacterStatistics(cursor);
        }
        else {
            newCharacter = null;
        }

        cursor.close();
        return newCharacter;
    }

    public CharacterStatistics putCharacterStatistics(CharacterStatistics cs) {
        database.execSQL(dbHelper.getUpdateCharacterHistory(cs));
        return null;
    }

    public void deleteCharacterStatistics(String tableName, CharacterStatistics character) {
        String ch = character.getCharacter();
        System.out.println("Character deleted with ch: " + ch);
        database.delete(tableName, MySQLiteHelper.COLUMN_CHARACTER
                + " = '" + ch +"'", null);
    }

    public List<CharacterStatistics> getAllCharacterStatistics() {
        List<CharacterStatistics> characters = new ArrayList<CharacterStatistics>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CS_DB,
                allColumnsCharacterHistory, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CharacterStatistics character = cursorToCharacterStatistics(cursor);
            characters.add(character);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return characters;
    }

    public List<CharacterStatistics> getCharacterStatisticsRegexp(String regexp) {
        List<CharacterStatistics> characters = new ArrayList<CharacterStatistics>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CS_DB,
                allColumnsCharacterHistory, regexp, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CharacterStatistics character = cursorToCharacterStatistics(cursor);
            characters.add(character);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return characters;
    }

    public CharacterStatistics getSingleCharacterStatistics(String chr) {

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CS_DB,
                allColumnsCharacterHistory, MySQLiteHelper.COLUMN_CHARACTER + " = '" + chr +"'", null, null, null, null);

        CharacterStatistics character;

        // make sure to close the cursor

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            character = cursorToCharacterStatistics(cursor);
        }
        else {
            character = null;
        }
        cursor.close();
        return character;
    }

    private CharacterStatistics cursorToCharacterStatistics(Cursor cursor) {

        CharacterStatistics character = new CharacterStatistics();
        character.setId(cursor.getLong(0));
        character.setCharacter(cursor.getString(1));
        character.setPinyin(cursor.getString(2));
        character.setEnglish(cursor.getString(3));
        character.setDate_added(cursor.getLong(4));
        character.setTimes_studied(cursor.getInt(5));
        character.setTimes_failed(cursor.getInt(6));
        character.setAverage_time(cursor.getInt(7));
        character.setLast_time(cursor.getInt(8));
        character.setLists_present(cursor.getString(9));

        return character;
    }
}