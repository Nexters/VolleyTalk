package com.teamnexters.volleytalk.follow.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.player.Player;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 19..
 */

public class PlayerFollowExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private SparseArray<String> parentArray;
    private SparseArray<List<Player>> childArray;


    public PlayerFollowExpandableAdapter(Context context, SparseArray parentArray, SparseArray childArray) {
        this.context = context;
        this.parentArray = parentArray;
        this.childArray = childArray;
    }

    public void setParentArray(SparseArray<String> parentArray) {
        this.parentArray = parentArray;
    }

    public void setChildArray(SparseArray<List<Player>> childArray) {
        this.childArray = childArray;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentArray.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parentArray.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_elv_parent, viewGroup, false);
        }

        TextView tv_teamname_including_following_player_follow = (TextView) view.findViewById(R.id.tv_teamname_including_following_player_follow);
        tv_teamname_including_following_player_follow.setText(parentArray.get(parentArray.keyAt(i)));

        return view;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(parentArray.keyAt(groupPosition)).get(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(parentArray.keyAt(groupPosition)).size();
    }

    @Override
    public long getChildId(int gropPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int grounPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {

        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lv_player, viewGroup, false);
        }

        Player selectedPlayer = (Player) getChild(grounPosition, childPosition);

        ImageView iv_star_player = (ImageView) view.findViewById(R.id.iv_star_player);
        TextView tv_back_num_player = (TextView) view.findViewById(R.id.tv_back_num_player);
        TextView tv_name_player = (TextView) view.findViewById(R.id.tv_name_player);
        TextView tv_position_player = (TextView) view.findViewById(R.id.tv_position_player);
        TextView tv_num_like_player = (TextView) view.findViewById(R.id.tv_num_like_player);
        TextView tv_num_post_player = (TextView) view.findViewById(R.id.tv_num_post_player);


        Glide.with(context)
                .load(R.mipmap.ico_star_on)
                .into(iv_star_player);

        tv_back_num_player.setText("No." + selectedPlayer.getBacknumber());
        //tv_name_player.setText(selectedPlayer.getPlayerInfo().getName());
        tv_position_player.setText(selectedPlayer.getPosition());
        tv_num_like_player.setText(selectedPlayer.getLikecount());
        tv_num_post_player.setText(selectedPlayer.getPostcount());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
