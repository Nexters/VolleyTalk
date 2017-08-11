package com.teamnexters.volleytalk.player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.player.Player;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 8..
 */

public class PlayerAdapter extends BaseAdapter {

    List<Player> playerList;

    public PlayerAdapter(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int position) {
        return playerList.get(position);
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
            view = inflater.inflate(R.layout.item_lv_player, viewGroup, false);
        }

        Player selectedPlayer = (Player) getItem(pos);

        ImageView iv_star_player = (ImageView) view.findViewById(R.id.iv_star_player);
        TextView tv_back_num_player = (TextView) view.findViewById(R.id.tv_back_num_player);
        TextView tv_name_player = (TextView) view.findViewById(R.id.tv_name_player);
        TextView tv_position_player = (TextView) view.findViewById(R.id.tv_position_player);
        TextView tv_num_like_player = (TextView) view.findViewById(R.id.tv_num_like_player);
        TextView tv_num_post_player = (TextView) view.findViewById(R.id.tv_num_post_player);

        //like 여부 판단해서 리소스 다르게.
        iv_star_player.setImageResource(R.mipmap.ico_star_on);

        tv_back_num_player.setText("No." + selectedPlayer.getBacknumber());
        tv_name_player.setText(selectedPlayer.getName());

        //API에 포지션 정보 없음
        //tv_position_player.setText();
        tv_num_like_player.setText(selectedPlayer.getLikecount());
        tv_num_post_player.setText(selectedPlayer.getPostcount());

        return view;
    }
}
