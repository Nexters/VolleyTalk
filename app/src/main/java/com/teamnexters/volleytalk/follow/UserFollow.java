package com.teamnexters.volleytalk.follow;

/**
 * Created by MIN on 2017. 8. 20..
 */

public class UserFollow {
    private int seq;
    private String followtype;
    private int typeseq;
    private String userid;
    private UserInfo userInfo;

    public UserFollow(int seq, String followtype, int typeseq, String userid, UserInfo userInfo) {
        this.seq = seq;
        this.followtype = followtype;
        this.typeseq = typeseq;
        this.userid = userid;
        this.userInfo = userInfo;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfo {
        private String nickname;
        private int followercount;
        private int postcount;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getFollowercount() {
            return followercount;
        }

        public void setFollowercount(int followercount) {
            this.followercount = followercount;
        }

        public int getPostcount() {
            return postcount;
        }

        public void setPostcount(int postcount) {
            this.postcount = postcount;
        }
    }
}
