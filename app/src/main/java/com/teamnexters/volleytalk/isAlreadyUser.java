package com.teamnexters.volleytalk;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class isAlreadyUser {
    private String status;

    public isAlreadyUser(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "isAlreadyUser{" +
                "status='" + status + '\'' +
                '}';
    }
}
