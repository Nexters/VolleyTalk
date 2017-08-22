package com.teamnexters.volleytalk.team.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johyunchol on 2016. 10. 25..
 */

public class TeamModel implements Serializable {

    public TeamModel() {
    }

    @SerializedName("seq")
    int seq;
    @SerializedName("team_color")
    int team_color;
    @SerializedName("team_img")
    String team_img;
    @SerializedName("team_text")
    String team_text;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getTeamColor() {
        return team_color;
    }

    public void setTeamColor(int team_color) {
        this.team_color = team_color;
    }

    public String getTeamImg() {
        return team_img;
    }

    public void setTeamImg(String team_img) {
        this.team_img = team_img;
    }

    public String getTeamText() {
        return team_text;
    }

    public void setTeamText(String team_text) {
        this.team_text = team_text;
    }
}