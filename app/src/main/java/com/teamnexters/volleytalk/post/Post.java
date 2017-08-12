package com.teamnexters.volleytalk.post;

/**
 * Created by MIN on 2017. 8. 9..
 */

public class Post {
    private String title;
    private String description;
    private String date;
    private String writer;
    private int like_count;
    private int reply_count;
    private String picURL;


    //나중에 picURL 포함한 constructor 추가하기
    public Post(String title, String description, String date, String writer, int like_count, int reply_count) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.writer = writer;
        this.like_count = like_count;
        this.reply_count = reply_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }
}
