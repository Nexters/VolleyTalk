package com.teamnexters.volleytalk.follow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.follow.UserFollow;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 20..
 */

public class UserFollowAdapter extends BaseAdapter {

    List<UserFollow> userFollowList;

    public UserFollowAdapter(List<UserFollow> userFollowList) {
        this.userFollowList = userFollowList;
    }

    @Override
    public int getCount() {
        return userFollowList.size();
    }

    @Override
    public Object getItem(int position) {
        return userFollowList.get(position);
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
            view = inflater.inflate(R.layout.item_lv_user_follow, viewGroup, false);
        }

        UserFollow selectedUser = (UserFollow) getItem(pos);

        TextView tv_user_name_follow = (TextView) view.findViewById(R.id.tv_user_name_follow);
        TextView tv_user_description_follow = (TextView) view.findViewById(R.id.tv_user_description_follow);
        TextView tv_num_follower_follow = (TextView) view.findViewById(R.id.tv_num_follower_follow);
        TextView tv_num_post_follow = (TextView) view.findViewById(R.id.tv_num_post_follow);

        tv_user_name_follow.setText(selectedUser.getUserInfo().getNickname());
        tv_num_follower_follow.setText(String.valueOf(selectedUser.getUserInfo().getFollowercount()));
        tv_num_post_follow.setText(String.valueOf(selectedUser.getUserInfo().getPostcount()));

        return view;
    }
}
