package com.teamnexters.volleytalk.cheering;

import com.teamnexters.volleytalk.User;

/**
 * Created by MIN on 2017. 8. 16..
 */

public class Cheering {
    private int seq;
    private int playerseq;
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

    public int getPlayerseq() {
        return playerseq;
    }

    public void setPlayerseq(int playerseq) {
        this.playerseq = playerseq;
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
