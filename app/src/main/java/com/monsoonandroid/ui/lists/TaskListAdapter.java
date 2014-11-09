package com.monsoonandroid.ui.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.monsoonandroid.R;
import com.monsoonandroid.storage.TaskStorage;
import com.monsoonandroid.storage.models.Task;
import com.monsoonandroid.utils.ScreenUtils;

import java.util.List;

/**
 * Created by piotr on 08/11/14.
 */
public class TaskListAdapter extends BaseAdapter {

    private List<Task> tasks;
    private LayoutInflater layoutInflater;
    private TaskListListener listener;

    public void setListener(TaskListListener listener) {
        this.listener = listener;
    }

    public interface TaskListListener
    {
        public void onDeleteAction(long id);
    }

    public TaskListAdapter(Context context, TaskStorage taskStorage)
    {
        super();
        this.tasks = taskStorage.findAllTasks();
        this.layoutInflater = LayoutInflater.from(context);
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_task, null);
            viewHolder.txtView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.root = convertView.findViewById(R.id.root);
            viewHolder.btnDelete = (ImageButton) convertView.findViewById(R.id.imageButton);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Task task = (Task) getItem(i);

        viewHolder.txtView.setText(task.getDescription());
        viewHolder.root.setBackgroundColor(task.getBackgroundColor());
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTask(task);
            }
        });

        viewHolder.txtView.setTextColor(task.getTextColor());
        return convertView;
    }

    private void deleteTask(Task task)
    {
        if (this.listener != null)
        {
            listener.onDeleteAction(task.getID());
        }
        this.tasks.remove(task);
        notifyDataSetChanged();
    }

    public void addTask(Task task)
    {

    }

    static class ViewHolder
    {
        public TextView txtView;
        public View root;
        public ImageButton btnDelete;
    }
}
