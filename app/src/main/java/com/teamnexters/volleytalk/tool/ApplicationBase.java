package com.teamnexters.volleytalk.tool;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by MIN on 2017. 7. 13..
 */

public class ApplicationBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        // TextView 폰트 설정
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NanumSquareOTFLight.otf"))
                .addBold(Typekit.createFromAsset(this, "NanumSquareOTFBold.otf"));
    }
}
