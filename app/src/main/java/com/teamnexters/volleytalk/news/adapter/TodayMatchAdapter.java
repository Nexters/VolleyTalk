package com.teamnexters.volleytalk.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.news.TodayMatch;

import java.util.ArrayList;

/**
 * Created by MIN on 2017. 7. 29..
 */

public class TodayMatchAdapter extends BaseAdapter {

    private ArrayList<TodayMatch> todayMatchList;

    public TodayMatchAdapter() {

    }

    public TodayMatchAdapter(ArrayList<TodayMatch> todayMatchItemsList) {
        this.todayMatchList = todayMatchItemsList;
    }

    public ArrayList<TodayMatch> getTodayMatchList() {
        return todayMatchList;
    }

    public void setTodayMatchList(ArrayList<TodayMatch> todayMatchList) {
        this.todayMatchList = todayMatchList;
    }

    @Override
    public int getCount() {
        return todayMatchList.size();
    }

    @Override
    public Object getItem(int position) {
        return todayMatchList.get(position);
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

        TodayMatch selectedItem = (TodayMatch)getItem(pos);

        tv_timeInfo_match.setText(selectedItem.getTime());
        tv_teamA_score.setText(selectedItem.getTeamA_score());
        tv_teamA_name.setText(selectedItem.getTeamA_name());
        tv_teamB_score.setText(selectedItem.getTeamB_score());
        tv_teamB_name.setText(selectedItem.getTeamB_name());

        return view;
    }
}
