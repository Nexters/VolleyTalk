package com.teamnexters.volleytalk.news;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.news.adapter.PhotoNewsAdapter;
import com.teamnexters.volleytalk.news.adapter.TextNewsAdapter;
import com.teamnexters.volleytalk.news.adapter.ThumbnailNewsAdapter;
import com.teamnexters.volleytalk.news.adapter.TodayMatchAdapter;
import com.teamnexters.volleytalk.ui_element.NonScrollListView;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import me.relex.circleindicator.CircleIndicator;



/**
 * Created by MIN on 2017. 7. 13..
 */

public class NewsFragment extends Fragment implements NewsContract.View{

    private NewsPresenter mPresenter;

    private ViewPager vp_photo_news;
    private CircleIndicator indicator_photo_news;
    public PhotoNewsAdapter adapter_photo_news;

    private NonScrollListView lv_days_match_news;
    public TodayMatchAdapter adapter_days_match_news;

    private NonScrollListView lv_thumbnail_news;
    private ThumbnailNewsAdapter adapter_thumbnail_news;

    private NonScrollListView lv_text_news;
    private TextNewsAdapter adapter_text_news;

    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_news = inflater.inflate(R.layout.fragment_news, container, false);

        //photo news
        vp_photo_news = (ViewPager) rootView_news.findViewById(R.id.vp_photo_news);
        indicator_photo_news = (CircleIndicator) rootView_news.findViewById(R.id.indicator_photo_news);
        adapter_photo_news = new PhotoNewsAdapter(getContext());


        //days match
        lv_days_match_news = (NonScrollListView) rootView_news.findViewById(R.id.lv_days_match_news);
        ArrayList<TodayMatch> testList = new ArrayList<>();
            testList.add(new TodayMatch("현대건설", "2", "KGC인삼공사", "1", "17:00"));
            testList.add(new TodayMatch("한국전력", "5", "삼성화재", "4", "20:00"));
        adapter_days_match_news = new TodayMatchAdapter(testList);
        lv_days_match_news.setAdapter(adapter_days_match_news);


        //thumbnail news
        lv_thumbnail_news = (NonScrollListView) rootView_news.findViewById(R.id.lv_thumbnail_news);
        adapter_thumbnail_news = new ThumbnailNewsAdapter();

        //text news
        lv_text_news = (NonScrollListView) rootView_news.findViewById(R.id.lv_text_news);
        adapter_text_news = new TextNewsAdapter();

        dialog = new SpotsDialog(getContext());
        dialog.show();

        mPresenter = new NewsPresenter(this);
        mPresenter.getNewsList();

        return rootView_news;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(@NonNull NewsContract.Presenter presenter) {

    }

    @Override
    public void setDataOnAdapter(NewsList newsList) {
        adapter_photo_news.setPhotoNewsList(newsList.getNewsList().subList(0, Config.PHOTO_NEWS_SIZE));
        vp_photo_news.setAdapter(adapter_photo_news);
        indicator_photo_news.setViewPager(vp_photo_news);

        adapter_thumbnail_news.setThumbnailNewsItemList(newsList.getNewsList().subList(Config.PHOTO_NEWS_SIZE, Config.PHOTO_NEWS_SIZE * 2));
        lv_thumbnail_news.setAdapter(adapter_thumbnail_news);

        adapter_text_news.setTextNewsItemList(newsList.getNewsList().subList(Config.PHOTO_NEWS_SIZE * 2, newsList.getNewsList().size()));
        lv_text_news.setAdapter(adapter_text_news);

        dialog.dismiss();
    }
}
