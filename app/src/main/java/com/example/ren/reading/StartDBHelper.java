package com.example.ren.reading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ren on 2017. 11. 3..
 */

public class StartDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "start.db";
    public static final String START_TABLE_NAME = "start";
    public static final String START_COLUMN_MONTH = "month";
    public static final String START_COLUMN_YEAR = "year";
    public static final String START_COLUMN_READING_TIME = "reading_time";

    public StartDBHelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " +           START_TABLE_NAME+
                "(_id integer primary key AUTOINCREMENT NOT NULL,"+  START_COLUMN_MONTH +
                " Text,"+                    START_COLUMN_YEAR+
                " Text,"+                    START_COLUMN_READING_TIME +
                " Text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(db);
    }

    public boolean insertStart(String month, String year, String reading_time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("reading_time", reading_time);

        db.insert(START_TABLE_NAME, null, contentValues);
        return true;
    }

    public void changeReadingTime(String reading_time) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE start SET reading_time='" + reading_time + "';");


    }
}
