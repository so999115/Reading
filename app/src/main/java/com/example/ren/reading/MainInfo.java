package com.example.ren.reading;

import android.util.Log;
import android.widget.ProgressBar;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Ren on 2017. 10. 8..
 */
public class MainInfo{

    String id;
    String bookname;
    String page;
    String genre;
    String progress;


    public MainInfo( String id,String bookname,String page,  String genre,String progress){
        this.id = id;
        this.bookname = bookname;
        this.page = page;
        this.genre = genre;
        this.progress = progress;
    }

    public String getBookname() {
        return this.bookname;
    }
    public void setBookname(String bookname) {
        bookname = bookname;
    }

    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {}

    public String getPage() {
        return page;
    }
    public void setPage(String page) {this.page = page;}

    public String getId() {
        return id;
    }
    public void setId(String id) {this.id = id;}

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {this.genre = genre;}
}