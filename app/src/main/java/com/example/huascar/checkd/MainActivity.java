package com.example.huascar.checkd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);
        mDatabase  = mDBHelper.getWritableDatabase();
        mDatabase.close();

        ArrayList<Task> taskList = new ArrayList<>();



        TopMoviesAdapter moviesAdapter = new TopMoviesAdapter(this, movies);

        ListView listView = findViewById(R.id.list);

        //this calls the getView method from the TopMoviesAdapter
        listView.setAdapter(moviesAdapter);
    }



}
