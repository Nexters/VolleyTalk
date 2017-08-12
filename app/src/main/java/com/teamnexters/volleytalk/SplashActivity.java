package com.teamnexters.volleytalk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        /*
        if ( Build.VERSION.SDK_INT >= 23 ) {
            int hasInternetPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

            if ( hasInternetPermission != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {

                }
            }
        }
        */


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
