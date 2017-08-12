package Team;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.teamnexters.volleytalk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smart on 2017-08-13.
 */


public  class TeamFragment extends Fragment {
    public LinearLayout wall_team;
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();

        //layout inflate

        View rootView = inflater.inflate(R.layout.fragment_team, container, false);
        wall_team = (LinearLayout) rootView.findViewById(R.id.wall_team);
        RecyclerView ro_view = (RecyclerView) rootView.findViewById(R.id.recyview);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        ro_view.setLayoutManager(layoutManager);

        RadioButton rb_male_team = (RadioButton) ro_view.findViewById(R.id.rb_male_team);
        RadioButton rb_female_team = (RadioButton) ro_view.findViewById(R.id.rb_female_team);

   //     rb_male_team.setOnClickListener(clickSegmentedControl);
     //   rb_female_team.setOnClickListener(clickSegmentedControl);


        List<Team_man> teamList = new ArrayList<>();
        teamList.add(new Team_man("http://postfiles9.naver.net/MjAxNzA4MTNfMjg1/MDAxNTAyNTYwMDI3Nzc3.Tq4E2lxC_YdBUC6Muxv0UBJvep6Rpm0zDz2crolx_7kg.kQfsDLLezRKfHjMFyjMjnnpbinzYdZfwmMQxp7B0MvAg.PNG.shypang/img_team_bluefans.png?type=w966", 1));
        teamList.add(new Team_man("http://postfiles6.naver.net/MjAxNzA4MTNfNzIg/MDAxNTAyNTYwMDI3Nzgw.tJC2T1LWA1yIPcjW-3Vofo6hlqsQQ3GJ4ySukiejTRwg.bVkkVLZNMT0KB2jMw5yroyBIobAyEj1EQbCN4Rdc3Nog.JPEG.shypang/img_team_jumbos.jpg?type=w966", 2));
        teamList.add(new Team_man("http://postfiles7.naver.net/MjAxNzA4MTNfMjU1/MDAxNTAyNTYwMDI3Nzgy.Pic71ci6YItLSodfq5skU6Whwqlj35VefzQjeSV0Racg.Hgk1SEbEpKSqGLYMPwAOQO4cOysouSBbKy44sC51Hj8g.PNG.shypang/img_team_jumbos.png?type=w966", 3));
        teamList.add(new Team_man("http://postfiles16.naver.net/MjAxNzA4MTNfMjI3/MDAxNTAyNTYwMDI3Nzg0.-wto9w3kPd-Aygxu8K3IfwspvmLkv1Z-Lnq9f-3ECXEg.o4v58nqUKDkcX6Tiep1QqoR0-hpI2k5GzSG1s-kh7dEg.PNG.shypang/img_team_rushcash.png?type=w966", 4));
        teamList.add(new Team_man("http://postfiles9.naver.net/MjAxNzA4MTNfMzgg/MDAxNTAyNTYwMDI3Nzgz.BkN3CMiSqK60FsVFWk2kX8SZiOqqRXAo2_J1sd_EnQsg.mJTjjBlrJDhDX8L4u0xBK6J5QhbSzwlmxVi1AxqA9e0g.PNG.shypang/img_team_skywakers.png?type=w966", 5));
        teamList.add(new Team_man("http://postfiles3.naver.net/MjAxNzA4MTNfMTc1/MDAxNTAyNTYwMDI3Nzg0.tJbMldELRnVDxmUfPxBjMbdchK6aKUuPfiB0GQZfphEg.FbQcl5LI3PlicWX9rOzBp241DDG1TWsTn4oOlB_cu-og.PNG.shypang/img_team_stars.png?type=w966", 6));
        teamList.add(new Team_man("http://postfiles1.naver.net/MjAxNzA4MTNfNzYg/MDAxNTAyNTYwMDI3ODU3.BSy6keCl4foLsc80Sq01rpgWm646gOY3aMLojdDTnQQg.EMMg69eriI56NCfTeZIEmjBHN9GkLil7NU_hxorHLCgg.PNG.shypang/img_team_vixtorm.png?type=w966", 7));
        teamList.add(new Team_man("http://postfiles7.naver.net/MjAxNzA4MTNfMjI3/MDAxNTAyNTYwMDI3ODY1.dW_GEweUgOoCZ_3Ngq1Q-TK5ha5dWLRZOscOMq9GAU8g.A9auyr52gcvUhuGsyivpY8gowVoMhiSOmWYts-3PcV8g.PNG.shypang/img_team_wibee.png?type=w966", 8));

        TeamAdapter teamAdapter = new TeamAdapter(teamList, context);
        ro_view.setAdapter(teamAdapter);


        return rootView;
    }



    /* View.OnClickListener clickSegmentedControl = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

        }
    };*/


}

/*
 팀상태로 나눠서 진행 하는거

 View.OnClickListener clickSegmentedControl = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            wall_team.removeAllViews();

            switch (view.getId()) {
                case R.id.rb_male_team:
                   // teamNameList = getResources().getStringArray(R.array.team_male_list);
                  //  teamColorList = getResources().getIntArray(R.array.team_male_color_list);
                  //  getplayerList("M");
                    break;
                case R.id.rb_female_team:
                 //   teamNameList = getResources().getStringArray(R.array.team_female_list);
                //    teamColorList = getResources().getIntArray(R.array.team_female_color_list);
                //    getplayerList("F");
                    break;
            }
        }
    };*/


