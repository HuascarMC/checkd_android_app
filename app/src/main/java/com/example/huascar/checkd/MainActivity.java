package com.example.huascar.checkd;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
