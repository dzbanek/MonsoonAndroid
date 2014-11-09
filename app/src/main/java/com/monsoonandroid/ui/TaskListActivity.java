package com.monsoonandroid.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.monsoonandroid.R;
import com.monsoonandroid.storage.TaskStorage;
import com.monsoonandroid.storage.models.Task;
import com.monsoonandroid.storage.sql.DBNames;
import com.monsoonandroid.ui.lists.TaskListAdapter;


public class TaskListActivity extends BaseActivity implements TaskListAdapter.TaskListListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteDatabase(DBNames.DATABASE_NAME);

        setContentView(R.layout.activity_task_list);

        listView = (ListView) findViewById(R.id.listView);
        TaskStorage taskStorage = getTaskStorage();
        if (taskStorage.numberOfTasks() == 0)
        {
            taskStorage.addTask(new Task("Test1", Color.RED, Color.BLACK));
            taskStorage.addTask(new Task("Test2", Color.YELLOW, Color.BLUE));
        }

        TaskListAdapter adapter = new TaskListAdapter(this, taskStorage);
        adapter.setListener(this);
        listView.setAdapter(adapter);

        ImageButton addTaskButton = (ImageButton) findViewById(R.id.buttonAddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteAction(long id) {
        int deleted = getTaskStorage().removeTask(id);
        Log.i("sql", "deleted = " + deleted);
    }
}
