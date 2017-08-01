package com.teamnexters.volleytalk.follow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.follow.adapter.TeamFollowAdapter;
import com.teamnexters.volleytalk.ui_element.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 7. 13..
 */

public class FollowFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_follow = inflater.inflate(R.layout.fragment_follow, container, false);

        NonScrollListView lv_team_follow = (NonScrollListView) rootView_follow.findViewById(R.id.lv_team_follow);

            //header test  --> 안 예쁘게 나와서 수정 중..
            /*
            View header_lv_team_follow = inflater.inflate(R.layout.header_lv_follow, null, false);
            TextView tv_title_for_lv_follow = (TextView) header_lv_team_follow.findViewById(R.id.tv_title_for_lv_follow);
            tv_title_for_lv_follow.setText("TEAM");
            lv_team_follow.addHeaderView(header_lv_team_follow);
            */

            //test list
            List<TeamFollow> testlist = new ArrayList<>();
            testlist.add(new TeamFollow("현대캐피탈  SKY WALKERS", "9999+", "1799"));
            testlist.add(new TeamFollow("대한항공 JUMBOS", "9999+", "1799"));
            testlist.add(new TeamFollow("한국전력 VIXTORM", "9999+", "1799"));
        TeamFollowAdapter adapter_team_follow = new TeamFollowAdapter(testlist);
        lv_team_follow.setAdapter(adapter_team_follow);

        return rootView_follow;
    }
}
