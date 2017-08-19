package com.teamnexters.volleytalk.follow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.follow.TeamFollow;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 2..
 */

public class TeamFollowAdapter extends BaseAdapter {

    List<TeamFollow> followingTeamList;
    String[] teamNameList;
    String teamName;

    public TeamFollowAdapter() {
    }

    public TeamFollowAdapter(List<TeamFollow> followingTeamList) {
        this.followingTeamList = followingTeamList;
    }

    public List<TeamFollow> getFollowingTeamList() {
        return followingTeamList;
    }

    public void setFollowingTeamList(List<TeamFollow> followingTeamList) {
        this.followingTeamList = followingTeamList;
    }

    @Override
    public int getCount() {
        return followingTeamList.size();
    }

    @Override
    public Object getItem(int position) {
        return followingTeamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int pos = position;
        final Context context = viewGroup.getContext();

        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lv_team_follow, viewGroup, false);
        }

        TextView tv_team_name_follow = (TextView) view.findViewById(R.id.tv_team_name_follow);
        TextView tv_num_like_follow = (TextView) view.findViewById(R.id.tv_num_follower_follow);
        TextView tv_num_post_follow = (TextView) view.findViewById(R.id.tv_num_post_follow);

        TeamFollow selectedItem = (TeamFollow) getItem(pos);

        if ( selectedItem.getTypeseq() < Config.FEMALE_TEAM_START_SEQ ) {
            teamNameList = context.getResources().getStringArray(R.array.team_male_list);
            teamName = teamNameList[selectedItem.getTypeseq() - Config.MALE_TEAM_START_SEQ];
        }  else {
            teamNameList = context.getResources().getStringArray(R.array.team_female_list);
            teamName = teamNameList[selectedItem.getTypeseq() - Config.FEMALE_TEAM_START_SEQ];
        }

        tv_team_name_follow.setText(teamName);
        tv_num_like_follow.setText(String.valueOf(selectedItem.getTeamInfo().getLikecount()));
        tv_num_post_follow.setText(String.valueOf(selectedItem.getTeamInfo().getLikecount()));


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //클릭했을 때 구현 필요
            }
        });

        return view;
    }
}
