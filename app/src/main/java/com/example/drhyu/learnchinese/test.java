package com.example.drhyu.learnchinese;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class test extends AppCompatActivity {
    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);


        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTopOffset();
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.bb_menu_recents) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.root_layout, CharacterStatisticsViewFragment.newInstance(), "CStats")
                            .commit();
                }else if (menuItemId == R.id.bb_menu_favorites){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.root_layout, PrePracticeFragment.newInstance(), "CStats")
                            .commit();
                } else{
                    Intent i = new Intent();
                    i.setClass(test.this,IndexActivity.class);
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