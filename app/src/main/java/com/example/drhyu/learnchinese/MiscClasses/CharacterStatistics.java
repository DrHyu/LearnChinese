package com.example.drhyu.learnchinese.MiscClasses;

import java.lang.*;

/**
 * Created by Jaume on 6/19/2016.
 */
public class CharacterStatistics extends Character {

    public int times_studied;
    public int times_failed;

    public int average_time;
    public int last_time;

    public String lists_present;

    @Override
    public String toString() {
        return character + " | " + pinyin + " | " + english + " | " + times_studied + " | " + times_failed + " | " + average_time + " | " + last_time;
    }

    public String getLists_present() {
        return lists_present;
    }

    public void setLists_present(String lists_present) {
        this.lists_present = lists_present;
    }

    public int getTimes_studied() {
        return times_studied;
    }

    public void setTimes_studied(int times_studied) {
        this.times_studied = times_studied;
    }

    public int getTimes_failed() {
        return times_failed;
    }

    public void setTimes_failed(int times_failed) {
        this.times_failed = times_failed;
    }

    public int getAverage_time() {
        return average_time;
    }

    public void setAverage_time(int average_time) {
        this.average_time = average_time;
    }

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }
}
