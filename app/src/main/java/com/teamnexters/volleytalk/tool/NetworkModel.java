package com.teamnexters.volleytalk.tool;

import com.bumptech.glide.request.Request;
import com.teamnexters.volleytalk.ExistNickname;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.DefaultData;
import com.teamnexters.volleytalk.news.NewsList;
import com.teamnexters.volleytalk.player.PlayerList;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by MIN on 2017. 7. 29..
 */

public interface NetworkModel {

    @GET("user/existNickname")
    Call<ResForm<ExistNickname>> existNickname(@Query("nickname") String nickname);

    @POST("user/updateNickname")
    Call<ResForm<DefaultData>> updateNickname(@Query("nickname") String nickname);

    @POST("user/login")
    Call<ResForm<DefaultData>> isAlreadySignedUpUser(@Query("userid") String userid,
                                                     @Query("nickname") String nickname,
                                                     @Query("email") String email,
                                                     @Query("profileImg") String profileImg);

    //@POST("user/delete")





    @GET("news/list")
    Call<ResForm<NewsList>> getNewsList();

    //@GET("news/team")





    @GET("player/list")
    Call<ResForm<List<PlayerList>>> getPlayerList(@Query("gender") String gender);

    //@GET("player/info")





    //@GET("post/list")

    //@GET("post/img/list")


    /*
    @Multipart
    @POST("post/apply")
    Call<ResForm<DefaultData>> uploadPost(@Part MultipartBody.Part file,
                                          @Part("type") String type,
                                          @Part("seq") String seq,
                                          @Part("title") String title,
                                          @Part("contents") String contents);
    */

    @Multipart
    @POST("post/apply")
    Call<ResForm<DefaultData>> uploadPost(@Part MultipartBody.Part file,
                                          @Part("type") RequestBody type,
                                          @Part("seq") RequestBody seq,
                                          @Part("title") RequestBody title,
                                          @Part("contents") RequestBody contents);



    //@GET("like/list")

    //@POST("like/apply")



    //@GET("follow/list")

    //@POST("follow/apply")


    //@GET("cheering/apply")

    //@POST("cheering/list")


    //@GET("comment/apply")

    //@POST("comment/list")




    AddCookiesInterceptor in1 = new AddCookiesInterceptor();
    OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(in1)
            .build();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.SERVER_IP)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
