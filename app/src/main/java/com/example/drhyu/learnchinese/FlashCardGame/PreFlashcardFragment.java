package com.example.drhyu.learnchinese.FlashCardGame;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.drhyu.learnchinese.DBStuff.ChDataSource;
import com.example.drhyu.learnchinese.DBStuff.TableInfo;
import com.example.drhyu.learnchinese.MiscClasses.Character;
import com.example.drhyu.learnchinese.MiscClasses.MyMultiSlider;
import com.example.drhyu.learnchinese.MultipleChoiceGame.MultipleChoiceActivity;
import com.example.drhyu.learnchinese.MultipleChoiceGame.MultipleChoiceSettings;
import com.example.drhyu.learnchinese.R;

import java.io.Serializable;
import java.util.List;

import io.apptik.widget.MultiSlider;


/**
 * Created by Jaume on 7/27/2016.
 */
public class PreFlashcardFragment extends Fragment {

    private ChDataSource datasource;
    private ListView listView;
    private MyMultiSlider multiSlider;


    private Button b1,b2,b3;
    private int currenltySlected = 0;

    List<TableInfo> tableInfos;
    List<Character> selectedTableValues;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        datasource = new ChDataSource(getActivity().getApplicationContext());

        // inflate the layout using the cloned inflater, not default inflater
        View v = inflater.inflate(R.layout.activity_pre_practice, container, false);
        listView            = (ListView) v.findViewById(android.R.id.list);

        multiSlider         = (MyMultiSlider) v.findViewById(R.id.range_slider);

        b1                  = (Button) v.findViewById(R.id.b1);
        b2                  = (Button) v.findViewById(R.id.b2);
        b3                  = (Button) v.findViewById(R.id.b3);

        getHandles();
        return v;
    }

    private void getHandles(){

        multiSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                } else {

                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("tableInfo",
                        (Serializable)listView.getAdapter().getItem((int) currenltySlected));
                i.setClass(getActivity(), FlashCardActivity.class);
                MultipleChoiceSettings ps = new MultipleChoiceSettings();
                ps.GAME_SPEED_FACTOR = 100;
                i.putExtra("settings",ps);
                i.putExtra("from", multiSlider.getThumb(0).getValue());
                i.putExtra("to", multiSlider.getThumb(1).getValue());
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("tableInfo",
                        (Serializable)listView.getAdapter().getItem((int) currenltySlected));
                i.setClass(getActivity(), FlashCardActivity.class);
                MultipleChoiceSettings ps = new MultipleChoiceSettings();
                ps.GAME_SPEED_FACTOR = 50;
                i.putExtra("settings",ps);
                i.putExtra("from", multiSlider.getThumb(0).getValue());
                i.putExtra("to", multiSlider.getThumb(1).getValue());
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("tableInfo",
                        (Serializable)listView.getAdapter().getItem((int) currenltySlected));
                MultipleChoiceSettings ps = new MultipleChoiceSettings();
                ps.GAME_SPEED_FACTOR = 25;
                i.putExtra("settings",ps);
                i.putExtra("from", multiSlider.getThumb(0).getValue());
                i.putExtra("to", multiSlider.getThumb(1).getValue());
                i.setClass(getActivity(), MultipleChoiceActivity.class);
                startActivity(i);
            }
        });
    }

    public static PreFlashcardFragment newInstance(){
        return new PreFlashcardFragment();
    }

    public PreFlashcardFragment(){
    }

    private void refreshList(){

        tableInfos = datasource.getAllTableNames();

        final List<TableInfo> val = tableInfos;

        listView.setAdapter(new  ArrayAdapter<TableInfo>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, tableInfos){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_prepractice_item, parent, false);
                }
                TextView table_name = (TextView) convertView.findViewById(R.id.table_name);
                TextView table_size = (TextView) convertView.findViewById(R.id.table_size);

                table_name.setText(val.get(position).getTableName());
                table_size.setText(Integer.toString(val.get(position).getTableSize()));

                return convertView;
            }


        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                updateSelectedTable(position);

            }
        });

        updateSelectedTable(currenltySlected);

        //                Intent i = new Intent();
//                i.putExtra("tableInfo",
//                        (Serializable)listView.getAdapter().getItem((int) id));
//                i.setClass(getActivity(), MultipleChoiceActivity.class);
//                startActivity(i);
    }

    private void updateSelectedTable(int pos){
        currenltySlected = pos;
        selectedTableValues = datasource.getAllCharacters(tableInfos.get(pos).getTableName());

        multiSlider.setMax(selectedTableValues.size()-1);
        multiSlider.setStepsThumbsApart(15);

        multiSlider.values = selectedTableValues;
        multiSlider.getThumb(0).setValue(0);

    }

    @Override
    public void onResume() {
        datasource.open();
        refreshList();
        super.onResume();
    }

    @Override
    public void onPause() {
        datasource.close();
        super.onPause();
    }

}

