package com.teamnexters.volleytalk.player;

import com.teamnexters.volleytalk.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MIN on 2017. 8. 10..
 */

public class Player implements Serializable {
    private int seq;
    private int teamseq;
    private String backnumber;
    private String name;
    private String physical;
    private String position;
    private String likecount;
    private String postcount;
    private List<User> like;
    private List<User> follow;

    public Player(int seq, int teamseq, String backnumber, String name, String physical, String position, String likecount, String postcount, List<User> like, List<User> follow) {
        this.seq = seq;
        this.teamseq = teamseq;
        this.backnumber = backnumber;
        this.name = name;
        this.physical = physical;
        this.position = position;
        this.likecount = likecount;
        this.postcount = postcount;
        this.like = like;
        this.follow = follow;
    }

    public Player(String backnumber, String name, String likecount, String postcount) {
        this.backnumber = backnumber;
        this.name = name;
        this.likecount = likecount;
        this.postcount = postcount;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getTeamseq() {
        return teamseq;
    }

    public void setTeamseq(int teamseq) {
        this.teamseq = teamseq;
    }

    public String getBacknumber() {
        return backnumber;
    }

    public void setBacknumber(String backnumber) {
        this.backnumber = backnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysical() {
        return physical;
    }

    public void setPhysical(String physical) {
        this.physical = physical;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLikecount() {
        return likecount;
    }

    public void setLikecount(String likecount) {
        this.likecount = likecount;
    }

    public String getPostcount() {
        return postcount;
    }

    public void setPostcount(String postcount) {
        this.postcount = postcount;
    }

    public List<User> getLike() {
        return like;
    }

    public void setLike(List<User> like) {
        this.like = like;
    }

    public List<User> getFollow() {
        return follow;
    }

    public void setFollow(List<User> follow) {
        this.follow = follow;
    }
}