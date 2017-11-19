package com.example.huascar.checkd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huascar on 18/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_NAME = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TAG = "tag";
    private static final String KEY_COMPLETED = "completed";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
        + KEY_ID + "INTEGER PRIMARY KEY," + KEY_TITLE + "TEXT,"
        + KEY_DESCRIPTION + " TEXT," + KEY_TAG + "TEXT," + KEY_COMPLETED + "TEXT" + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createTask(String title, String description, Boolean completed, String tag) {
        Task task = new Task(title, description, completed);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title); // Shop Name
        values.put(KEY_DESCRIPTION, description); // Shop Phone Number
        values.put(KEY_COMPLETED, completed); // Shop Phone Number
        db.insert(TABLE_NAME, null, values); // Inserting Row
        db.close(); // Closing database connection
    }
}