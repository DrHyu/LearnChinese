package com.example.drhyu.learnchinese.DBStuff;

import java.io.Serializable;

/**
 * Created by Jaume on 19/10/2014.
 */
public class TableInfo implements Serializable {

    private int tableSize;
    private String tableName;

    public TableInfo( String tableName,int tableSize ) {
        this.tableSize = tableSize;
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return tableName;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }




}
