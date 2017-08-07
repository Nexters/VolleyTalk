package com.teamnexters.volleytalk.follow.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.follow.TeamFollow;
import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.ui_pages.DetailNewsActivity;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 2..
 */

public class TeamFollowAdapter extends BaseAdapter {

    List<TeamFollow> followingTeamList;

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
            view = inflater.inflate(R.layout.item_lv_follow, viewGroup, false);
        }

        TextView tv_team_name_follow = (TextView) view.findViewById(R.id.tv_team_name_follow);
        TextView tv_num_like_follow = (TextView) view.findViewById(R.id.tv_num_like_follow);
        TextView tv_num_post_follow = (TextView) view.findViewById(R.id.tv_num_post_follow);

        TeamFollow selectedItem = (TeamFollow) getItem(pos);


        tv_team_name_follow.setText(Html.fromHtml(selectedItem.getName()).toString());
        tv_num_like_follow.setText(Html.fromHtml(selectedItem.getLike()).toString());
        tv_num_post_follow.setText(selectedItem.getPost());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //클릭했을 때 구현 필요
            }
        });

        return view;
    }
}
