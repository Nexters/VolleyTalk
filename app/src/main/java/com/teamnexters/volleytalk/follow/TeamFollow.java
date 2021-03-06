package com.teamnexters.volleytalk.follow;

/**
 * Created by MIN on 2017. 8. 2..
 */

public class TeamFollow {

    private int seq;
    private String followtype;
    private int typeseq;
    private String userid;
    private Count teamInfo;

    public TeamFollow(int seq, String followtype, int typeseq, String userid, Count teamInfo) {
        this.seq = seq;
        this.followtype = followtype;
        this.typeseq = typeseq;
        this.userid = userid;
        this.teamInfo = teamInfo;
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

    public Count getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(Count teamInfo) {
        this.teamInfo = teamInfo;
    }

    public class Count {
        private int likecount;
        private int postcount;

        public Count(int likecount, int postcount) {
            this.likecount = likecount;
            this.postcount = postcount;
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
    }
}
