package com.teamnexters.volleytalk.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MIN on 2017. 7. 30..
 */

public class NewsList {

    private int start;
    private int display;
    private List<News> items;

    public NewsList(int start, int display, List<News> items) {
        this.start = start;
        this.display = display;
        this.items = items;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<News> getItems() {
        return items;
    }

    public void setItems(List<News> items) {
        this.items = items;
    }
}
