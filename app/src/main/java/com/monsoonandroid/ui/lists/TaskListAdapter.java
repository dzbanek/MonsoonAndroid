package com.monsoonandroid.ui.lists;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.monsoonandroid.storage.TaskStorage;
import com.monsoonandroid.storage.models.Task;

import java.util.List;

/**
 * Created by piotr on 08/11/14.
 */
public class TaskListAdapter extends BaseAdapter {

    private TaskStorage taskStorage;
    private List<Task> tasks;

    public TaskListAdapter(TaskStorage taskStorage)
    {
        super();
        this.tasks = taskStorage.findAllTasks();
        this.taskStorage = taskStorage;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tasks.get(i).getID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
