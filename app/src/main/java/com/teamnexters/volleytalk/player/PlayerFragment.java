package com.teamnexters.volleytalk.player;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.player.adapter.PlayerAdapter;
import com.teamnexters.volleytalk.ui_element.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 7. 13..
 */

public class PlayerFragment extends Fragment {

    public TextView foldOnOff;
    public PlayerAdapter adapter_team_player;
    public List<Player> testlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_player = inflater.inflate(R.layout.fragment_player, container, false);
        LinearLayout wall_player = (LinearLayout) rootView_player.findViewById(R.id.wall_player);

        wall_player.addView(createPlayerList(getContext()));

        return rootView_player;
    }


    public View createPlayerList(Context context) {

        LinearLayout playerList = new LinearLayout(context);
        playerList.setOrientation(LinearLayout.VERTICAL);
        playerList.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams_list = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int margin_default = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        layoutParams_list.setMargins(margin_default, margin_default * 2, margin_default, margin_default);
        playerList.setLayoutParams(layoutParams_list);

        //리스트뷰 헤더 추가
        int height_header = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams_header = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height_header);
        RelativeLayout lv_header = new RelativeLayout(context);
        lv_header.setLayoutParams(layoutParams_header);
        GradientDrawable header = (GradientDrawable) getResources().getDrawable(R.drawable.lv_header);
        //팀대표색으로 색상 변경
        header.setColor(Color.parseColor("#AB2143"));
        lv_header.setBackground(header);

        //헤더에 텍스트뷰 추가
        TextView teamname = new TextView(context);
        teamname.setTextColor(Color.WHITE);
        teamname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        teamname.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSans-Regular.ttf"));
        teamname.setText("AAAAA");
        lv_header.addView(teamname);
        RelativeLayout.LayoutParams layoutParams_teamname = (RelativeLayout.LayoutParams)teamname.getLayoutParams();
        layoutParams_teamname.addRule(RelativeLayout.CENTER_IN_PARENT);
        teamname.setLayoutParams(layoutParams_teamname);

        //리스트뷰
        NonScrollListView lv_player = new NonScrollListView(context);
        lv_player.setBackgroundColor(Color.WHITE);
        lv_player.setDivider(getResources().getDrawable(R.drawable.lv_days_match_divider));
        //테스트용 리스트
        testlist = new ArrayList<>();
        testlist.add(new Player("No.10", "대니", "9999+", "1799"));
        testlist.add(new Player("No.09", "박주형", "9999+", "1799"));
        testlist.add(new Player("No.08", "송준호", "9999+", "1799"));
        testlist.add(new Player("No.14", "이시우", "9999+", "1799"));
        testlist.add(new Player("No.13", "한정후", "9999+", "1799"));
        testlist.add(new Player("No.19", "허수봉", "9999+", "1799"));
        testlist.add(new Player("No.15", "문성민", "9999+", "1799"));

        adapter_team_player = new PlayerAdapter(testlist.subList(0, Config.FOLDED_LIST_SIZE));
        lv_player.setAdapter(adapter_team_player);

        View divider = new View(context);
        divider.setBackgroundColor(Color.parseColor("#eeeeee"));
        divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics())));

        //리스트뷰 푸터 추가
        RelativeLayout lv_footer = new RelativeLayout(context);
        lv_footer.setBackground(getResources().getDrawable(R.drawable.lv_footer));
        lv_footer.setLayoutParams(layoutParams_header);
        lv_footer.setOnClickListener(clickFoldListener);

        //푸터에 텍스트뷰 추가
        foldOnOff = new TextView(context);
        foldOnOff.setTextColor(Color.parseColor("#9b9b9b"));
        foldOnOff.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        foldOnOff.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSans-Regular.ttf"));
        foldOnOff.setText("접기");
        lv_footer.addView(foldOnOff);
        RelativeLayout.LayoutParams layoutParams_btn_fold = (RelativeLayout.LayoutParams)foldOnOff.getLayoutParams();
        layoutParams_btn_fold.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        teamname.setLayoutParams(layoutParams_btn_fold);

        playerList.addView(lv_header);
        playerList.addView(lv_player);
        playerList.addView(divider);
        playerList.addView(lv_footer);

        playerList.setClipToOutline(true);
        playerList.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        playerList.setElevation(4 * context.getResources().getDisplayMetrics().density);

        return playerList;
    }

    View.OnClickListener clickFoldListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (adapter_team_player.getCount() == Config.FOLDED_LIST_SIZE) {
                foldOnOff.setText("펼치기");
                adapter_team_player.setPlayerList(testlist);
                adapter_team_player.notifyDataSetChanged();
            } else {
                foldOnOff.setText("접기");
                adapter_team_player.setPlayerList(testlist.subList(0, Config.FOLDED_LIST_SIZE));
                adapter_team_player.notifyDataSetChanged();
            }
        }
    };

}
