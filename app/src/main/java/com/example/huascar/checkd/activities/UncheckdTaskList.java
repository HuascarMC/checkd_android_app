package com.example.huascar.checkd.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huascar.checkd.R;
import com.example.huascar.checkd.TaskListCheckdAdapter;
import com.example.huascar.checkd.TaskListUncheckdAdapter;
import com.example.huascar.checkd.db.DatabaseHelper;
import com.example.huascar.checkd.models.Task;

import java.util.ArrayList;

public class UncheckdTaskList extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncheckd_task_list);

        mDBHelper = new DatabaseHelper(this);
        mDatabase  = mDBHelper.getWritableDatabase();
        mDatabase.close();

        final ArrayList<Task> taskList = mDBHelper.getAllUncheckdTasks();

        final TaskListUncheckdAdapter taskListUncheckdAdapter = new TaskListUncheckdAdapter(this, taskList);
        ListView listView = findViewById(R.id.uncheckdlist);

        listView.setAdapter(taskListUncheckdAdapter);
    }

    public void getTask(View viewMore) {
        Task selectedTask = (Task) viewMore.getTag();
        Intent intent = new Intent(this, EditTask.class);

        intent.putExtra("taskId", selectedTask.getId());
        startActivity(intent);

        Toast.makeText(this, selectedTask.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public void deleteTask(View delete) {
        Task selectedTask = (Task) delete.getTag();
        mDBHelper.delete(selectedTask);

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
