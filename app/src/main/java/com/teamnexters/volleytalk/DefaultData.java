package com.teamnexters.volleytalk;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class DefaultData {
    private String status;
    private String user;
    private String res;
    private String mimetype;

    public DefaultData(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
}
