package com.teamnexters.volleytalk;

import android.app.Activity;
import android.app.Application;

import com.kakao.auth.KakaoSDK;
import com.tsengvn.typekit.Typekit;

/**
 * Created by MIN on 2017. 8. 1..
 */

public class GlobalApplication extends Application {
    private static volatile GlobalApplication instance = null;
    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());

        // TextView 폰트 설정
        Typekit.getInstance()
                //한글 폰트
                .addNormal(Typekit.createFromAsset(this, "NotoSans-Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "NotoSans-Bold.ttf"))

                //영어 - 숫자 폰트
                .addCustom1(Typekit.createFromAsset(this, "Roboto-Regular.ttf"))
                .addCustom2(Typekit.createFromAsset(this, "Roboto-Bold.ttf"));
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
