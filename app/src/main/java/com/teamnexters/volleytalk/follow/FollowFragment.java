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

        //if 서버에서 follow 정보 받아와서 size 0 일 경우
        // mipmap.img_like_none 화면 중앙에 넣을 것
        // 이 때 배경 색은 #fdfdfd인듯

        // 팀, 선수, 유저는 밑에서 처리

        NonScrollListView lv_team_follow = (NonScrollListView) rootView_follow.findViewById(R.id.lv_team_follow);

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
