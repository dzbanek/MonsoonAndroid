package com.monsoonandroid.storage.sql;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.monsoonandroid.storage.TaskStorage;
import com.monsoonandroid.storage.models.Task;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by piotr on 08/11/14.
 */
public class TaskSQLStorage implements TaskStorage {

    private SQLDatabaseHelper dbHelper;

    public TaskSQLStorage(Context context, SQLDatabaseHelper dbHelper)
    {
        super();
        this.dbHelper = dbHelper;
    }

    @Override
    public long addTask(Task task) {
        try {
            //http://ormlite.com/javadoc/ormlite-core/com/j256/ormlite/dao/Dao.html#create(T)
            boolean created = getTaskDao().create(task) == 1;

            if (created)
                return task.getID();
            else
                return -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int removeTask(long id) {
        try {
            return getTaskDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Task> findAllTasks() {
        try {
            return getTaskDao().queryBuilder().orderBy(DBNames.COLUMN_TIMESTAMP, false).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public long numberOfTasks() {
        try {
            return getTaskDao().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Dao<Task, Long> getTaskDao()
    {
        try {
            return dbHelper.getDao(Task.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
