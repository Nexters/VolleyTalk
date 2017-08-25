package com.teamnexters.volleytalk;

import java.io.Serializable;

/**
 * Created by MIN on 2017. 8. 19..
 */

public class User implements Serializable {
    private int seq;
    private String userid;
    private String nickname;
    private String email;
    private String profileimg_thumb;
    private String bgimg;
    private int likecount;
    private int postcount;
    private int followercount;
    private int followingcount;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileimg_thumb() {
        return profileimg_thumb;
    }

    public void setProfileimg_thumb(String profileimg_thumb) {
        this.profileimg_thumb = profileimg_thumb;
    }

    public String getBgimg() {
        return bgimg;
    }

    public void setBgimg(String bgimg) {
        this.bgimg = bgimg;
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

    public int getFollowercount() {
        return followercount;
    }

    public void setFollowercount(int followercount) {
        this.followercount = followercount;
    }

    public int getFollowingcount() {
        return followingcount;
    }

    public void setFollowingcount(int followingcount) {
        this.followingcount = followingcount;
    }
}
