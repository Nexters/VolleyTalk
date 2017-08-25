package com.teamnexters.volleytalk.team.model;

import com.google.gson.annotations.SerializedName;
import com.teamnexters.volleytalk.player.Player;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MIN on 2017. 7. 30..
 */

public class TeamDetailList implements Serializable {

    int seq;
    String name;
    String gender;
    String extablish;
    String homeground;
    String stadium;
    String teamlogo;
    int likecount;
    int postcount;
    String createdAt;
    String updatedAt;

    public TeamDetailList(int seq, String name, String gender, String extablish, String homeground, String stadium, String teamlogo, int likecount, int postcount, String createdAt, String updatedAt) {
        this.seq = seq;
        this.name = name;
        this.gender = gender;
        this.extablish = extablish;
        this.homeground = homeground;
        this.stadium = stadium;
        this.teamlogo = teamlogo;
        this.likecount = likecount;
        this.postcount = postcount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExtablish() {
        return extablish;
    }

    public void setExtablish(String extablish) {
        this.extablish = extablish;
    }

    public String getHomeground() {
        return homeground;
    }

    public void setHomeground(String homeground) {
        this.homeground = homeground;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTeamlogo() {
        return teamlogo;
    }

    public void setTeamlogo(String teamlogo) {
        this.teamlogo = teamlogo;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getPostcount() {
        return postcount;
    }

    public void setPostcount(int postcount) {
        this.postcount = postcount;
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
