package com.example.huascar.checkd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.huascar.checkd.models.Task;

/**
 * Created by huascar on 22/11/2017.
 */

public class TaskListUncheckdAdapter extends ArrayAdapter {

    private TextView viewDesc;
    private TextView titleInput;
    private TextView taskDesc;
    private TextView completedBox;

    public TaskListUncheckdAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_task_list, parent, false);

        }

        Task currentTaskItem = (Task) getItem(position);

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
