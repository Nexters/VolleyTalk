package com.teamnexters.volleytalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kakao.auth.Session;

/**
 * Created by MIN on 2017. 8. 11..
 */

public class SplashActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       if (Session.getCurrentSession().isClosed()) {
            intent = new Intent(this, LoginActivity.class);
       } else {
            intent = new Intent(this, MainActivity.class);
       }

       startActivity(intent);

       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       finish();

    }
}
