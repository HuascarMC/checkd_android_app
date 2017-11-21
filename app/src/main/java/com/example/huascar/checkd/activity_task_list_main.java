package com.example.huascar.checkd;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class activity_task_list_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list_main);

        DatabaseHelper mDBHelper = new DatabaseHelper(this);

        ArrayList<Task> taskList = mDBHelper.getAllTasks();

        TaskListAdapter taskListAdapter = new TaskListAdapter(this, taskList);
        ListView listView = findViewById(R.id.list);

        listView.setAdapter(taskListAdapter);
    }


    public void getTask(View listItemSelected) {
        Task selectedTask = (Task) listItemSelected.getTag();
        Intent intent = new Intent(this, EditTask.class);

        intent.putExtra("taskId", selectedTask.getId());
        startActivity(intent);

        Toast.makeText(this, selectedTask.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
