package com.teamnexters.volleytalk.follow;

import com.teamnexters.volleytalk.player.Player;

/**
 * Created by MIN on 2017. 8. 20..
 */

public class PlayerFollow {
    private int seq;
    private String followtype;
    private int typeseq;
    private String userid;
    private Player playerInfo;

    public PlayerFollow(int seq, String followtype, int typeseq, String userid, Player playerInfo) {
        this.seq = seq;
        this.followtype = followtype;
        this.typeseq = typeseq;
        this.userid = userid;
        this.playerInfo = playerInfo;
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

    public Player getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(Player playerInfo) {
        this.playerInfo = playerInfo;
    }
}
