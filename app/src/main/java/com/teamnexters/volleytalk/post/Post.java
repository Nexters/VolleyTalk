package com.teamnexters.volleytalk.post;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by MIN on 2017. 8. 9..
 */

public class Post implements Serializable {
    private int seq;
    private String teamseq;
    private String title;
    private String contents;
    private String img_url;
    private String img_url_thumb;
    private String userid;
    private int like_count;
    private int reply_count;
    private String createdAt;
    private String updatedAt;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTeamseq() {
        return teamseq;
    }

    public void setTeamseq(String teamseq) {
        this.teamseq = teamseq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url_thumb() {
        return img_url_thumb;
    }

    public void setImg_url_thumb(String img_url_thumb) {
        this.img_url_thumb = img_url_thumb;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
