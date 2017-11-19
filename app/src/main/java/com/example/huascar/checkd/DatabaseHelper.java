package com.example.huascar.checkd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

    public void createTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle()); // Shop Name
        values.put(KEY_DESCRIPTION, task.getDescription()); // Shop Phone Number
        values.put(KEY_COMPLETED, task.getCompleted()); // Shop Phone Number
        db.insert(TABLE_NAME, null, values); // Inserting Row
        db.close(); // Closing database connection
    }

    public ArrayList<Task> getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Task> taskList = new ArrayList<Task>();
        Task taskModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                taskModel = new Task();
                taskModel.setID(cursor.getString(0));
                taskModel.setFirstName(cursor.getString(1));
                taskModel.setLastName(cursor.getString(2));
                taskList.add(taskModel);
            }
        }
        cursor.close();
        db.close();
        return taskList;
    }
}