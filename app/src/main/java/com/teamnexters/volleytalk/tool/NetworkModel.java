package com.teamnexters.volleytalk.tool;

import com.bumptech.glide.request.Request;
import com.teamnexters.volleytalk.ExistNickname;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.User;
import com.teamnexters.volleytalk.cheering.Cheering;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.DefaultData;
import com.teamnexters.volleytalk.follow.Follow;
import com.teamnexters.volleytalk.follow.FollowList;
import com.teamnexters.volleytalk.news.NewsList;
import com.teamnexters.volleytalk.player.Player;
import com.teamnexters.volleytalk.player.PlayerList;
import com.teamnexters.volleytalk.post.Comment;
import com.teamnexters.volleytalk.post.Post;
import com.teamnexters.volleytalk.team.model.TeamDetailList;
import com.teamnexters.volleytalk.team.model.TeamDetailModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    @POST("user/delete")
    Call<ResForm<String>> dropOut(@Query("userid") String userid);

    @GET("user/info")
    Call<ResForm<User>> getUserInfo(@Query("userid") String userid);


    @GET("news/list")
    Call<ResForm<NewsList>> getNewsList();

    //@GET("news/team")


    @GET("player/list")
    Call<ResForm<List<PlayerList>>> getPlayerList(@Query("gender") String gender);

    @GET("player/info")
    Call<ResForm<Player>> getPlayerInfo(@Query("playerseq") int playerseq);


    @GET("post/list")
    Call<ResForm<List<Post>>> getPostList(@Query("type") String type,
                                          @Query("seq") String seq,
                                          @Query("start") int start,
                                          @Query("postCount") int postCount);

    @GET("post/img/list")
    Call<ResForm<List<Post>>> getPostThumbnailImageList(@Query("type") String type,
                                                        @Query("start") int start,
                                                        @Query("imgCount") int imgCount);

    @Multipart
    @POST("post/apply")
    Call<ResForm<DefaultData>> uploadPost(@Part MultipartBody.Part file,
                                          @Part("type") RequestBody type,
                                          @Part("seq") RequestBody seq,
                                          @Part("title") RequestBody title,
                                          @Part("contents") RequestBody contents);


    //@GET("like/list")

    @POST("like/apply")
    Call<ResForm<DefaultData>> applyLike(@Query("likeTypes") String likeTypes,
                                         @Query("likeSeq") int likeSeq);


    @GET("follow/list")
    Call<ResForm<FollowList>> getFollowList();

    @POST("follow/apply")
    Call<ResForm<Follow>> applyFollow(@Query("followTypes") String followTypes,
                                      @Query("followSeq") int followSeq);

    @FormUrlEncoded
    @POST("cheering/apply")
    Call<ResForm<DefaultData>> applyCheering(@Field("playerseq") int playerseq,
                                             @Field("comment") String comment);

    @GET("cheering/list")
    Call<ResForm<List<Cheering>>> getCheeringList(@Query("playerseq") int playerseq);

    @FormUrlEncoded
    @POST("comment/apply")
    Call<ResForm<DefaultData>> applyComment(@Field("type") String type,
                                            @Field("postseq") int postseq,
                                            @Field("comment") String comment);

    @GET("comment/list")
    Call<ResForm<List<Comment>>> getCommentList(@Query("type") String type,
                                                @Query("seq") int seq);

    @GET("img/{file}")
    Call<ResponseBody> getImageFile(@Path("file") String filename);

    /*********************************************************************************
     * Team
     ********************************************************************************/
    @GET("team/list")
    Call<ResForm<List<TeamDetailList>>> getTeamList(@Query("gender") String gender);

    @GET("team/info")
    Call<ResForm<TeamDetailList>> getTeamInfo(@Query("gender") String gender,
                                              @Query("teamSeq") int teamSeq);

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
