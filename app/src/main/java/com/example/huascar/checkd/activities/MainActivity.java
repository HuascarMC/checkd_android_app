package com.example.huascar.checkd.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huascar.checkd.R;
import com.example.huascar.checkd.db.DatabaseHelper;
import com.example.huascar.checkd.models.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private TextView viewDesc;
    private TextView titleInput;
    private TextView taskDesc;
    private CheckBox completedBox;
    private ProgressBar progressBar;

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

        progressBar = findViewById(R.id.progressBar);
        getProgress();

    }

    public void onSubmitClick(View view) {
        String titleScan = titleInput.getText().toString();
        Log.d("name", titleScan);

        String descScan = taskDesc.getText().toString();
        Log.d("desc", descScan);

        Boolean completedBox = checkCheckbox(this.completedBox);
        Log.d("completed", completedBox.toString());

        Task task = new Task();
        task.setTitle(titleScan);
        task.setDescription(descScan);
        task.setCompleted(completedBox);

        mDBHelper.createTask(task);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);

        Button play_button = (Button)this.findViewById(R.id.button);
        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("sound", "Playing sound...");
                mp.start();
            }
        });

        Toast.makeText(this, "Task saved.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public Boolean checkCheckbox(CheckBox checkBox) {
        if (this.completedBox.isChecked()) {
            return true;
        }else {
            return false;
        }
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
                    TaskList.class);
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

    public void getProgress() {
        ArrayList<Task> completed = mDBHelper.getAllCheckdTasks();
        ArrayList<Task> tasks = mDBHelper.getAllTasks();
        int completedTasks = 0;
        int allTasks = tasks.size();

        for(Task completedTask : completed) {
            completedTasks ++;
        }

        if (allTasks != 0) {
            int progress = ((completedTasks*100)/allTasks);
            progressBar.setProgress(progress);
            Log.d("progress", Integer.toString(progress));
        } else {
            progressBar.setProgress(0);
        }


    }
}
