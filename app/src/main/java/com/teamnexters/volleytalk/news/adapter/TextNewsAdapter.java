package com.teamnexters.volleytalk.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.news.NewsFragment;
import com.teamnexters.volleytalk.ui_pages.DetailNewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 7. 29..
 */

public class TextNewsAdapter extends BaseAdapter {

    private List<News> textNewsItemList;

    public TextNewsAdapter(List<News> textNewsItemList) {
        this.textNewsItemList = textNewsItemList;
    }

    public TextNewsAdapter() {
    }

    public List<News> getTextNewsItemList() {
        return textNewsItemList;
    }

    public void setTextNewsItemList(List<News> textNewsItemList) {
        this.textNewsItemList = textNewsItemList;
    }

    @Override
    public int getCount() {
        return textNewsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return textNewsItemList.get(position);
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
            view = inflater.inflate(R.layout.item_lv_text_news, viewGroup, false);
        }

        TextView tv_title_text_news = (TextView)view.findViewById(R.id.tv_title_text_news);
        TextView tv_source_text_news = (TextView)view.findViewById(R.id.tv_source_text_news);

        final News selectedItem = (News) getItem(pos);

        tv_title_text_news.setText(Html.fromHtml(selectedItem.getTitle()).toString());
        tv_source_text_news.setText(Html.fromHtml(selectedItem.getPubDate()).toString());

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
