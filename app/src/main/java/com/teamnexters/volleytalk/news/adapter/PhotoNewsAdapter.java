package com.teamnexters.volleytalk.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.ui_pages.DetailNewsActivity;

import java.util.List;

/**
 * Created by MIN on 2017. 7. 29..
 */

public class PhotoNewsAdapter extends PagerAdapter {

    private final int SIZE_PHOTO_NEWS = 3;

    private List<News> photoNewsList;

    private LayoutInflater inflater;
    private Context context;

    public PhotoNewsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<News> getPhotoNewsList() {
        return photoNewsList;
    }

    public void setPhotoNewsList(List<News> photoNewsList) {
        this.photoNewsList = photoNewsList;
    }

    @Override
    public int getCount() {
        return SIZE_PHOTO_NEWS;
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

        final News selectedItem = photoNewsList.get(position);

        Glide.with(context)
                .load(selectedItem.getImgurl())
                .into(iv_main_news);

        tv_title_main_news.setText(Html.fromHtml(selectedItem.getTitle()).toString());
        tv_source_main_news.setText(Html.fromHtml(selectedItem.getPubDate()).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailNewsActivity.class);
                intent.putExtra("title", selectedItem.getTitle());
                intent.putExtra("link", selectedItem.getLink());
                view.getContext().startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
