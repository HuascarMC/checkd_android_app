package com.example.huascar.checkd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        String descScan = taskDesc.getText().toString();
        Log.d("desc", descScan);

        Boolean completedBox = this.completedBox.isEnabled();
        Log.d("completed", completedBox.toString());

        Task task = new Task();
        task.setId(1);
        task.setTitle(titleScan);
        task.setDescription(descScan);
        task.setCompleted(completedBox);

        mDBHelper.createTask(task);

        Toast.makeText(this, "Task saved.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void onMenuInflate() {
        // intent says go to here to there, where am I going when this method is called.
        Intent intent =
                new Intent(this, TaskList.class);

        // Answers class is a class with an array of answers
        // it's instantiated here to make the answers available
        TaskList taskList = new TaskList();

        // The answer class has a method that returns a randomAnswer
        // it's then stored into a variable of string
//        String randomAnswer = answers.getRandomAnswer();

        // The intent class seems to have a hashMap and in that hashmap
        // we store the randomAnswer with a key called "answer"
//        intent.putExtra("answer", randomAnswer);

        startActivity(intent);
    }
}
