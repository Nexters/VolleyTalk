package com.teamnexters.volleytalk.tool;

import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.news.NewsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by MIN on 2017. 7. 29..
 */

public interface NetworkModel {

    @GET("news/list")
    Call<List<News>> getNewsList();

    @GET("news/list")
    Call<NewsList> getNews();


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.SERVER_IP)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
