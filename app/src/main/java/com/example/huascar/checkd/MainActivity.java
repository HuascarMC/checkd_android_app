package com.example.huascar.checkd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private TextView viewDesc;
    private TextView titleInput;
    private TextView taskDesc;
    private TextView completedBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);
        mDatabase  = mDBHelper.getWritableDatabase();
        mDatabase.close();

        this.viewDesc = findViewById(R.id.viewDesc);
        this.titleInput = findViewById(R.id.titleInput);
        this.taskDesc = findViewById(R.id.taskDesc);
        this.completedBox = findViewById(R.id.completedBox);
    }

    public void onSubmitClick(View view) {
        String titleScan = titleInput.getText().toString();
        Log.d("name", titleScan);

        String descScan = titleInput.getText().toString();
        Log.d("desc", descScan);

        String completedBox = titleInput.getText().toString();
        Log.d("completed", completedBox);

        Task task = new Task();
        task.setTitle(titleScan);
        task.setDescription(descScan);
        task.setCompleted(completedBox);

        mDBHelper.createTask(task);
    }
}
