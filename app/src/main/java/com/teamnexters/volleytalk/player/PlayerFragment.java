package com.teamnexters.volleytalk.player;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.follow.TeamFollow;
import com.teamnexters.volleytalk.follow.adapter.TeamFollowAdapter;
import com.teamnexters.volleytalk.news.NewsList;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.teamnexters.volleytalk.ui_element.FoldableListView;
import com.teamnexters.volleytalk.ui_element.NonScrollListView;
import com.tsengvn.typekit.Typekit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 7. 13..
 */

public class PlayerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_player = inflater.inflate(R.layout.fragment_player, container, false);

        LinearLayout wall_player = (LinearLayout) rootView_player.findViewById(R.id.wall_player);

        /*
        NonScrollListView lv_team_1 = (NonScrollListView) rootView_player.findViewById(R.id.lv_team_player);

        final List<TeamFollow> testlist = new ArrayList<>();
        testlist.add(new TeamFollow("현대캐피탈  SKY WALKERS", "9999+", "1799"));
        testlist.add(new TeamFollow("대한항공 JUMBOS", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));


        final TeamFollowAdapter adapter_team_follow = new TeamFollowAdapter(testlist.subList(0, 5));
        lv_team_1.setAdapter(adapter_team_follow);

        RelativeLayout btn_fold_ONOFF_player = (RelativeLayout) rootView_player.findViewById(R.id.btn_fold_ONOFF_player);
        btn_fold_ONOFF_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_team_follow.setFollowingTeamList(testlist);
                adapter_team_follow.notifyDataSetChanged();
            }
        });
        */

        wall_player.addView(createPlayerList(getContext()));

        return rootView_player;
    }


    private View createPlayerList(Context context) {
        LinearLayout playerList = new LinearLayout(context);
        playerList.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams_list = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int margin_default = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        layoutParams_list.setMargins(margin_default, margin_default * 2, margin_default, 0);
        playerList.setLayoutParams(layoutParams_list);

        int height_header = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams_header = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height_header);
        RelativeLayout lv_header = new RelativeLayout(context);
        lv_header.setLayoutParams(layoutParams_header);
        GradientDrawable header = (GradientDrawable) getResources().getDrawable(R.drawable.lv_header);
        header.setColor(Color.parseColor("#AB2143"));
        lv_header.setBackground(header);

        TextView teamname = new TextView(context);
        teamname.setText("AAAAA");
        teamname.setTextColor(Color.WHITE);
        teamname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        teamname.setTypeface(teamname.getTypeface(), Typeface.NORMAL);
        lv_header.addView(teamname);
        RelativeLayout.LayoutParams layoutParams_teamname = (RelativeLayout.LayoutParams)teamname.getLayoutParams();
        layoutParams_teamname.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        teamname.setLayoutParams(layoutParams_teamname);

        NonScrollListView lv_player = new NonScrollListView(context);
        lv_player.setDivider(getResources().getDrawable(R.drawable.lv_days_match_divider));
        List<TeamFollow> testlist = new ArrayList<>();
        testlist.add(new TeamFollow("현대캐피탈  SKY WALKERS", "9999+", "1799"));
        testlist.add(new TeamFollow("대한항공 JUMBOS", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));


        TeamFollowAdapter adapter_team_follow = new TeamFollowAdapter(testlist);
        lv_player.setAdapter(adapter_team_follow);


        RelativeLayout lv_footer = new RelativeLayout(context);
        lv_footer.setBackground(getResources().getDrawable(R.drawable.lv_footer));
        lv_footer.setLayoutParams(layoutParams_header);

        TextView foldOnOff = new TextView(context);
        foldOnOff.setText("접기");
        foldOnOff.setTextColor(Color.parseColor("#9b9b9b"));
        foldOnOff.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        foldOnOff.setTypeface(foldOnOff.getTypeface(), Typeface.NORMAL);
        lv_footer.addView(foldOnOff);
        RelativeLayout.LayoutParams layoutParams_btn_fold = (RelativeLayout.LayoutParams)foldOnOff.getLayoutParams();
        layoutParams_btn_fold.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        teamname.setLayoutParams(layoutParams_btn_fold);

        playerList.addView(lv_header);
        playerList.addView(lv_player);
        playerList.addView(lv_footer);

        playerList.setElevation(4 * context.getResources().getDisplayMetrics().density);

        return playerList;
    }



}
