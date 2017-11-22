package com.example.huascar.checkd.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.huascar.checkd.R;
import com.example.huascar.checkd.TaskListAdapter;
import com.example.huascar.checkd.TaskListCheckdAdapter;
import com.example.huascar.checkd.db.DatabaseHelper;
import com.example.huascar.checkd.models.Task;

import java.util.ArrayList;

public class CheckdTaskList extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkd_task_list);

        mDBHelper = new DatabaseHelper(this);
        mDatabase  = mDBHelper.getWritableDatabase();
        mDatabase.close();

        final ArrayList<Task> taskList = mDBHelper.getAllCheckdTasks();

        final TaskListCheckdAdapter taskListCheckdAdapter = new TaskListCheckdAdapter(this, taskList);
        ListView listView = findViewById(R.id.checkdlist);

        listView.setAdapter(taskListCheckdAdapter);

    }
}
