package com.monsoonandroid.storage.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.monsoonandroid.storage.models.Task;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by piotr on 08/11/14.
 */
public class SQLDatabaseHelper extends OrmLiteSqliteOpenHelper {

    public SQLDatabaseHelper(Context context)
    {
        super(context, DBNames.DATABASE_NAME, null, DBNames.DATABASE_VERSION);
    }

    protected SQLDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    protected SQLDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, int configFileId) {
        super(context, databaseName, factory, databaseVersion, configFileId);
    }

    protected SQLDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, File configFile) {
        super(context, databaseName, factory, databaseVersion, configFile);
    }

    protected SQLDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, InputStream stream) {
        super(context, databaseName, factory, databaseVersion, stream);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i("sql", "helper onCreate()");
        try {
            TableUtils.createTable(connectionSource, Task.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTables() throws SQLException {
        TableUtils.clearTable(getConnectionSource(), Task.class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // @TODO
    }
}
