package com.teamnexters.volleytalk.news;

/**
 * Created by MIN on 2017. 7. 29..
 */


public class TodayMatch {
    private String TeamA_name;
    private String TeamA_score;
    private String TeamB_name;
    private String TeamB_score;
    private String time;

    public TodayMatch(String teamA_name, String teamA_score, String teamB_name, String teamB_score, String time) {
        this.TeamA_name = teamA_name;
        this.TeamA_score = teamA_score;
        this.TeamB_name = teamB_name;
        this.TeamB_score = teamB_score;
        this.time = time;
    }

    public String getTeamA_name() {
        return TeamA_name;
    }

    public void setTeamA_name(String teamA_name) {
        TeamA_name = teamA_name;
    }

    public String getTeamA_score() {
        return TeamA_score;
    }

    public void setTeamA_score(String teamA_score) {
        TeamA_score = teamA_score;
    }

    public String getTeamB_name() {
        return TeamB_name;
    }

    public void setTeamB_name(String teamB_name) {
        TeamB_name = teamB_name;
    }

    public String getTeamB_score() {
        return TeamB_score;
    }

    public void setTeamB_score(String teamB_score) {
        TeamB_score = teamB_score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
