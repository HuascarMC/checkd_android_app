package com.example.huascar.checkd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    







//    public ArrayList<Task> getAllRecords() {
//        database = this.getReadableDatabase();
//        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
//        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
//        ContactModel contactModel;
//        if (cursor.getCount() > 0) {
//            for (int i = 0; i < cursor.getCount(); i++) {
//                cursor.moveToNext();
//                contactModel = new ContactModel();
//                contactModel.setID(cursor.getString(0));
//                contactModel.setFirstName(cursor.getString(1));
//                contactModel.setLastName(cursor.getString(2));
//                contacts.add(contactModel);
//            }
//        }
//        cursor.close();
//        database.close();
//        return contacts;
//    }



}
