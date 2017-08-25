package com.teamnexters.volleytalk.team.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by johyunchol on 2016. 10. 25..
 */

public class TeamDetailModel implements Serializable {

    public TeamDetailModel() {
    }

    @SerializedName("seq")
    int seq;
    @SerializedName("name")
    String name;
    @SerializedName("gender")
    String gender;
    @SerializedName("extablish")
    int extablish;
    @SerializedName("homeground")
    String homeground;
    @SerializedName("stadium")
    String stadium;
    @SerializedName("teamlogo")
    String teamlogo;
    @SerializedName("likecount")
    int likecount;
    @SerializedName("postcount")
    int postcount;
    @SerializedName("createdAt")
    String createdAt;
    @SerializedName("updatedAt")
    String updatedAt;

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

    public int getExtablish() {
        return extablish;
    }

    public void setExtablish(int extablish) {
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
