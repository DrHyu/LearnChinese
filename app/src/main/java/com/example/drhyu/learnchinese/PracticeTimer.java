package com.example.drhyu.learnchinese;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Jaume on 21/10/2014.
 */
public class PracticeTimer {

    private ProgressBar pBar;
    private TextView    tView;

    private long timePerCorrectAnswer;

    private PracticeActivity pA;

    private long max_time =0;
    private long time_left = 0;

    private boolean running = false;

    private static MCountDownTimer mTimer;

    public PracticeTimer(ProgressBar pBar, TextView tView,
                         long baseTime, long timePerCorrectAnswer, PracticeActivity pA) {
        this.pBar = pBar;
        this.tView = tView;
        this.max_time = baseTime;
        this.timePerCorrectAnswer = timePerCorrectAnswer;
        this.pA = pA;

        pBar.setMax((int)baseTime/100);
    }

    public void start(){
        if(!running) {
            resume();
        }
    }

    public void addCorrectTime(){
        mTimer.cancel();
        time_left += timePerCorrectAnswer;
        pBar.incrementProgressBy((int)timePerCorrectAnswer/100);
        startNewTimer(time_left);
    }

    public void pause(){
        mTimer.cancel();
        mTimer = null;
        running = false;
    }

    public void resume(){
        startNewTimer(max_time);
        running = true;
    }

    public void stop(){
        mTimer.cancel();
        mTimer = null;
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

    private void startNewTimer(long t){
        mTimer = new MCountDownTimer(t,100) {
            @Override
            public void onTick(long m) {
                time_left = m;
                pBar.setProgress((int)m/100);
                Log.v("TIMER", "Time left" + time_left);
            }

            @Override
            public void onFinish() {
                pA.timerFinishedCallback();
            }
        }.start();
    }
}
