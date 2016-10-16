package com.example.drhyu.learnchinese.MultipleChoiceGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import com.example.drhyu.learnchinese.R;

import java.io.Serializable;


/**
 * Created by Jaume on 6/23/2016.
 */
public class SettingsActivity extends Activity implements View.OnClickListener  {
    
    private MultipleChoiceSettings ps;

    private Switch s1,s2,s3,s4,s5,s6;
    private SeekBar sb_game_speed;
    private Button save, drop;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity_layout);

        Intent i = getIntent();

        ps = (MultipleChoiceSettings) i.getSerializableExtra("MultipleChoiceSettings");

        setHandlers();
        updateViews();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public void onClick(View v)
    {
        //Switch-like arrangement
        if(v instanceof Switch){
            Switch s = (Switch) v;
            if(s.getId() == R.id.switch1){
                ps.SHOW_HANZI_QUESTION = !ps.SHOW_HANZI_QUESTION;
                if(ps.SHOW_HANZI_QUESTION){ps.SHOW_HANZI_ANSWER = false;}
            }
            else if(s.getId() == R.id.switch2){
                ps.SHOW_PINYIN_QUESTION = !ps.SHOW_PINYIN_QUESTION;
                if(ps.SHOW_PINYIN_QUESTION){ps.SHOW_PINYIN_ANSWER = false;}
            }
            else if(s.getId() == R.id.switch3){
                ps.SHOW_ENGLISH_QUESTION = !ps.SHOW_ENGLISH_QUESTION;
                if(ps.SHOW_ENGLISH_QUESTION){ps.SHOW_ENGLISH_ANSWER = false;}
            }
            else if(s.getId() == R.id.switch4){
                ps.SHOW_HANZI_ANSWER = !ps.SHOW_HANZI_ANSWER;
                if(ps.SHOW_HANZI_ANSWER){ps.SHOW_HANZI_QUESTION = false;}
            }
            else if(s.getId() == R.id.switch5){
                ps.SHOW_PINYIN_ANSWER = !ps.SHOW_PINYIN_ANSWER;
                if(ps.SHOW_PINYIN_ANSWER){ps.SHOW_PINYIN_QUESTION = false;}
            }
            else if(s.getId() == R.id.switch6){
                ps.SHOW_ENGLISH_ANSWER = !ps.SHOW_ENGLISH_ANSWER;
                if(ps.SHOW_ENGLISH_ANSWER){ps.SHOW_ENGLISH_QUESTION = false;}

            }
            updateViews();
        }
        else if (v instanceof SeekBar){
            SeekBar sb = (SeekBar) v;
            ps.GAME_SPEED_FACTOR = sb.getProgress();
        }
        else if (v instanceof Button){
            Button b = (Button) v;
            if(b.getId() == save.getId()){



                Intent i = new Intent();
                i.putExtra("MultipleChoiceSettings",(Serializable)ps);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
            else if(b.getId() == drop.getId()){
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }


    }

    private void setHandlers()
    {
        s1 = (Switch) this.findViewById(R.id.switch1);
        s2 = (Switch) this.findViewById(R.id.switch2);
        s3 = (Switch) this.findViewById(R.id.switch3);
        s4 = (Switch) this.findViewById(R.id.switch4);
        s5 = (Switch) this.findViewById(R.id.switch5);
        s6 = (Switch) this.findViewById(R.id.switch6);

        save = (Button) this.findViewById(R.id.save_button);
        drop = (Button) this.findViewById(R.id.cancel_button);

        sb_game_speed = (SeekBar) this.findViewById(R.id.seekBar);

        s1.setOnClickListener(this);
        s2.setOnClickListener(this);
        s3.setOnClickListener(this);
        s4.setOnClickListener(this);
        s5.setOnClickListener(this);
        s6.setOnClickListener(this);

        save.setOnClickListener(this);
        drop.setOnClickListener(this);

        sb_game_speed.setOnClickListener(this);

    }

    private void updateViews()
    {
        if(     (ps.SHOW_HANZI_QUESTION||ps.SHOW_PINYIN_QUESTION||ps.SHOW_ENGLISH_QUESTION) &&
                (ps.SHOW_HANZI_ANSWER  ||ps.SHOW_PINYIN_ANSWER  ||ps.SHOW_ENGLISH_ANSWER) &&
                !(ps.SHOW_HANZI_QUESTION &&ps.SHOW_PINYIN_QUESTION&&ps.SHOW_ENGLISH_QUESTION) &&
                !(ps.SHOW_HANZI_ANSWER   &&ps.SHOW_PINYIN_ANSWER  &&ps.SHOW_ENGLISH_ANSWER)) {
            save.setEnabled(true);
        }
        else {
            save.setEnabled(false);
        }

        s1.setChecked(ps.SHOW_HANZI_QUESTION);
        s2.setChecked(ps.SHOW_PINYIN_QUESTION);
        s3.setChecked(ps.SHOW_ENGLISH_QUESTION);
        s4.setChecked(ps.SHOW_HANZI_ANSWER);
        s5.setChecked(ps.SHOW_PINYIN_ANSWER);
        s6.setChecked(ps.SHOW_ENGLISH_ANSWER);

        sb_game_speed.setProgress(ps.GAME_SPEED_FACTOR);
    }
}
