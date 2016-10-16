/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.drhyu.learnchinese.FlashCardGame;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.drhyu.learnchinese.DBStuff.ChDataSource;
import com.example.drhyu.learnchinese.DBStuff.TableInfo;
import com.example.drhyu.learnchinese.MiscClasses.AutoResizeButton;
import com.example.drhyu.learnchinese.MiscClasses.AutoResizeTextView;
import com.example.drhyu.learnchinese.MiscClasses.Character;
import com.example.drhyu.learnchinese.MiscClasses.MPageTransformer;
import com.example.drhyu.learnchinese.MiscClasses.PracticeTimer;
import com.example.drhyu.learnchinese.MultipleChoiceGame.MultipleChoiceActivity;
import com.example.drhyu.learnchinese.MultipleChoiceGame.MultipleChoiceSettings;
import com.example.drhyu.learnchinese.R;
import com.example.drhyu.learnchinese.StartActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlashCardActivity extends FragmentActivity {

    private static final int NUM_PAGES = 5;


    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    private HashMap<Integer,FlashCardFragment> mPageReferenceMap;

    private ChDataSource datasource;
    private List<Character> data;

    private Boolean game_on = false;

    private int game_correct_answer = 0;
    private int correct_answer_position = 0;

    private final int NUMBER_OF_ANSWERS         = 7;

    private final long BASE_TIME             = 100_000;
    private final long CORRECT_ANSW_TIME     = 30000;

    private MultipleChoiceSettings ps;

    private static PracticeTimer pTimer;

    public enum GAME_SATE {GAME_ON,GAME_PAUSED,GAME_END};

    public MultipleChoiceActivity.GAME_SATE game_sate;

    private AutoResizeButton b1,b2,b3,b4,b5,b6,b7,b8;
    private Button sb,gc;

    private AutoResizeTextView textView1;
    private ProgressBar timerBar;

    private ArrayList<Integer> game_question_order = new ArrayList<Integer>();
    private ArrayList<Integer> button_options      = new ArrayList<Integer>();
    private ArrayList<String>  correctly_answered  = new ArrayList<String>();
    private ArrayList<String>  uncorrectly_answered  = new ArrayList<String>();

    private TableInfo ti;
    private int from =0;
    private int to = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_layout);

        Intent i = getIntent();
        ti = (TableInfo) i.getSerializableExtra("tableInfo");
        from = i.getIntExtra("from",0);
        to = i.getIntExtra("to",0);
        ps = (MultipleChoiceSettings) i.getSerializableExtra("settings");
        if( ps == null){ ps = new MultipleChoiceSettings();}

        setUpPager();
        game_initialize();

        Button b1 = (Button) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActiveFragment().flipCard();
            }
        });

    }

    private void game_initialize()
    {
        datasource = new ChDataSource(this);
        datasource.open();

        data = datasource.getAllCharacters(ti.getTableName());

        datasource.close();
    }


    private void setUpPager(){
        mPageReferenceMap = new HashMap<Integer, FlashCardFragment>();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });
        mPager.setPageTransformer(true, new MPageTransformer());
    }

    public FlashCardFragment getActiveFragment(){
        int index = mPager.getCurrentItem();
        return mPagerAdapter.getFragment(index);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FlashCardFragment myFragment = FlashCardFragment.create(data.get(position).getEnglish(),data.get(position).getCharacter(),true);
            mPageReferenceMap.put(position, myFragment);
            return myFragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            mPageReferenceMap.remove(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public FlashCardFragment getFragment(int key) {
            return mPageReferenceMap.get(key);
        }
    }
}

