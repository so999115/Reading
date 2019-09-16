package com.example.ren.reading;

/**
 * Created by so999 on 2017-11-02.
 */


public class PastReadBookInfo {

    String bookname;
    String page;
    String genre;
    String progress;

    public PastReadBookInfo(String bookname, String page, String genre, String progress) {
        this.bookname = bookname;
        this.page = page;
        this.genre = genre;
        this.progress = progress;
    }

    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {this.bookname = bookname;}


    public String getPage() {
        return page;
    }
    public void setPage(String page) {this.page = page;}

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {this.genre = genre;}

    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {this.progress = progress;}

}