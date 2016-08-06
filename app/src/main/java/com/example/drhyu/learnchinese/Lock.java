package com.example.drhyu.learnchinese;

/**
 * Created by Jaume on 21/10/2014.
 */
public class Lock{

    private boolean isLocked = false;

    public synchronized void lock()
            throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}