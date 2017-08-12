package com.teamnexters.volleytalk.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by MIN on 2017. 8. 11..
 */

public class DetailPlayerActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailplayer);

        Intent intent = getIntent();
        Player who = (Player) intent.getSerializableExtra("who");

        TextView tv_name_detail_player = (TextView) findViewById(R.id.tv_name_detail_player);
        TextView tv_back_detail_player = (TextView) findViewById(R.id.tv_back_detail_player);
        TextView tv_physical_detail_player = (TextView) findViewById(R.id.tv_physical_detail_player);
        TextView tv_num_heart_detail_player = (TextView) findViewById(R.id.tv_num_heart_detail_player);
        TextView tv_num_feed_detail_player = (TextView) findViewById(R.id.tv_num_feed_detail_player);

        tv_name_detail_player.setText(who.getName());
        tv_back_detail_player.setText("No." + who.getBacknumber());
        tv_physical_detail_player.setText(who.getPhysical());
        tv_num_heart_detail_player.setText(who.getLikecount());
        tv_num_feed_detail_player.setText(who.getPostcount());

    }
}
