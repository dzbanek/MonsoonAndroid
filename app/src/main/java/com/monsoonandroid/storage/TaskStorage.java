package com.monsoonandroid.storage;

import com.monsoonandroid.storage.models.Task;

import java.util.List;

/**
 * Created by piotr on 08/11/14.
 */
public interface TaskStorage {

    public long addTask(Task task);
    public int removeTask(long id);
    public List<Task> findAllTasks();
}
