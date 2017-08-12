package com.teamnexters.volleytalk.news;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<NewsList> call = networkModel.getNewsList();
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList newsList = response.body();
                newsView.setDataOnAdapter(newsList);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {

            }
        });
    }
}
