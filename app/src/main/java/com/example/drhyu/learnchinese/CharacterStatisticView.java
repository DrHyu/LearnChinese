package com.example.drhyu.learnchinese;

/**
 * Created by Jaume on 6/25/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CharacterStatisticView extends Activity implements View.OnClickListener {

    private ChDataSource datasource;

    private ListView listView;
    private Button bAdd, bDelete, bEdit;

    private EditText editText;

    private CharacterStatistics lastSelected = null;
    private ArrayList<CharacterStatistics> selectedItems;

    private List<CharacterStatistics> data;

    public enum SortBy {HANZI,PINYIN,ENGLISH,FREQUENCY,BEST,WORST};
    public enum DataSelection {ALL,STUDIED_ONLY,FROM_LIST};

    Spinner sortSpinner;
    SortBy sortBy = SortBy.FREQUENCY;

    Spinner dataSelectionSpinner;
    DataSelection dataFilter = DataSelection.ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_character_history);

        datasource = new ChDataSource(this);
        getHandles();
    }

    private void refreshList(){

        if(dataFilter == DataSelection.ALL) {
            data = datasource.getAllCharacterStatistics();

        } else if (dataFilter == DataSelection.FROM_LIST){
            data = datasource.getCharacterStatisticsRegexp(" "+MySQLiteHelper.COLUMN_LISTS_PRESENT+" REGEXP '.*"+ "hsk1" +",.*'");

        } else if (dataFilter == DataSelection.STUDIED_ONLY){
            data = datasource.getCharacterStatisticsRegexp(" `"+MySQLiteHelper.COLUMN_TIMES_STUDIED+"` > 0");
        }

        Comparator cmp = null;

        if(sortBy == SortBy.HANZI){
            cmp = new Comparator<CharacterStatistics>() {
                @Override
                public int compare(CharacterStatistics lhs, CharacterStatistics rhs) {
                    return lhs.character.compareToIgnoreCase(rhs.character);
                }
            };
        } else if(sortBy == SortBy.PINYIN){
            cmp = new Comparator<CharacterStatistics>() {
                @Override
                public int compare(CharacterStatistics lhs, CharacterStatistics rhs) {
                    return lhs.pinyin.compareToIgnoreCase(rhs.pinyin);
                }
            };
        } else if(sortBy == SortBy.ENGLISH){
            cmp = new Comparator<CharacterStatistics>() {
                @Override
                public int compare(CharacterStatistics lhs, CharacterStatistics rhs) {
                    return lhs.english.compareToIgnoreCase(rhs.english);
                }
            };
        } else if(sortBy == SortBy.BEST){
            cmp = new Comparator<CharacterStatistics>() {
                @Override
                public int compare(CharacterStatistics lhs, CharacterStatistics rhs) {
                    double l,r;
                    l = (double)(lhs.times_studied+2-lhs.times_failed)/(double)(lhs.times_failed+2);
                    r = (double)(rhs.times_studied+2-rhs.times_failed)/(double)(rhs.times_failed+2);
                    return l > r ? -1 : (l == r ? 0 : 1);
                }
            };
        } else if(sortBy == SortBy.WORST){
            cmp = new Comparator<CharacterStatistics>() {
                @Override
                public int compare(CharacterStatistics lhs, CharacterStatistics rhs) {
                    double l,r;
                    l = (double)(lhs.times_studied+2-lhs.times_failed)/(double)(lhs.times_failed+2);
                    r = (double)(rhs.times_studied+2-rhs.times_failed)/(double)(rhs.times_failed+2);
                    return l < r ? -1 : (l == r ? 0 : 1);
                }
            };
        }


        //Sorted by frequency by default
        if(sortBy != SortBy.FREQUENCY){Collections.sort(data, cmp);}

        final List<CharacterStatistics> values = data;

        listView.setAdapter(new  ArrayAdapter<CharacterStatistics>(this, android.R.layout.simple_list_item_1, values){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_ch_item, parent, false);
                }
                TextView english = (TextView) convertView.findViewById(R.id.english);
                TextView hanzi = (TextView) convertView.findViewById(R.id.hanzi);
                TextView pinyin = (TextView) convertView.findViewById(R.id.pinyin);

                english.setText(values.get(position).english.toString());
                hanzi.setText(values.get(position).character.toString());
                pinyin.setText(values.get(position).pinyin.toString());

                int passed = (data.get(position).times_studied-data.get(position).times_failed);
                int failed = data.get(position).times_failed;

                if(failed > passed){
                    int t = (passed+2)*255/(failed+2);
                    convertView.setBackgroundColor(0xff000000 | 0x00ff0000 | t << 8 | t);
                } else {
                    int t = (((failed+2)*205/(passed+2)) +50 );
                    convertView.setBackgroundColor(0xff000000 | t << 16 | 0x0000ff00 | t);
                }

                return convertView;
            }


        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View temp = (View) view.findViewById(R.id.extra);
                if(temp.getVisibility() == View.VISIBLE){
                    temp.setVisibility(View.GONE);
                } else {
                    TextView tv1 = (TextView) view.findViewById(R.id.tv1);
                    TextView tv2 = (TextView) view.findViewById(R.id.tv2);
                    TextView tv3 = (TextView) view.findViewById(R.id.tv3);
                    TextView tv4 = (TextView) view.findViewById(R.id.tv4);
                    TextView tv5 = (TextView) view.findViewById(R.id.tv5);

                    tv1.setText("Times studied: " + data.get(position).times_studied);
                    tv2.setText("Times failed:  " + data.get(position).times_failed);
                    tv3.setText("Average time:  " + data.get(position).average_time);
                    tv4.setText("Last time:     " + data.get(position).id);
                    tv5.setText("Lists:     "     + data.get(position).lists_present.toUpperCase());

                    temp.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getHandles(){
        listView = (ListView) findViewById(android.R.id.list);

        sortSpinner = (Spinner) findViewById(R.id.spinner1);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Frequency");
        categories.add("Hanzi");
        categories.add("Pinyin");
        categories.add("English");
        categories.add("Best");
        categories.add("Worst");
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        sortSpinner.setAdapter(sortAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        sortBy = SortBy.FREQUENCY;
                        break;
                    case 1:
                        sortBy = SortBy.HANZI;
                        break;
                    case 2:
                        sortBy = SortBy.PINYIN;
                        break;
                    case 3:
                        sortBy = SortBy.ENGLISH;
                        break;
                    case 4:
                        sortBy = SortBy.BEST;
                        break;
                    case 5:
                        sortBy = SortBy.WORST;
                        break;
                    default:
                        break;
                }
                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dataSelectionSpinner = (Spinner) findViewById(R.id.spinner2);

        // Spinner Drop down elements
        final List<String> dataSelection = new ArrayList<String>();
        dataSelection.add("All");
        dataSelection.add("Studied-only");
        dataSelection.add("HSK1");

        ArrayAdapter<String> dataSelectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataSelection);

        dataSelectionSpinner.setAdapter(dataSelectionAdapter);

        dataSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        dataFilter = DataSelection.ALL;
                        break;
                    case 1:
                        dataFilter = DataSelection.STUDIED_ONLY;
                        break;
                    case 2:
                        dataFilter = DataSelection.FROM_LIST;
                        break;
                    default:
                        break;
                }
                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClick(View v) {

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