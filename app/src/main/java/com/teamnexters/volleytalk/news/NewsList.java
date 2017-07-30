package com.teamnexters.volleytalk.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MIN on 2017. 7. 30..
 */

public class NewsList {

    private List<News> items;

    public List<News> getNewsList() {
        return items;
    }

    public void setNewsList(List<News> newsList) {
        this.items = newsList;
    }
}
