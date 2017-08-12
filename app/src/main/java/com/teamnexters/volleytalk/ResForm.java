package com.teamnexters.volleytalk;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class ResForm<T> {
    private String status;
    private String errMsg;
    private T resData;

    public ResForm(String status, String errMsg) {
        this.status = status;
        this.errMsg = errMsg;
    }

    public ResForm(String status, T resData) {
        this.status = status;
        this.resData = resData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResData() {
        return resData;
    }

    public void setResData(T resData) {
        this.resData = resData;
    }
}
