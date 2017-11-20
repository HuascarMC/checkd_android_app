package com.example.huascar.checkd;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class activity_task_list_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list_main);

        DatabaseHelper mDBHelper = new DatabaseHelper(this);

        ArrayList<Task> taskList = mDBHelper.getAllTasks();

        TaskListAdapter taskListAdapter = new TaskListAdapter(this, taskList);
        ListView listView = findViewById(R.id.list);
    }
}
