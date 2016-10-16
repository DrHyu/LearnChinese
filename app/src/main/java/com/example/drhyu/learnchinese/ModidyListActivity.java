package com.example.drhyu.learnchinese;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.drhyu.learnchinese.DBStuff.ChDataSource;
import com.example.drhyu.learnchinese.DBStuff.TableInfo;
import com.example.drhyu.learnchinese.MiscClasses.Character;

import java.util.List;

public class ModidyListActivity extends ListActivity {
    private ChDataSource datasource;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    private TableInfo ti;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_char_layout);

        Intent i = getIntent();

        ti = (TableInfo) i.getSerializableExtra("tableInfo");

        datasource = new ChDataSource(this);
        datasource.open();

        List<Character> values;
        values = datasource.getAllCharacters(ti.getTableName());

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);


        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);

    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        ArrayAdapter<Character> adapter = (ArrayAdapter<Character>) getListAdapter();
        Character new_char = null;
        switch (view.getId()) {
            case R.id.add:
                String character = editText1.getText().toString();
                String pinyin    = editText2.getText().toString();
                String english   = editText3.getText().toString();

                // save the new new_char to the database
                new_char = datasource.createCharacter(ti.getTableName(),character,pinyin,english);

                adapter.add(new_char);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    new_char = (Character) getListAdapter().getItem(0);
                    datasource.deleteCharacter(ti.getTableName(), new_char);
                    adapter.remove(new_char);
                }
                break;
            case R.id.addHSK1:
                break;
            case R.id.addHSK2:
                break;
            case R.id.deleteAll:
                while (getListAdapter().getCount() > 0) {
                    new_char = (Character) getListAdapter().getItem(0);
                    datasource.deleteCharacter(ti.getTableName(), new_char);
                    adapter.remove(new_char);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}