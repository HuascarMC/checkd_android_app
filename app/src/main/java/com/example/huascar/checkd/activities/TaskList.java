package com.example.huascar.checkd.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.huascar.checkd.R;
import com.example.huascar.checkd.TaskListAdapter;
import com.example.huascar.checkd.actions.SwipeDismissListViewTouchListener;
import com.example.huascar.checkd.db.DatabaseHelper;
import com.example.huascar.checkd.activities.EditTask;
import com.example.huascar.checkd.models.Task;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDBHelper;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list_main);

        mDBHelper = new DatabaseHelper(this);
        mDatabase  = mDBHelper.getWritableDatabase();
        mDatabase.close();

        final ArrayList<Task> taskList = mDBHelper.getAllTasks();

        final TaskListAdapter taskListAdapter = new TaskListAdapter(this, taskList);
        ListView listView = findViewById(R.id.list);

        listView.setAdapter(taskListAdapter);

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {



                                    taskList.remove(position);
                                    Task task = taskList.get(position);

                                    mDBHelper.delete(task);

                                    taskListAdapter.notifyDataSetChanged();

                                }

                            }
                        });
        listView.setOnTouchListener(touchListener);

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