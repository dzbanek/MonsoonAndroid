package com.monsoonandroid.ui;

import android.app.Activity;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.monsoonandroid.storage.TaskStorage;
import com.monsoonandroid.storage.sql.SQLDatabaseHelper;
import com.monsoonandroid.storage.sql.TaskSQLStorage;

/**
 * Created by piotr on 08/11/14.
 */
public class BaseActivity extends Activity {

    private TaskStorage taskStorage;

    protected TaskStorage getTaskStorage()
    {
        if (taskStorage == null)
        {
            SQLDatabaseHelper dbHelper = OpenHelperManager.getHelper(this, SQLDatabaseHelper.class);
            this.taskStorage = new TaskSQLStorage(this, dbHelper);

        }
        return taskStorage;
    }
}
