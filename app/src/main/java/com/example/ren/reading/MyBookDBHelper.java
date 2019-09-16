package com.example.ren.reading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * Created by so999 on 2017-11-02.
 */

public class MyBookDBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "mybook.db";

    //읽고있는책
    public static final String NOW_TABLE_NAME = "now";
    public static final String NOW_BOOK_BOOKNAME = "now_book_bookname";
    public static final String NOW_BOOK_PAGE = "now_book_page";
    public static final String NOW_BOOK_GENRE = "now_book_genre";
    public static final String NOW_BOOK_PROGRESS = "now_book_progress";
    public static final String NOW_BOOK_ID = "_id";

    //읽을 책
    public static final String FUTURE_TABLE_NAME = "future";
    public static final String FUTURE_BOOK_BOOKNAME = "future_book_bookname";
    public static final String FUTURE_BOOK_PAGE = "future_book_page";
    public static final String FUTURE_BOOK_GENRE = "future_book_genre";
    public static final String FUTURE_BOOK_PROGRESS = "future_book_progress";

    //읽었던 책
    public static final String PAST_TABLE_NAME = "past";
    public static final String PAST_BOOK_BOOKNAME = "past_book_bookname";
    public static final String PAST_BOOK_PAGE = "past_book_page";
    public static final String PAST_BOOK_GENRE = "past_book_genre";
    public static final String PAST_BOOK_PROGRESS = "past_book_progress";


    public MyBookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table now ("+NOW_BOOK_ID+" integer primary key AUTOINCREMENT NOT NULL," + NOW_BOOK_BOOKNAME +
                " Text," + NOW_BOOK_PAGE +
                " Text," + NOW_BOOK_GENRE +
                " Text," + NOW_BOOK_PROGRESS +
                " Text)"
        );

        db.execSQL("create table future ( _id integer primary key AUTOINCREMENT NOT NULL," + FUTURE_BOOK_BOOKNAME +
                " Text," + FUTURE_BOOK_PAGE +
                " Text," + FUTURE_BOOK_GENRE +
                " Text," + FUTURE_BOOK_PROGRESS +
                " Text)"
        );

        db.execSQL("create table past ( _id integer primary key AUTOINCREMENT NOT NULL," + PAST_BOOK_BOOKNAME +
                " Text," + PAST_BOOK_PAGE +
                " Text," + PAST_BOOK_GENRE +
                " Text," + PAST_BOOK_PROGRESS +
                " Text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS now");
        db.execSQL("DROP TABLE IF EXISTS future");
        db.execSQL("DROP TABLE IF EXISTS past");
        onCreate(db);
    }

    //읽는 책 리스트뷰와 insert(책제목,페이지,장르,진행률)
    public ArrayList<NowReadBookInfo> ngetData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NowReadBookInfo> NOW = new ArrayList<NowReadBookInfo>();
        Cursor result = db.rawQuery("select * from now", null);
        while (result.moveToNext()) {
            boolean add = NOW.add(new NowReadBookInfo(result.getString(result.getColumnIndex(NOW_BOOK_BOOKNAME)), result.getString(result.getColumnIndex(NOW_BOOK_PAGE)), result.getString(result.getColumnIndex(NOW_BOOK_GENRE)), result.getString(result.getColumnIndex(NOW_BOOK_PROGRESS))));
        }
        return NOW;
    }

    public boolean InsertNow(String now_book_bookname, String now_book_page, String now_book_genre, String now_book_progress) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("now_book_bookname", now_book_bookname);
        contentValues.put("now_book_page", now_book_page);
        contentValues.put("now_book_genre", now_book_genre);
        contentValues.put("now_book_progress", now_book_progress);

        db.insert(NOW_TABLE_NAME, null, contentValues);
        return true;
    }


//읽을 책 리스트뷰와 insert

    public ArrayList<PastReadBookInfo> pgetData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<PastReadBookInfo> PAST = new ArrayList<PastReadBookInfo>();
        Cursor result = db.rawQuery("select * from past", null);
        while (result.moveToNext()) {
            boolean add = PAST.add(new PastReadBookInfo(result.getString(result.getColumnIndex(PAST_BOOK_BOOKNAME)), result.getString(result.getColumnIndex(PAST_BOOK_PAGE)), result.getString(result.getColumnIndex(PAST_BOOK_GENRE)), result.getString(result.getColumnIndex(PAST_BOOK_PROGRESS))));
        }
        return PAST;
    }

    public boolean InsertPast(String past_book_bookname, String past_book_page, String past_book_genre, String past_book_progress) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("past_book_bookname", past_book_bookname);
        contentValues.put("past_book_page", past_book_page);
        contentValues.put("past_book_genre", past_book_genre);
        contentValues.put("past_book_progress", past_book_progress);

        db.insert(PAST_TABLE_NAME, null, contentValues);
        return true;
    }


//읽었던 책 리스트뷰와 insert

    public ArrayList<FutureReadBookInfo> fgetData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FutureReadBookInfo> FUTURE = new ArrayList<FutureReadBookInfo>();
        Cursor result = db.rawQuery("select * from future", null);
        while (result.moveToNext()) {
            boolean add = FUTURE.add(new FutureReadBookInfo(result.getString(result.getColumnIndex(FUTURE_BOOK_BOOKNAME)), result.getString(result.getColumnIndex(FUTURE_BOOK_PAGE)), result.getString(result.getColumnIndex(FUTURE_BOOK_GENRE)), result.getString(result.getColumnIndex(FUTURE_BOOK_PROGRESS))));
        }
        return FUTURE;
    }

    public boolean InsertFuture(String future_book_bookname, String future_book_page, String future_book_genre, String future_book_progress) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("future_book_bookname", future_book_bookname);
        contentValues.put("future_book_page", future_book_page);
        contentValues.put("future_book_genre", future_book_genre);
        contentValues.put("future_book_progress", future_book_progress);

        db.insert(FUTURE_TABLE_NAME, null, contentValues);
        return true;

    }


    //메인 리스트뷰와 insert(수정중)
    public ArrayList<MainInfo> maingetData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<MainInfo> MAIN = new ArrayList<MainInfo>();
        Cursor result = db.rawQuery("select * from now", null);
        while (result.moveToNext()) {
            boolean add = MAIN.add(new MainInfo(result.getString(result.getColumnIndex(NOW_BOOK_ID)),result.getString(result.getColumnIndex(NOW_BOOK_BOOKNAME)),result.getString(result.getColumnIndex(NOW_BOOK_PAGE)), result.getString(result.getColumnIndex(NOW_BOOK_GENRE)), result.getString(result.getColumnIndex(NOW_BOOK_PROGRESS))));
        }
        return MAIN;
    }
    public void changeprogress(String progress, String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE now SET now_book_progress='" + progress + "' WHERE _id='" + id + "';");


    }
    //완독시 리스트에서 지우기 위해
    public void deletenow(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM now WHERE _id='" + id + "';");
        // db.close();
    }


    //장르 통계
    public String genre_state(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from past where past_book_genre ='"+a+"'",null);
        String result = String.valueOf(c.getCount());

        return result;
    }

    //전체 통계
    public String genre_state_total(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from past",null);
        String result = String.valueOf(c.getCount());

        return result;
    }



}