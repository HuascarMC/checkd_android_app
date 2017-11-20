package com.example.huascar.checkd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        this.completedBox = findViewById(R.id.id);
    }

    public void onSubmitClick(View view) {
        String titleScan = titleInput.getText().toString();
        Log.d("name", titleScan);

        String descScan = taskDesc.getText().toString();
        Log.d("desc", descScan);

        Boolean completedBox = this.completedBox.isEnabled();
        Log.d("completed", completedBox.toString());

        Task task = new Task();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // pop-up message
        if (item.getItemId() == R.id.taskList) {
            Intent intent
                    = new Intent(
                    this,
                    activity_task_list_main.class);
            startActivity(intent);

            Toast.makeText(this,
                    "Task list is being shown.",
                    Toast.LENGTH_SHORT).show();

        } else if(item.getItemId() == R.id.add_task) {
            Intent intent
                    = new Intent(
                    this,
                    MainActivity.class);
            startActivity(intent);

            Toast.makeText(this,
                    "Add new task",
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
