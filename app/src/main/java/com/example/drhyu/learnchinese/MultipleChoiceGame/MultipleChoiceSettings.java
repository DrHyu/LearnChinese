package com.example.drhyu.learnchinese.MultipleChoiceGame;

import java.io.Serializable;

/**
 * Created by Jaume on 6/24/2016.
 */
public class MultipleChoiceSettings implements Serializable {

    public boolean SHOW_HANZI_QUESTION     = true;
    public boolean SHOW_ENGLISH_QUESTION   = false;
    public boolean SHOW_PINYIN_QUESTION    = false;
    public boolean SHOW_HANZI_ANSWER       = false;
    public boolean SHOW_ENGLISH_ANSWER     = true;
    public boolean SHOW_PINYIN_ANSWER      = true;

    public int GAME_SPEED_FACTOR = 100;

    public MultipleChoiceSettings(){

    }

}
