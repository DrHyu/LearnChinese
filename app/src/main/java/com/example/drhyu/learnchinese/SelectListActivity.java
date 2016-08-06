package com.example.drhyu.learnchinese;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SelectListActivity extends ListActivity implements View.OnClickListener  {

    private ChDataSource datasource;
    private  ArrayAdapter<String> adapter;

    private ListView listView;
    private Button  bAdd, bDelete, bEdit;
    private EditText editText;

    private TableInfo lastSelected = null;
    private ArrayList<Integer> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group_edit);

        datasource = new ChDataSource(this);
        datasource.open();

        selectedItems = new ArrayList<Integer>();

        getHandles();
        refreshList();

    }

    private void refreshList(){

        List<TableInfo> values;

        values = datasource.getAllTableNames();
        ArrayAdapter<TableInfo> adapter = new ArrayAdapter<TableInfo>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        clearSelected();
    }
    private void getHandles(){

        listView = (ListView) findViewById(android.R.id.list);
        bAdd = (Button) findViewById(R.id.button1);
        bAdd.setOnClickListener(this);
        bDelete = (Button) findViewById(R.id.button3);
        bDelete.setOnClickListener(this);
        bEdit = (Button) findViewById(R.id.button2);
        bEdit.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText1);

    }

    public void onClick(View v) {
        //Switch-like arrangement
        Button btn = (Button) v;

        //Add Button
        if(btn.getId() == R.id.button1){
            String s = editText.getText().toString();
            if(!s.equals("")){
                datasource.createTable(s, ChDataSource.TableType.CHARACTER);
                refreshList();
            }
        }
        else if(btn.getId() == R.id.button2){
            if(selectedItems.size() == 1) {
                Intent i = new Intent();
                i.putExtra("tableInfo",
                        (Serializable)listView.getAdapter().getItem(selectedItems.get(0)));
                i.setClass(SelectListActivity.this, ModidyListActivity.class);
                startActivity(i);
            }
            else if(selectedItems.size() > 0){
                Toast.makeText(getApplicationContext(),
                        "Multiple lists selected !", Toast.LENGTH_SHORT).show();
            }
            else if(selectedItems.size() == 0){
                Toast.makeText(getApplicationContext(),
                        "No lists selected !", Toast.LENGTH_SHORT).show();
            }
        }
        //Drop table
        else if(btn.getId() == R.id.button3){
            if(selectedItems.size() > 0) {
                while(selectedItems.size() > 0) {
                    datasource.removeTable(
                            ((TableInfo) listView.getAdapter().
                                    getItem(selectedItems.get(0))).getTableName());
                    selectedItems.remove(0);
                }
                refreshList();
            }
            else if(selectedItems.size() == 0){
                Toast.makeText(getApplicationContext(),
                        "No lists selected !", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if(isSelected((int)id)){
            v.setBackgroundColor(Color.WHITE);
        }
        else{
            v.setBackgroundColor(Color.BLUE);
        }
        lastSelected = (TableInfo)l.getAdapter().getItem((int)id);
    }

    private boolean isSelected(int id){

        for (int i = 0; i < selectedItems.size(); i ++){
            if(selectedItems.get(i) == id){
                selectedItems.remove(i);
                return true;
            }
        }
        selectedItems.add(id);
        return false;
    }

    private void clearSelected(){

        for(int i = 0; i <listView.getCount(); i ++){
            listView.getAdapter().getView(i,null,null).setBackgroundColor(Color.WHITE);
        }
        selectedItems.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_group, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        datasource.open();
        refreshList();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
