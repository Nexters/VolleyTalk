package com.teamnexters.volleytalk.ui_pages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by MIN on 2017. 7. 13..
 */

public class NewsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_news = inflater.inflate(R.layout.fragment_news, container, false);


        //photo news
        ViewPager vp_mainImage_news = (ViewPager) rootView_news.findViewById(R.id.vp_mainImage_news);
        CircleIndicator indicator_mainImage_news = (CircleIndicator) rootView_news.findViewById(R.id.indicator_mainImage_news);
        PhotoNewsAdapter photoNewsAdapter = new PhotoNewsAdapter(getContext());

        vp_mainImage_news.setAdapter(photoNewsAdapter);
        indicator_mainImage_news.setViewPager(vp_mainImage_news);


        //days match
        ListView lv_days_match_news = (ListView) rootView_news.findViewById(R.id.lv_days_match_news);
        ArrayList<TodayMatchItem> testList = new ArrayList<>();
            testList.add(new TodayMatchItem("현대건설", "2", "KGC인삼공사", "1", "17:00"));
            testList.add(new TodayMatchItem("한국전력", "5", "삼성화재", "4", "20:00"));
        TodayMatchAdapter adapter_days_match = new TodayMatchAdapter(testList);
        lv_days_match_news.setAdapter(adapter_days_match);


       // RelativeLayout bg_item_days_match = (RelativeLayout) lv_days_match_news.getChildAt(lv_days_match_news.getLastVisiblePosition()).findViewById(R.id.bg_item_days_match);
       // bg_item_days_match.setBackground(getResources().getDrawable(R.drawable.lv_days_match_footer_bg));

        return rootView_news;
    }

    private class PhotoNewsAdapter extends PagerAdapter {

        private final int SIZE_IMAGENEWS = 4;
        private LayoutInflater inflater;
        private Context context;

        public PhotoNewsAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return SIZE_IMAGENEWS;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override public View instantiateItem(ViewGroup container, int position) {

            View view = inflater.inflate(R.layout.slider_photo_news, container, false);
            ImageView iv_main_news = (ImageView) view.findViewById(R.id.iv_main_news);
            TextView tv_source_main_news = (TextView) view.findViewById(R.id.tv_source_main_news);
            TextView tv_title_main_news = (TextView) view.findViewById(R.id.tv_title_main_news);

            Glide.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into(iv_main_news);

            tv_title_main_news.setText(String.valueOf(position));

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    public class TodayMatchAdapter extends BaseAdapter {

        private ArrayList<TodayMatchItem> todayMatchItemsList;

        public TodayMatchAdapter(ArrayList<TodayMatchItem> todayMatchItemsList) {
            this.todayMatchItemsList = todayMatchItemsList;
        }

        @Override
        public int getCount() {
            return todayMatchItemsList.size();
        }

        @Override
        public Object getItem(int position) {
            return todayMatchItemsList.get(position);
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
                view = inflater.inflate(R.layout.item_lv_daysmatch, viewGroup, false);
            }

            TextView tv_timeInfo_match = (TextView) view.findViewById(R.id.tv_timeInfo_match);
            TextView tv_teamA_score = (TextView) view.findViewById(R.id.tv_teamA_score);
            TextView tv_teamA_name = (TextView) view.findViewById(R.id.tv_teamA_name);
            TextView tv_teamB_score = (TextView) view.findViewById(R.id.tv_teamB_score);
            TextView tv_teamB_name = (TextView) view.findViewById(R.id.tv_teamB_name);

            TodayMatchItem selectedItem = (TodayMatchItem)getItem(pos);

            tv_timeInfo_match.setText(selectedItem.getTime());
            tv_teamA_score.setText(selectedItem.getTeamA_score());
            tv_teamA_name.setText(selectedItem.getTeamA_name());
            tv_teamB_score.setText(selectedItem.getTeamB_score());
            tv_teamB_name.setText(selectedItem.getTeamB_name());

            return view;
        }
    }

    public class TodayMatchItem {
        private String TeamA_name;
        private String TeamA_score;
        private String TeamB_name;
        private String TeamB_score;
        private String time;

        public TodayMatchItem(String teamA_name, String teamA_score, String teamB_name, String teamB_score, String time) {
            TeamA_name = teamA_name;
            TeamA_score = teamA_score;
            TeamB_name = teamB_name;
            TeamB_score = teamB_score;
            this.time = time;
        }

        public String getTeamA_name() {
            return TeamA_name;
        }

        public void setTeamA_name(String teamA_name) {
            TeamA_name = teamA_name;
        }

        public String getTeamA_score() {
            return TeamA_score;
        }

        public void setTeamA_score(String teamA_score) {
            TeamA_score = teamA_score;
        }

        public String getTeamB_name() {
            return TeamB_name;
        }

        public void setTeamB_name(String teamB_name) {
            TeamB_name = teamB_name;
        }

        public String getTeamB_score() {
            return TeamB_score;
        }

        public void setTeamB_score(String teamB_score) {
            TeamB_score = teamB_score;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }



}
