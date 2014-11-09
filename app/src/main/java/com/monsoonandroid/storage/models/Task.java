package com.monsoonandroid.storage.models;

import android.content.Intent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.monsoonandroid.storage.sql.DBNames;
import com.monsoonandroid.ui.screens.AddTaskActivity;

import java.util.Date;

/**
 * Created by piotr on 08/11/14.
 */
@DatabaseTable(tableName = DBNames.TABLE_TASKS)
public class Task {

    @DatabaseField(generatedId = true, columnName = DBNames._ID)
    private long id;

    @DatabaseField(columnName = DBNames.COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = DBNames.COLUMN_BCG_COLOR)
    private int backgroundColor;

    @DatabaseField(version = true, columnName = DBNames.COLUMN_TIMESTAMP, dataType = DataType.DATE)
    private Date ts;
    
    @DatabaseField(columnName = DBNames.COLUMN_BCG_TEXT)
    private int textColor;

    public static Task fromIntentExtras(Intent intent)
    {
        String desc = intent.getStringExtra(AddTaskActivity.ARG_TEXT);
        int txtColor = intent.getIntExtra(AddTaskActivity.ARG_TXT_COLOR, 0);
        int bcgColor = intent.getIntExtra(AddTaskActivity.ARG_BCG_COLOR, 0);
        return new Task(desc, bcgColor, txtColor);
    }

    public Task(String description, int backgroundColor, int textColor) {
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    Task()
    {

    }

    public long getID() {
        return id;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getDescription() {
        return description;
    }

    public int getTextColor() {
        return textColor;
    }
}
