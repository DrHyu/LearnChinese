package com.example.drhyu.learnchinese;

/**
 * Created by Jaume on 18/10/2014.
 */
public class Character {

    public long id;
    public String character;
    public String pinyin;
    public String english;
    public Long date_added;

    @Override
    public String toString() {
        return character + " | " + pinyin + " | " + english;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Long getDate_added() {
        return date_added;
    }

    public void setDate_added(Long date_added) {
        this.date_added = date_added;
    }

}