package com.example.wacky.attenaide;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;

import com.example.wacky.attenaide.data.AttenaideContract;
import com.example.wacky.attenaide.data.AttenaideDbHelper;

public class DataEntry extends AppCompatActivity {

    private SQLiteDatabase mDb;
    private StudentListAdapter mAdapter;
    private EditText mNewEnrollNoEditText;
    private EditText mNewNameEditText;
    private final static String LOG_TAG = DataEntry.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        RecyclerView studentlistRecyclerView;
        studentlistRecyclerView = (RecyclerView) this.findViewById(R.id.student_list_recycler_view);
        mNewEnrollNoEditText = (EditText) findViewById(R.id.enrollment_entry);
        mNewNameEditText = (EditText) findViewById(R.id.name_entry);

//        studentlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AttenaideDbHelper dbHelper = new AttenaideDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getAllStudents();
        mAdapter = new StudentListAdapter(this,cursor);
        studentlistRecyclerView.setAdapter(mAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerview, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir){
                long id = (long) viewHolder.itemView.getTag();
//                removeStudent(id);
                mAdapter.swapCursor(getAllStudents());
            }
        }).attachToRecyclerView(studentlistRecyclerView);
    }

    public void addToStudentsTable(View view){
        if (mNewEnrollNoEditText.getText().length() == 0 ||
                mNewNameEditText.getText().length() == 0) {
            return;
        }
        addNewStudent(mNewEnrollNoEditText.getText().toString(), mNewNameEditText.getText().toString());
        mNewEnrollNoEditText.getText().clear();
        mNewNameEditText.getText().clear();
    }

    private Cursor getAllStudents() {
        return mDb.query(
                AttenaideContract.AttenaideEntry.STUDENT_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                AttenaideContract.AttenaideEntry.COLUMN_ENROLLMENT_NO+" desc"
        );
    }
    private long addNewStudent(String enrollment, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AttenaideContract.AttenaideEntry.COLUMN_ENROLLMENT_NO, enrollment );
        contentValues.put(AttenaideContract.AttenaideEntry.COLUMN_NAME, name);
        return mDb.insert(AttenaideContract.AttenaideEntry.STUDENT_TABLE_NAME, null, contentValues);
    }
}
