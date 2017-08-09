package com.teamnexters.volleytalk.player;

/**
 * Created by MIN on 2017. 8. 8..
 */
public class Player {

    private String seq;
    private String teamseq;
    private String backnumber;
    private String name;
    private String physical;
    private String likecount;
    private String postcount;
    private String like;
    private String follow;

    public Player(String seq, String teamseq, String backnumber, String name, String physical, String likecount, String postcount) {
        this.seq = seq;
        this.teamseq = teamseq;
        this.backnumber = backnumber;
        this.name = name;
        this.physical = physical;
        this.likecount = likecount;
        this.postcount = postcount;
    }

    public Player(String backnumber, String name, String likecount, String postcount) {
        this.backnumber = backnumber;
        this.name = name;
        this.likecount = likecount;
        this.postcount = postcount;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTeamseq() {
        return teamseq;
    }

    public void setTeamseq(String teamseq) {
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

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }
}
