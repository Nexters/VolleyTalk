package com.teamnexters.volleytalk.team;

/**
 * Created by smart on 2017-08-12.
 */

public class Team_man {
    private String imgURL;
    private int seq;

    public Team_man(String imgURL, int seq) {
        this.imgURL = imgURL;
        this.seq = seq;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
