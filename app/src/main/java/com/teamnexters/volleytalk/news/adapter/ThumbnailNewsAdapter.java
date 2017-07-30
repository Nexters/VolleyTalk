package com.teamnexters.volleytalk.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.MainActivity;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.tool.ApplicationBase;
import com.teamnexters.volleytalk.ui_pages.DetailNewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 7. 29..
 */

public class ThumbnailNewsAdapter extends BaseAdapter {

    private List<News> thumbnailNewsItemList;

    public ThumbnailNewsAdapter(List<News> thumbnailNewsItemList) {
        this.thumbnailNewsItemList = thumbnailNewsItemList;
    }

    public ThumbnailNewsAdapter() {
    }

    public List<News> getThumbnailNewsItemList() {
        return thumbnailNewsItemList;
    }

    public void setThumbnailNewsItemList(List<News> thumbnailNewsItemList) {
        this.thumbnailNewsItemList = thumbnailNewsItemList;
    }

    @Override
    public int getCount() {
        return thumbnailNewsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return thumbnailNewsItemList.get(position);
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
            view = inflater.inflate(R.layout.item_lv_thumbnail_news, viewGroup, false);
        }

        ImageView iv_thumbnail_news = (ImageView) view.findViewById(R.id.iv_thumbnail_news);
        TextView tv_title_thumbnail_news = (TextView) view.findViewById(R.id.tv_title_thumbnail_news);
        TextView tv_description_thumbnail_news = (TextView) view.findViewById(R.id.tv_description_thumbnail_news);
        TextView tv_source_thumbnail_news = (TextView) view.findViewById(R.id.tv_source_thumbnail_news);

        final News selectedItem = (News) getItem(pos);

        Glide.with(context)
                .load(selectedItem.getImgurl())
                .into(iv_thumbnail_news);

        tv_title_thumbnail_news.setText(Html.fromHtml(selectedItem.getTitle()).toString());
        tv_description_thumbnail_news.setText(Html.fromHtml(selectedItem.getDescription()).toString());
        tv_source_thumbnail_news.setText(selectedItem.getPubDate());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailNewsActivity.class);
                intent.putExtra("title", selectedItem.getTitle());
                intent.putExtra("link", selectedItem.getLink());
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
