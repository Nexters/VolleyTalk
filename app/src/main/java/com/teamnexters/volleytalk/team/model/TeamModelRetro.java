package com.teamnexters.volleytalk.team.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johyunchol on 2016. 10. 25..
 */

public class TeamModelRetro implements Serializable {

    @SerializedName("message")
    String message;
    @SerializedName("code")
    String code;

    public List<TeamModel> list = new ArrayList<>();

    public List<TeamModel> getList() {
        return list;
    }

    public boolean isSuccessful() {
        return Integer.parseInt(code) == 200;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
