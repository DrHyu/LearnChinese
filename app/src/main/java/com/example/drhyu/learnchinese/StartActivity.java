package com.example.drhyu.learnchinese;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import android.support.annotation.Nullable;
import android.support.annotation.IdRes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class StartActivity extends Activity {
    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);


        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTopOffset();
        mBottomBar.noNavBarGoodness();
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.bb_menu_character_history) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.root_layout, CharacterStatisticsViewFragment.newInstance(), "CStats")
                            .commit();
                }else if (menuItemId == R.id.bb_menu_practice){
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.root_layout, PreMultipleChoiceFragment.newInstance(), "PrePractice")
                            .commit();

                 }else if (menuItemId == R.id.bb_menu_flashcard){
                    getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root_layout, PreFlashcardFragment.newInstance(), "Flashcards")
                        .commit();
                }
                else{
                    Intent i = new Intent();
                    i.setClass(StartActivity.this,IndexActivity.class);
                    startActivity(i);
                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                Toast.makeText(getApplicationContext(), "HIHIIH", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }
}