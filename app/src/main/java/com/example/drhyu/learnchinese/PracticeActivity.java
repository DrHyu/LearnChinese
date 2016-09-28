package com.example.drhyu.learnchinese;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PracticeActivity extends Activity implements View.OnClickListener {

    private ChDataSource datasource;
    private List<Character> data;

    private Boolean game_on = false;

    private int game_correct_answer = 0;
    private int correct_answer_position = 0;

    private final int NUMBER_OF_ANSWERS         = 7;

    private final long BASE_TIME             = 100_000;
    private final long CORRECT_ANSW_TIME     = 30000;

    private PracticeSettings ps;

    private static PracticeTimer pTimer;

    public enum GAME_SATE {GAME_ON,GAME_PAUSED,GAME_END};

    public GAME_SATE game_sate;

    private AutoResizeButton b1,b2,b3,b4,b5,b6,b7,b8;
    private Button sb,gc;

    private AutoResizeTextView textView1;
    private ProgressBar timerBar;

    private ArrayList<Integer> game_question_order = new ArrayList<Integer>();
    private ArrayList<Integer> button_options      = new ArrayList<Integer>();
    private ArrayList<String>  correctly_answered  = new ArrayList<String>();
    private ArrayList<String>  uncorrectly_answered  = new ArrayList<String>();

    private  TableInfo ti;
    private int from =0;
    private int to = 0;


    private PracticeActivity pA = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_layout);

        Intent i = getIntent();
        ti = (TableInfo) i.getSerializableExtra("tableInfo");

        from = i.getIntExtra("from",0);
        to = i.getIntExtra("to",0);

        setHandlers();
        ps = new PracticeSettings();
        // TODO Set and get from DB

        game_initialize();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        datasource.open();
        game_resume();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        datasource.close();
        game_pause();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);

        if(resultCode == Activity.RESULT_OK) {
            ps = (PracticeSettings) i.getSerializableExtra("PracticeSettings");
            setButtonText();
        }
    }

    @Override
    public void onClick(View v)
    {
        //Switch-like arrangement
        if(v instanceof Switch){
            Switch s = (Switch) v;
        }
        else if(v instanceof Button){
            Button btn = (Button) v;
            if(game_sate == GAME_SATE.GAME_ON) {
                if (btn.getId() == R.id.button1 && game_correct_answer == 1) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                } else if (btn.getId() == R.id.button2 && game_correct_answer == 2) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                } else if (btn.getId() == R.id.button3 && game_correct_answer == 3) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                } else if (btn.getId() == R.id.button4 && game_correct_answer == 4) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                } else if (btn.getId() == R.id.button5 && game_correct_answer == 5) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                } else if (btn.getId() == R.id.button6 && game_correct_answer == 6) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                } else if (btn.getId() == R.id.button7 && game_correct_answer == 7) {
                    correctly_answered.add(data.get(game_question_order.get(0)).character);
                    pTimer.addCorrectTime();
                    game_set_next_question();
                }

                // Failed answer
                else if (btn.getId() == R.id.button1) {
                    uncorrectly_answered.add(data.get(button_options.get(0)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                } else if (btn.getId() == R.id.button2) {
                    uncorrectly_answered.add(data.get(button_options.get(1)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                } else if (btn.getId() == R.id.button3) {
                    uncorrectly_answered.add(data.get(button_options.get(2)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                } else if (btn.getId() == R.id.button4) {
                    uncorrectly_answered.add(data.get(button_options.get(3)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                } else if (btn.getId() == R.id.button5) {
                    uncorrectly_answered.add(data.get(button_options.get(4)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                } else if (btn.getId() == R.id.button6) {
                    uncorrectly_answered.add(data.get(button_options.get(5)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                    ;
                } else if (btn.getId() == R.id.button7) {
                    uncorrectly_answered.add(data.get(button_options.get(6)).getCharacter());
                    uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
                    game_wrong_answer();
                }
            }

            if (btn.getId() == R.id.settings_button) {
                Intent i = new Intent();
                i.setClass(PracticeActivity.this, SettingsActivity.class);
                i.putExtra("PracticeSettings", (Serializable) ps);
                startActivityForResult(i, 0);
            }
            if (btn.getId() == R.id.game_control) {
                if (game_sate == GAME_SATE.GAME_ON) {
                    game_pause();
                } else if(game_sate == GAME_SATE.GAME_PAUSED){
                    game_resume();
                } else if(game_sate == GAME_SATE.GAME_END){
                    game_end();
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void game_initialize()
    {
        datasource = new ChDataSource(this);
        datasource.open();

        data = datasource.getAllCharacters(ti.getTableName());

        if(to >0){
            int size = data.size();
            for(int i = size-1; i >= to ;i--){
                data.remove(i);
            }
        }
        if(from > 0){
            for (int i = 0; i < from ; i++){
                data.remove(0);
            }
        }

        if(data.size() < NUMBER_OF_ANSWERS){
            Toast.makeText(getApplicationContext(),
                    "There are "+ data.size() + " characters in this group, at least "
                            + (NUMBER_OF_ANSWERS+1) + " are needed."
                    , Toast.LENGTH_SHORT).show();
            game_end();
        }
        else{
            game_sate = GAME_SATE.GAME_ON;
            game_start();
        }

        // Fill the array with all the question numbers
        for (int i=0; i < data.size(); i++){
            game_question_order.add(i);
        }
        // Shuffle it randomly
        for(int i=data.size()-1; i>0; i--){
            int rand = (int) (Math.random()*i);
            int temp = game_question_order.get(i);
            game_question_order.set(i,game_question_order.get(rand));
            game_question_order.set(rand, temp);
        }

        //Remove possible previously un/correctly answered questions
        correctly_answered.clear();
        uncorrectly_answered.clear();

        game_set_next_question();
    }

    private void game_set_next_question()
    {
        if(game_sate == GAME_SATE.GAME_ON) {

            //Remove previous question from the list
            game_question_order.remove(0);

            if (game_question_order.size() == 0) {
                //game completed
                Toast.makeText(getApplicationContext(),
                    "GAME COMPLETED", Toast.LENGTH_SHORT).show();
                game_end();
                return;
            }


            // Fill the array with all the question numbers
            int[] temp = new int[data.size()-1];

            int count = 0;
            for (int i=0; i < data.size(); i++){
                if(i != game_question_order.get(0)) {
                    temp[count] = i;
                    count++;
                }
            }
            // Shuffle the order
            for(int i=temp.length-1; i>0; i--){
                int rand = (int) (Math.random()*i);
                int t = temp[i];
                temp[i] = temp[rand];
                temp[rand] = t;
            }

            button_options.clear();
            // Get 7 random answers
            for(int i = 0; i < NUMBER_OF_ANSWERS; i++){
                button_options.add(temp[i]);
            }
            //Should give 7 random pointers to data to fill buttons with dummy stuff

            // Get a random position for the correct answer
            correct_answer_position = (int) (Math.random() * NUMBER_OF_ANSWERS);


            button_options.set(correct_answer_position, game_question_order.get(0));
            game_correct_answer = correct_answer_position +1;

            setButtonText();
        }

    }

    private void setButtonText()
    {

        String questionText = "";
        //Put the question in the editText
        if(ps.SHOW_HANZI_QUESTION) {
            questionText = questionText + data.get(button_options.get(correct_answer_position)).getCharacter() + "\n";
        }
        if(ps.SHOW_PINYIN_QUESTION){
            questionText = questionText + data.get(button_options.get(correct_answer_position)).getPinyin() + "\n";
        }
        if(ps.SHOW_ENGLISH_QUESTION){
            questionText = questionText + data.get(button_options.get(correct_answer_position)).getEnglish() + "\n";
        }
        if (questionText.endsWith("\n")) {
            questionText = questionText.substring(0, questionText.length() - 1);
        }
        textView1.setTextSize(100);
        textView1.setText(questionText);

        String bText[] = new String[NUMBER_OF_ANSWERS];

        for(int i=0; i < bText.length; i ++){
            bText[i] = "";
        }
        //Put the text in the buttons
        if(ps.SHOW_HANZI_ANSWER) {
            for(int i=0; i < bText.length; i ++){
                bText[i] = bText[i] + data.get(button_options.get(i)).getCharacter() + "\n";
            }
        }
        if(ps.SHOW_PINYIN_ANSWER) {
            for(int i=0; i < bText.length; i ++){
                bText[i] = bText[i] + data.get(button_options.get(i)).getPinyin() + "\n";
            }
        }
        if(ps.SHOW_ENGLISH_ANSWER) {
            for(int i=0; i < bText.length; i ++){
                bText[i] = bText[i] + data.get(button_options.get(i)).getEnglish() + "\n";
            }
        }
        b1.setTextSize(100);
        b1.setText(bText[0]);
        b2.setTextSize(100);
        b2.setText(bText[1]);
        b3.setTextSize(100);
        b3.setText(bText[2]);
        b4.setTextSize(100);
        b4.setText(bText[3]);
        b5.setTextSize(100);
        b5.setText(bText[4]);
        b6.setTextSize(100);
        b6.setText(bText[5]);
        b7.setTextSize(100);
        b7.setText(bText[6]);

    }

    private void game_wrong_answer()
    {
        Toast.makeText(getApplicationContext(),
                "WRONG ! Correct Answer: " + game_correct_answer , Toast.LENGTH_SHORT).show();

        pTimer.addCorrectTime();
        game_set_next_question();
    }

    public void timerFinishedCallback()
    {
        uncorrectly_answered.add(data.get(game_question_order.get(0)).character);
        game_end();
        pA.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(pA, "TIME OUT !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void game_restart(){
        game_start();
    }
    private void game_start(){
        game_sate = GAME_SATE.GAME_ON;
        pTimer = new PracticeTimer(timerBar,null, BASE_TIME*(ps.GAME_SPEED_FACTOR/100),CORRECT_ANSW_TIME*(ps.GAME_SPEED_FACTOR/100),this);

        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
        b5.setClickable(true);
        b6.setClickable(true);
        b7.setClickable(true);

        gc.setText("PAUSE");
    }

    private void game_pause() {

        game_sate = GAME_SATE.GAME_PAUSED;
        pTimer.pause();
        b1.setClickable(false);
        b2.setClickable(false);
        b3.setClickable(false);
        b4.setClickable(false);
        b5.setClickable(false);
        b6.setClickable(false);
        b7.setClickable(false);

        gc.setClickable(true);
        sb.setClickable(true);
        gc.setText("RESUME");
    }
    private void game_resume() {

        game_sate = GAME_SATE.GAME_ON;
        pTimer.resume();

        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
        b5.setClickable(true);
        b6.setClickable(true);
        b7.setClickable(true);

        gc.setText("PAUSE");

    }
    private void game_end(){
        // Update the character history
        game_sate = GAME_SATE.GAME_END;

        b1.setClickable(false);
        b2.setClickable(false);
        b3.setClickable(false);
        b4.setClickable(false);
        b5.setClickable(false);
        b6.setClickable(false);
        b7.setClickable(false);

        CharacterStatistics c;

        for(int i = 0; i < correctly_answered.size(); i ++){
            c = datasource.getSingleCharacterStatistics(correctly_answered.get(i));
            c.times_studied ++;
            c.average_time += 5; //TODO
            c.last_time = 3;
            datasource.putCharacterStatistics(c);
        }

        for(int i = 0; i < uncorrectly_answered.size(); i ++){
            c = datasource.getSingleCharacterStatistics(uncorrectly_answered.get(i));
            c.times_studied ++;
            c.times_failed ++;
            c.average_time += 5; //TODO
            c.last_time = 3;
            datasource.putCharacterStatistics(c);
        }




    }

    private void setHandlers()
    {

        textView1 = (AutoResizeTextView) this.findViewById(R.id.textView1);
        timerBar = (ProgressBar) this.findViewById(R.id.progressBar1);


        b1 = (AutoResizeButton) this.findViewById(R.id.button1);
        b2 = (AutoResizeButton) this.findViewById(R.id.button2);
        b3 = (AutoResizeButton) this.findViewById(R.id.button3);
        b4 = (AutoResizeButton) this.findViewById(R.id.button4);
        b5 = (AutoResizeButton) this.findViewById(R.id.button5);
        b6 = (AutoResizeButton) this.findViewById(R.id.button6);
        b7 = (AutoResizeButton) this.findViewById(R.id.button7);

        sb = (Button) this.findViewById(R.id.settings_button);
        gc = (Button) this.findViewById(R.id.game_control);

        //b8 = (Button) this.findViewById(R.id.button8);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);

        sb.setOnClickListener(this);
        gc.setOnClickListener(this);

    }

}
