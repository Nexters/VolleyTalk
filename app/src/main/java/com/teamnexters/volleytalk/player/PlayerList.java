package com.teamnexters.volleytalk.player;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 8..
 */

public class PlayerList {
    private String team;
    private List<Player> player;

    public PlayerList(String team, List<Player> player) {
        this.team = team;
        this.player = player;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }
}
