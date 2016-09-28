package com.example.drhyu.learnchinese;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jaume on 21/10/2014.
 */
public class StoppableRunnable implements Runnable {

    volatile boolean killIt = false;

    private ProgressBar pBar;
    private TextView tView;

    private long timePerQuestion;
    private long timePerCorrectQuestion;

    long time_per_progress;

    private PracticeActivity pA;

    private Lock lock;

    Thread t;

    public StoppableRunnable(ProgressBar pBar, TextView tView, long timePerQuestion,
                             long timePerCorrectQuestion, PracticeActivity pA) {
        this.pBar = pBar;
        this.tView = tView;
        this.timePerQuestion = timePerQuestion;
        this.timePerCorrectQuestion = timePerCorrectQuestion;
        this.pA = pA;
        time_per_progress = timePerQuestion/100;
        pBar.setMax(100);
        pBar.setProgress(100);
    }
    public StoppableRunnable(ProgressBar pBar, TextView tView, long timePerQuestion,
                             long timePerCorrectQuestion, PracticeActivity pA, int startingProgress) {
        this.pBar = pBar;
        this.tView = tView;
        this.timePerQuestion = timePerQuestion;
        this.timePerCorrectQuestion = timePerCorrectQuestion;
        this.pA = pA;
        time_per_progress = timePerQuestion/100;

        pBar.setMax(100);
        pBar.setProgress(startingProgress);
    }

    public int getProgress(){
        return pBar.getProgress();
    }

    @Override
    public void run() {
        while(pBar.getProgress() > 0 ){//&& !killIt){
            try {
                Thread.sleep(time_per_progress);
                pBar.incrementProgressBy(-1);
//                if(killIt){
//                    break;
//                }
            }
            catch (Exception e){}
        }
//        if(!killIt) {
//            pA.timerFinishedCallback();
//        }
    }

    public void kill() {
        killIt = true;
    }
}
