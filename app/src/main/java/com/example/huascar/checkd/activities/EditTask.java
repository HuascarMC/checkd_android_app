package com.example.huascar.checkd.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huascar.checkd.R;
import com.example.huascar.checkd.db.DatabaseHelper;
import com.example.huascar.checkd.models.Task;

public class EditTask extends AppCompatActivity {

    private TextView editTitle;
    private TextView editDesc;
    private CheckBox editCbox;
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDBHelper;
    private Task taskChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        mDBHelper = new DatabaseHelper(this);
        mDatabase  = mDBHelper.getWritableDatabase();
        mDatabase.close();

        Intent intent = getIntent();

        Bundle extra = intent.getExtras();
        int taskId = extra.getInt("taskId");

        taskChange = mDBHelper.findTaskById(taskId);

        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        editCbox = findViewById(R.id.editCbox);

        editTitle.setText(taskChange.getTitle());
        editDesc.setText(taskChange.getDescription());
        editCbox.setChecked(taskChange.getCompletedBoolean());
    }

    public void UpdateOnClick(View update) {

        String titleScan = editTitle.getText().toString();
        Log.d("name", titleScan);

        String descScan = editDesc.getText().toString();
        Log.d("desc", descScan);

        Boolean completedBox = this.editCbox.isEnabled();
        Log.d("completed", completedBox.toString());

        Task task = new Task();
        task.setTitle(titleScan);
        task.setDescription(descScan);
        task.setCompleted(completedBox);

        mDBHelper.update(taskChange, task);

        Toast.makeText(this, "Task updated.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static class CheckdTaskList extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_checkd_task_list);
        }
    }
}
