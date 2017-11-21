package com.example.huascar.checkd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.huascar.checkd.models.Task;

import java.util.ArrayList;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private TextView viewDesc;
    private TextView titleInput;
    private TextView taskDesc;
    private TextView completedBox;

    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_task_list, parent, false);
        }

        Task currentTaskItem = getItem(position);

        TextView title = listItemView.findViewById(R.id.title);
        title.setText(currentTaskItem.getTitle());

        TextView description = listItemView.findViewById(R.id.desc);
        description.setText(currentTaskItem.getDescription());

        Button viewMore = listItemView.findViewById(R.id.button2);
        viewMore.setTag(currentTaskItem);

        Button delete = listItemView.findViewById(R.id.delete);
        delete.setTag(currentTaskItem);
        // this line of code is to be picked up by the click event
//        listItemView.setTag(currentTaskItem);

        return listItemView;
        }
}
