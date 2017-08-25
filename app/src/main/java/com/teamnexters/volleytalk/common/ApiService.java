package com.teamnexters.volleytalk.common;

import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.player.PlayerList;
import com.teamnexters.volleytalk.team.model.TeamDetailModel;
import com.teamnexters.volleytalk.team.model.TeamDetailModelRetro;
import com.teamnexters.volleytalk.team.model.TeamModelRetro;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by johyunchol on 2016. 9. 27..
 */

public interface ApiService {
    public static final String API_URL = "http://13.124.105.110:3000/";

    /*********************************************************************************
     * Team
     ********************************************************************************/
    @GET("team/info")
    Call<ResForm<List<TeamDetailModel>>> getTeamInfo(@Query("gender") String gender,
                                                     @Query("teamSeq") int teamSeq);

    @GET("team/info")
    Call<ResForm<List<PlayerList>>> getPlayerList(@Query("gender") String gender);

    /*********************************************************************************
     * Category
     ********************************************************************************/
    @FormUrlEncoded
    @POST("category/get/main_list")
    Call<TeamModelRetro> getCategoryList(@Field("language") String strLang);

    @FormUrlEncoded
    @POST("category/get/video_list")
    Call<TeamModelRetro> getVideoList(@Field("language") String strLang,
                                      @Field("category_type") String categoryType,
                                      @Field("list_flag") String list_flag);

}
