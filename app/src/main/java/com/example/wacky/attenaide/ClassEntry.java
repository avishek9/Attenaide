package com.example.wacky.attenaide;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wacky.attenaide.data.AttenaideContract.*;
import com.example.wacky.attenaide.data.AttenaideDbHelper;


public class ClassEntry extends AppCompatActivity {

    private EditText mNewClassNameEditText;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_entry);

        mNewClassNameEditText = (EditText) findViewById(R.id.class_name_edit_text);
        AttenaideDbHelper dbHelper = new AttenaideDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
    }

    public void addNewClass(View view) {
        try {
            addClass(mNewClassNameEditText.getText().toString());
            mNewClassNameEditText.getText().clear();
            Toast.makeText(this, "Class Added!", Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteConstraintException e){
            Toast.makeText(this, "ERROR! Class already present", Toast.LENGTH_SHORT).show();
        }
    }

    private long addClass(String name){

    ContentValues cv = new ContentValues();
        cv.put(AttenaideEntry.COLUMN_CLASS_NAME, name);
        return mDb.insertOrThrow(AttenaideEntry.CLASS_TABLE_NAME, null, cv);
    }
}
