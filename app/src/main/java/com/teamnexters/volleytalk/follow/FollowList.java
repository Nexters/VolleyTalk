package com.teamnexters.volleytalk.follow;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 20..
 */

public class FollowList {
    private List<TeamFollow> team;
    private List<PlayerFollow> player;
    private List<UserFollow> user;

    public FollowList(List<TeamFollow> team, List<PlayerFollow> player, List<UserFollow> user) {
        this.team = team;
        this.player = player;
        this.user = user;
    }

    public List<TeamFollow> getTeam() {
        return team;
    }

    public void setTeam(List<TeamFollow> team) {
        this.team = team;
    }

    public List<PlayerFollow> getPlayer() {
        return player;
    }

    public void setPlayer(List<PlayerFollow> player) {
        this.player = player;
    }

    public List<UserFollow> getUser() {
        return user;
    }

    public void setUser(List<UserFollow> user) {
        this.user = user;
    }
}
