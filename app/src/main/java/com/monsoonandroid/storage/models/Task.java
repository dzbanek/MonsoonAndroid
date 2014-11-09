package com.monsoonandroid.storage.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.monsoonandroid.storage.sql.DBNames;

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

    @DatabaseField(version = true, columnName = DBNames.COLUMN_TIMESTAMP)
    private long ts;
    
    @DatabaseField(columnName = DBNames.COLUMN_BCG_TEXT)
    private int textColor;

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
