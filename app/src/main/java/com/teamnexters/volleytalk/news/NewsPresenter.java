package com.teamnexters.volleytalk.news;

import android.app.Application;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.tool.ApplicationBase;
import com.teamnexters.volleytalk.tool.NetworkModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 7. 29..
 */

public class NewsPresenter implements NewsContract.Presenter{

    private final NewsContract.View newsView;

    public NewsPresenter(@NonNull NewsContract.View newsView) {
        this.newsView = newsView;
        newsView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getNewsList() {
        final NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<NewsList>> call = networkModel.getNewsList();
        call.enqueue(new Callback<ResForm<NewsList>>() {
            @Override
            public void onResponse(Call<ResForm<NewsList>> call, Response<ResForm<NewsList>> response) {
                ResForm<NewsList> newsList = response.body();
                if(newsList.getStatus().equals("true")) {
                    newsView.setDataOnAdapter(newsList.getResData().getItems());
                } else {
                    Log.e("TEST", newsList.getErrMsg());
                }
            }

            @Override
            public void onFailure(Call<ResForm<NewsList>> call, Throwable t) {

            }
        });
    }
}
