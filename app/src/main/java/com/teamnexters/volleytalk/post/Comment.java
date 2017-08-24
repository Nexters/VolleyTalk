package com.teamnexters.volleytalk.post;

import com.teamnexters.volleytalk.User;

/**
 * Created by MIN on 2017. 8. 24..
 */

public class Comment {
    private int seq;
    private int postseq;
    private String comment;
    private String userid;
    private String createdAt;
    private User user;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getPostseq() {
        return postseq;
    }

    public void setPostseq(int postseq) {
        this.postseq = postseq;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
