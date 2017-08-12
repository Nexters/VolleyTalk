package com.teamnexters.volleytalk.tool;

import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.isAlreadyUser;
import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.news.NewsList;
import com.teamnexters.volleytalk.player.PlayerList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by MIN on 2017. 7. 29..
 */

public interface NetworkModel {

    @GET("news/list")
    Call<NewsList> getNewsList();

    @GET("player/list/{gender}")
    Call<List<PlayerList>> getPlayerList(@Path("gender") String gender);

    @FormUrlEncoded
    @POST("user/login")
    Call<isAlreadyUser> isAlreadySignedUpUser(@Field("userId") long userId,
                                              @Field("nickname") String nickname,
                                              @Field("email") String email,
                                              @Field("profileImg") String profileImg);



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.SERVER_IP)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
