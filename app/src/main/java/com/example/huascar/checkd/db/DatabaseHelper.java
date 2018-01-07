package com.example.huascar.checkd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.huascar.checkd.models.Task;

import java.lang.reflect.Array;
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
    private static final String KEY_COMPLETED = "completed";

    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE + " TEXT, "
                + KEY_DESCRIPTION + " TEXT, " + KEY_COMPLETED + " INTEGER )";
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
//        values.put(KEY_ID, task.getId());
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_COMPLETED, task.getCompletedBoolean());
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Task> getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Task> taskList = new ArrayList<>();
        Task task;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                task = new Task();
                task.setId((cursor.getInt(0)));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setCompleted(cursor.getInt(3) == 1);

                taskList.add(task);
            }
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public ArrayList<Task> getAllCheckdTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Task> taskList = new ArrayList<>();
        Task task;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                task = new Task();
                task.setId((cursor.getInt(0)));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setCompleted(cursor.getInt(3) == 1);

                if (task.getCompletedBoolean() == true) {
                    taskList.add(task);
                }
            }
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public ArrayList<Task> getAllUncheckdTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Task> taskList = new ArrayList<>();
        Task task;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                task = new Task();
                task.setId((cursor.getInt(0)));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setCompleted(cursor.getInt(3) == 1);

                if (task.getCompletedBoolean() == false) {
                    taskList.add(task);
                }
            }
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public void update(Task taskChange, Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_COMPLETED, task.getCompletedBoolean());
        String whereClause = "id=?";
        String whereArgs[] = {taskChange.getId().toString()};
        db.update("Tasks", values, whereClause, whereArgs);
        db.close(); // Closing database connection
    }

    public void delete(Task task) {
        Integer id = task.getId();
        delete(id);
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE ID =" + id);
        Log.d("delete", "has been deleted");
        db.close();
    }

    public Task findTaskById(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID =" + id, null);

        Task task = new Task();

        cursor.moveToFirst();

        task.setId(cursor.getInt(0));
        task.setTitle(cursor.getString(1));
        task.setDescription(cursor.getString(2));
        task.setCompleted(cursor.getInt(3) == 1);
        db.close();
        return task;
    }
}
