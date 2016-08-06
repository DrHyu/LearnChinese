package com.example.drhyu.learnchinese;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SelectGroupPractice extends ListActivity implements View.OnClickListener  {

    private ChDataSource datasource;
    private  ArrayAdapter<String> adapter;

    private ListView listView;

    private TableInfo lastSelected = null;
    private ArrayList<Integer> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_practice);

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
    }

    private void getHandles(){
        listView = (ListView) findViewById(android.R.id.list);
    }

    public void onClick(View v) {
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent i = new Intent();
        i.putExtra("tableInfo",
                (Serializable)listView.getAdapter().getItem((int) id));
        i.setClass(SelectGroupPractice.this, PracticeActivity.class);
        startActivity(i);
        this.finish();
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
