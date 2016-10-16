package com.example.drhyu.learnchinese.DBStuff;

/**
 * Created by Jaume on 18/10/2014.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.drhyu.learnchinese.MiscClasses.CharacterStatistics;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CS_DB = "ch_db";

    public static final String COLUMN_ID                = "_id";
    public static final String COLUMN_CHARACTER         = "chr";
    public static final String COLUMN_PINYIN            = "pinyin";
    public static final String COLUMN_ENGLISH           = "english";
    public static final String COLUMN_DATE_ADDED        = "date_added";
    public static final String COLUMN_TIMES_STUDIED     = "times_studied";
    public static final String COLUMN_TIMES_FAILED      = "times_failed";
    public static final String COLUMN_AVERAGE_TIME      = "average_time";
    public static final String COLUMN_LAST_TIME         = "last_time";
    public static final String COLUMN_LISTS_PRESENT     = "lists_present";

    public static final String DATABASE_NAME = "characters.db";
    public static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "";

    public static final String GET_TABLE_NAMES =
            "SELECT name FROM sqlite_master WHERE type='table';";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("");
        onCreate(db);
    }

    public String getCreateStringCharacterTable (String tableName)
    {
        return "create table " + tableName + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_CHARACTER + " text not null, "
                + COLUMN_PINYIN + " text, "
                + COLUMN_ENGLISH + " text, "
                + COLUMN_DATE_ADDED + " datetime not null );";
    }

    public String getDropString (String tableName)
    {
        return "DROP TABLE IF EXISTS " + tableName + ";";
    }

    public String getCreateStringCharacterHistoryTable (String tableName)
    {
        return "create table " + tableName + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_CHARACTER + " text not null unique, "
                + COLUMN_PINYIN + " text, "
                + COLUMN_ENGLISH + " text, "
                + COLUMN_DATE_ADDED + " datetime not null, "
                + COLUMN_TIMES_STUDIED + " integer not null, "
                + COLUMN_TIMES_FAILED + " integer not null, "
                + COLUMN_AVERAGE_TIME + " integer not null, "
                + COLUMN_LAST_TIME + " integer not null, "
                + COLUMN_LISTS_PRESENT + " text not null "
                + ");";
    }

    public String getDropStringCharacterHistory (String tableName)
    {
        return "DROP TABLE IF EXISTS " + tableName + ";";
    }

    public String getUpdateCharacterHistory (CharacterStatistics cs){

        return "insert or replace into " + TABLE_CS_DB
                + " ("
                + COLUMN_ID             + ", "
                + COLUMN_CHARACTER      + ", "
                + COLUMN_PINYIN         + ", "
                + COLUMN_ENGLISH        + ", "
                + COLUMN_DATE_ADDED     + ", "
                + COLUMN_TIMES_STUDIED  + ", "
                + COLUMN_TIMES_FAILED   + ", "
                + COLUMN_AVERAGE_TIME   + ", "
                + COLUMN_LAST_TIME      + ", "
                + COLUMN_LISTS_PRESENT
                +  ") values ("
                + "(select "+ COLUMN_ID + " from " + TABLE_CS_DB + " where " + COLUMN_CHARACTER + " = \"" + cs.character +"\"),"
                + "\""  + cs.character       + "\", "
                + "\""  + cs.pinyin          + "\", "
                + "\""  + cs.english         + "\", "
                        + cs.date_added      + ", "
                        + cs.times_studied   + ", "
                        + cs.times_failed    + ", "
                        + cs.average_time    + ", "
                        + cs.last_time       + ", "
                + "\""  + cs.lists_present   + "\")";

    }

}