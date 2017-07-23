package com.example.wacky.attenaide.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.wacky.attenaide.data.AttenaideContract.*;

import static android.provider.BaseColumns._ID;

/**
 * Created by wacky on 3/5/17.
 */

public class AttenaideDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "attenaide.db";
    private static final int DATABASE_VERSION = 1;



    public AttenaideDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_STUDENTS_TABLE = "CREATE TABLE " + AttenaideEntry.STUDENT_TABLE_NAME + " ("+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttenaideEntry.COLUMN_ENROLLMENT_NO + " TEXT UNIQUE NOT NULL, " +
                AttenaideEntry.COLUMN_NAME + " TEXT NOT NULL, "+
                AttenaideEntry.COLUMN_CLASS_ID + " INTEGER, "+
                "FOREIGN KEY("+AttenaideEntry.COLUMN_CLASS_ID+") REFERENCES "+AttenaideEntry.CLASS_TABLE_NAME+" ("+_ID+"));";

        final String SQL_CREATE_ATTENDANCE_TABLE = " CREATE TABLE " + AttenaideEntry.ATTEN_TABLE_NAME + " ("+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttenaideEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                AttenaideEntry.COLUMN_LECTURE_ID + " INTEGER, "+
                AttenaideEntry.COLUMN_STUDENT_ID + " INTEGER, "+
                AttenaideEntry.COLUMN_STATUS + " INTEGER NOT NULL, " +
                "FOREIGN KEY ("+AttenaideEntry.COLUMN_LECTURE_ID+") REFERENCES "+AttenaideEntry.LECTURE_TABLE_NAME + " ("+_ID+"), "+
                "FOREIGN KEY ("+AttenaideEntry.COLUMN_STUDENT_ID+") REFERENCES "+AttenaideEntry.STUDENT_TABLE_NAME + " ("+_ID+"));, ";

        final String SQL_CREATE_LECTURE_TABLE = " CREATE TABLE " + AttenaideEntry.LECTURE_TABLE_NAME + " ("+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttenaideEntry.COLUMN_CLASS_ID + " INTEGER, " +
                AttenaideEntry.COLUMN_DATE + " TEXT NOT NULL, "+
                AttenaideEntry.COLUMN_LECTURE_NO + " INTEGER NOT NULL, "+
                "FOREIGN KEY ("+AttenaideEntry.COLUMN_CLASS_ID+") REFERENCES "+AttenaideEntry.CLASS_TABLE_NAME + " ("+_ID+"));";

        final String SQL_CREATE_CLASS_TABLE = " CREATE TABLE " + AttenaideEntry.CLASS_TABLE_NAME + " ("+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttenaideEntry.COLUMN_CLASS_NAME + " TEXT UNIQUE NOT NULL);";


        db.execSQL(SQL_CREATE_STUDENTS_TABLE);
        db.execSQL(SQL_CREATE_ATTENDANCE_TABLE);
        db.execSQL(SQL_CREATE_CLASS_TABLE);
        db.execSQL(SQL_CREATE_LECTURE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
