package com.example.ren.reading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Adapter;

import java.util.ArrayList;

/**
 * Created by Nihal on 1/24/2017.
 */

public class DiaryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "diary.db";
    public static final String DIARY_TABLE_NAME = "diary";
    public static final String DIARY_COLUMN_ID = "_id";
    public static final String DIARY_COLUMN_CREATE_AT = "create_at";
    public static final String DIARY_COLUMN_TITLE = "title";
    public static final String DIARY_COLUMN_CONTENT = "content";

    public DiaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " +           DIARY_TABLE_NAME+
                "("+ DIARY_COLUMN_ID+" integer primary key AUTOINCREMENT NOT NULL,"+  DIARY_COLUMN_CREATE_AT +
                " Text,"+                    DIARY_COLUMN_TITLE+
                " Text,"+                    DIARY_COLUMN_CONTENT +
                " Text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(db);
    }

    public boolean insertdiary(String create_at, String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("create_at", create_at);
        contentValues.put("title", title);
        contentValues.put("content", content);

        db.insert(DIARY_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<DiaryInfo> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DiaryInfo> diary= new ArrayList<DiaryInfo>();
        Cursor result = db.rawQuery("select * from "+DIARY_TABLE_NAME , null);
        while(result.moveToNext()){
            diary.add( new DiaryInfo(result.getString(result.getColumnIndex(DIARY_COLUMN_ID)),result.getString(result.getColumnIndex(DIARY_COLUMN_CREATE_AT)), result.getString(result.getColumnIndex(DIARY_COLUMN_TITLE)),result.getString(result.getColumnIndex(DIARY_COLUMN_CONTENT))));

        }
        return diary;
    }

    public boolean updatediary(int id, int create_et, int title, int content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("create_et", create_et);
        contentValues.put("title", title);
        contentValues.put("content", content);

        db.update(DIARY_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }
    public void deletediary(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM diary WHERE _id='" + id + "';");
        // db.close();
    }

    public void changediary(String date,String title,String content, String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE diary SET create_at='" + date + "',title='" + title + "',content='" + content + "' WHERE _id='" + id + "';");


    }

    }