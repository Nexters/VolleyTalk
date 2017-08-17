package com.teamnexters.volleytalk.cheering;

/**
 * Created by MIN on 2017. 8. 16..
 */

public class Cheering {
    private String content;
    private String user;
    private String imgURL;
    private String time;

    public Cheering(String content, String user, String imgURL, String time) {
        this.content = content;
        this.user = user;
        this.imgURL = imgURL;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
