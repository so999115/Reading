package com.example.ren.reading;

/*BOOKINFO와 같은 거*/

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookInfo{

    String id;
    String bookname;
    String writer;
    String publisher;
    String page;
    String genre;

    public BookInfo(String id, String bookname, String writer, String publisher, String page,String genre){
        this.bookname = bookname;
        this.writer = writer;
        this.publisher = publisher;
        this.page = page;
        this.genre = genre;
        this.id = id;
    }


    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {this.bookname = bookname;}

    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {this.writer = writer;}

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {this.publisher = publisher;}

    public String getPage() {
        return page;
    }
    public void setPage(String page) {this.page = page;}

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {this.genre = genre;}

    public String getId() {
        return id;
    }
    public void setId(String id) {this.id = id;}

}