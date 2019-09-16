package com.example.ren.reading;

/**
 * Created by Ren on 2017. 9. 14..
 */

public class DiaryInfo {
    private String id;
    private String create_at;
    private String title;
    private String content;

    public DiaryInfo(String id,String create_at, String title, String content) {
        this.id = id;
        this.create_at = create_at;
        this.title = title;
        this.content = content;
    }

    public void setCreate_at(String create_at) {create_at = create_at;}

    public void setTitle(String title) {
        title = title;
    }

    public void setContent(String content) {
        content = content;
    }

    public void setId(String id) {
        id = id;
    }

    public String getCreate_at() {
        return this.create_at;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getId() {
        return this.id;
    }



}
