package com.teamnexters.volleytalk.follow;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.follow.adapter.PlayerFollowExpandableAdapter;
import com.teamnexters.volleytalk.follow.adapter.TeamFollowAdapter;
import com.teamnexters.volleytalk.follow.adapter.UserFollowAdapter;
import com.teamnexters.volleytalk.player.Player;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.teamnexters.volleytalk.ui_element.NonScrollExpandableListView;
import com.teamnexters.volleytalk.ui_element.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 7. 13..
 */

public class FollowFragment extends Fragment {

    List<TeamFollow> list_team_follow;
    TeamFollowAdapter adapter_team_follow;
    List<UserFollow> list_user_follow;
    UserFollowAdapter adapter_user_follow;
    PlayerFollowExpandableAdapter adapter_player_follow;
    NonScrollExpandableListView elv_player_follow;

    RelativeLayout header_lv_team_follow, header_lv_player_follow, header_lv_user_follow, rl_none_follow;

    SparseArray<String> parentArray;
    SparseArray<List<Player>> childArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_follow = inflater.inflate(R.layout.fragment_follow, container, false);

        header_lv_team_follow = (RelativeLayout) rootView_follow.findViewById(R.id.header_lv_team_follow);
        header_lv_player_follow = (RelativeLayout) rootView_follow.findViewById(R.id.header_lv_player_follow);
        header_lv_user_follow = (RelativeLayout) rootView_follow.findViewById(R.id.header_lv_user_follow);

        rl_none_follow = (RelativeLayout) rootView_follow.findViewById(R.id.rl_none_follow);

        // 팀, 선수, 유저는 밑에서 처리
        NonScrollListView lv_team_follow = (NonScrollListView) rootView_follow.findViewById(R.id.lv_team_follow);

        //list_team_follow.add(new Follow(3, "team", 1, "497708408"));
        list_team_follow = new ArrayList<>();
        adapter_team_follow = new TeamFollowAdapter(list_team_follow);
        lv_team_follow.setAdapter(adapter_team_follow);


        parentArray = new SparseArray<>();
        childArray = new SparseArray<>();
        elv_player_follow = (NonScrollExpandableListView) rootView_follow.findViewById(R.id.elv_player_follow);
        adapter_player_follow = new PlayerFollowExpandableAdapter(getContext(), parentArray, childArray);
        elv_player_follow.setAdapter(adapter_player_follow);

        NonScrollListView lv_following_user_follow = (NonScrollListView) rootView_follow.findViewById(R.id.lv_following_user_follow);
        list_user_follow = new ArrayList<>();
        adapter_user_follow = new UserFollowAdapter(list_user_follow);
        lv_following_user_follow.setAdapter(adapter_user_follow);

        getFollowList();

        return rootView_follow;
    }


    public void getFollowList() {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<FollowList>> call = networkModel.getFollowList();
        call.enqueue(new Callback<ResForm<FollowList>>() {
            @Override
            public void onResponse(Call<ResForm<FollowList>> call, Response<ResForm<FollowList>> response) {
                ResForm<FollowList> result = response.body();
                if (result.getStatus().equals("true")) {
                    setAllListView(result.getResData());
                } else {
                    Toast.makeText(getContext(), result.getErrMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResForm<FollowList>> call, Throwable t) {

            }
        });
    }

    public void openPlayerFollowListView() {
        for(int i = 0; i < parentArray.size(); i++)
            elv_player_follow.expandGroup(i);
    }

    public void setTeamnameInParentArray(int teamSeq) {
        if ( teamSeq < Config.FEMALE_TEAM_START_SEQ ) {
            String[] teamNameList = getResources().getStringArray(R.array.team_male_list);
            parentArray.append(teamSeq, teamNameList[teamSeq - Config.MALE_TEAM_START_SEQ]);
        } else {
            String[] teamNameList = getResources().getStringArray(R.array.team_female_list);
            parentArray.append(teamSeq, teamNameList[teamSeq - Config.FEMALE_TEAM_START_SEQ]);
        }
    }

    public void setAllListView(FollowList list) {
        //set TeamFollow ListView
        if ( list.getTeam().size() > 0 ) {
            header_lv_team_follow.setVisibility(View.VISIBLE);
            list_team_follow.addAll(list.getTeam());
            adapter_team_follow.notifyDataSetChanged();
        } else {
            header_lv_team_follow.setVisibility(View.GONE);
        }

        //set UserFollow ListView
        if ( list.getUser().size() > 0 ) {
            header_lv_user_follow.setVisibility(View.VISIBLE);
            list_user_follow.addAll(list.getUser());
            adapter_user_follow.notifyDataSetChanged();
        } else {
            header_lv_user_follow.setVisibility(View.GONE);
        }

        //set PlayerFollow ListView
        if( list.getPlayer().size() > 0 ) {
            header_lv_player_follow.setVisibility(View.VISIBLE);
            arrangePlayerFollowInfo(list.getPlayer());
            adapter_player_follow.setParentArray(parentArray);
            adapter_player_follow.setChildArray(childArray);
            adapter_player_follow.notifyDataSetChanged();
            openPlayerFollowListView();
        } else {
            header_lv_player_follow.setVisibility(View.GONE);
        }


        if(list.getPlayer().size() == 0 && list.getUser().size()  == 0 && list.getTeam().size() == 0) {
            rl_none_follow.setVisibility(View.VISIBLE);
        } else {
            rl_none_follow.setVisibility(View.GONE);
        }


    }


    public void arrangePlayerFollowInfo(List<PlayerFollow> list_player_follow) {

        for(int i = 0; i < list_player_follow.size(); i++) {
            Player p = list_player_follow.get(i).getPlayerInfo();
            p.setSeq(list_player_follow.get(i).getTypeseq());

            if(parentArray.get(p.getTeamseq()) == null) {
                setTeamnameInParentArray(p.getTeamseq());
                List<Player> playerList = new ArrayList<>();
                childArray.append(p.getTeamseq(), playerList);
            }

            childArray.get(p.getTeamseq()).add(p);
        }
    }

}
