package com.example.huascar.checkd;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

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

        TextView completed = listItemView.findViewById(R.id.id);
        completed.setText(Integer.toString(currentTaskItem.getId()));

        Button viewMore = listItemView.findViewById(R.id.button2);
        viewMore.setTag(currentTaskItem);

        Button delete = listItemView.findViewById(R.id.delete);
        delete.setTag(currentTaskItem);
        // this line of code is to be picked up by the click event
//        listItemView.setTag(currentTaskItem);

        return listItemView;
        }
}
