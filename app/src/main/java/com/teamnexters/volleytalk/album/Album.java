package com.teamnexters.volleytalk.album;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class Album {
    private String imgURL;
    private int seq;

    public Album(String imgURL, int seq) {
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
