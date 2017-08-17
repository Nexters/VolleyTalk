package com.teamnexters.volleytalk.tool;

import com.kakao.usermgmt.response.model.UserProfile;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by MIN on 2017. 8. 16..
 */

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader("Cookie", "userid=" + UserProfile.loadFromCache().getId());
        //builder.addHeader("Cookie", "path=" + "/");

        return chain.proceed(builder.build());
    }
}