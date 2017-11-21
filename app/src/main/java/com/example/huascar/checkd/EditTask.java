package com.example.huascar.checkd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditTask extends AppCompatActivity {

    private TextView editTitle;
    private TextView editDesc;
    private TextView editCbox;
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
}
