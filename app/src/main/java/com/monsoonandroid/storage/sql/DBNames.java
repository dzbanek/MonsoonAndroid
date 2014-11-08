package com.monsoonandroid.storage.sql;

import android.provider.BaseColumns;

/**
 * Created by piotr on 08/11/14.
 */
public interface DBNames extends BaseColumns {
    public static final String TABLE_TASKS = "task";

    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_BCG_COLOR = "color_bcg";
    public static final String COLUMN_BCG_TEXT = "color_text";
}
