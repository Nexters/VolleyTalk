package com.teamnexters.volleytalk.news;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.teamnexters.volleytalk.tool.NetworkModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
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

        new AsyncTask<Void, Void, NewsList>() {
            @Override
            protected NewsList doInBackground(Void... voids) {
                NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
                Call<NewsList> call = networkModel.getNews();
                NewsList newsList = null;

                try {
                    newsList = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return newsList;
            }

            @Override
            protected void onPostExecute(NewsList s) {
                super.onPostExecute(s);
                newsView.setDataOnAdapter(s);
            }
        }.execute();
    }
}