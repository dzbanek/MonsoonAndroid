package com.monsoonandroid.ui.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.monsoonandroid.R;
import com.monsoonandroid.storage.TaskStorage;
import com.monsoonandroid.storage.models.Task;
import com.monsoonandroid.ui.lists.TaskListAdapter;


public class TaskListActivity extends BaseActivity implements TaskListAdapter.TaskListListener {

    private static final int REQUEST_ADD_TASK = 11;

    private ListView listView;
    private TaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // deleteDatabase(DBNames.DATABASE_NAME);

        setContentView(R.layout.activity_task_list);

        listView = (ListView) findViewById(R.id.listView);
        TaskStorage taskStorage = getTaskStorage();

        this.adapter = new TaskListAdapter(this, taskStorage);
        adapter.setListener(this);
        listView.setAdapter(adapter);

        ImageButton addTaskButton = (ImageButton) findViewById(R.id.buttonAddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddTaskActivity();
            }
        });
    }

    private void startAddTaskActivity()
    {
        Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
        startActivityForResult(intent, REQUEST_ADD_TASK);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_TASK)
        {
            if (resultCode == RESULT_OK)
            {
                Task task = Task.fromIntentExtras(data);
                getTaskStorage().addTask(task);
                this.adapter.addTask(task);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteAction(long id) {
        int deleted = getTaskStorage().removeTask(id);
    }
}
