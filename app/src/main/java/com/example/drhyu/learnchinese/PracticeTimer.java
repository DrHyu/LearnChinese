package com.example.drhyu.learnchinese;

import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Jaume on 21/10/2014.
 */
public class PracticeTimer {

    private ProgressBar pBar;
    private TextView    tView;

    private long baseTime;
    private long timePerCorrectQuestion;
    private int currentProgress;

    private PracticeActivity pA;

    long time_per_progress;

    private boolean running = false;

    Thread t;
    StoppableRunnable r;

    public PracticeTimer(ProgressBar pBar, TextView tView,
                         long baseTime, long timePerCorrectQuestion, PracticeActivity pA) {
        this.pBar = pBar;
        this.tView = tView;
        this.baseTime = baseTime;
        this.timePerCorrectQuestion = timePerCorrectQuestion;
        this.pA = pA;

        time_per_progress = baseTime /100;
    }

    public void start(){
        currentProgress =100;
        resume();
    }

    public void addCorrectTime(){
        try {
            //lock.lock();
                pBar.incrementProgressBy((int)(timePerCorrectQuestion/time_per_progress));
            //lock.unlock();
        }
        catch (Exception e){
        }
    }

    public void pause(){
        currentProgress = r.getProgress();
        r.kill();
        running = false;
    }

    public void resume(){
        r = new StoppableRunnable(pBar,tView, baseTime,timePerCorrectQuestion,pA,currentProgress);
        t = new Thread(r);
        t.start();
        running = true;
    }

    public void stop(){
        r.kill();
        running = false;
    }

    public boolean isRunning(){
        return running;
    }
}
