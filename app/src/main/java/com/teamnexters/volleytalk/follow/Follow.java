package com.teamnexters.volleytalk.follow;

/**
 * Created by MIN on 2017. 8. 19..
 */

public class Follow {
    private int seq;
    private String followtype;
    private int typeseq;
    private String userid;

    public Follow(int seq, String followtype, int typeseq, String userid) {
        this.seq = seq;
        this.followtype = followtype;
        this.typeseq = typeseq;
        this.userid = userid;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getFollowtype() {
        return followtype;
    }

    public void setFollowtype(String followtype) {
        this.followtype = followtype;
    }

    public int getTypeseq() {
        return typeseq;
    }

    public void setTypeseq(int typeseq) {
        this.typeseq = typeseq;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
