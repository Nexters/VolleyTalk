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
                //한글 폰트
                .addNormal(Typekit.createFromAsset(this, "NotoSans-Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "NotoSans-Bold.ttf"))

                //영어 - 숫자 폰트
                .addCustom1(Typekit.createFromAsset(this, "Roboto-Regular.ttf"))
                .addCustom2(Typekit.createFromAsset(this, "Roboto-Bold.ttf"));
    }
}
