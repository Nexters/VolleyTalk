package com.teamnexters.volleytalk.team.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johyunchol on 2016. 10. 25..
 */

public class TeamDetailModelRetro implements Serializable {

    @SerializedName("status")
    boolean status;

    public List<TeamDetailModel> resData = new ArrayList<>();

    public List<TeamDetailModel> getList() {
        return resData;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
