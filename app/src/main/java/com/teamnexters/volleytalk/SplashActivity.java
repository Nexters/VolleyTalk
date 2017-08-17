package com.teamnexters.volleytalk;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.kakao.auth.Session;
import com.kakao.usermgmt.response.model.UserProfile;

import java.security.MessageDigest;

/**
 * Created by MIN on 2017. 8. 11..
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        // 퍼미션 관련

        if ( Build.VERSION.SDK_INT >= 23 ) {
            int hasInternetPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

            if ( hasInternetPermission != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {

                }
            }
        }
        */

        Intent intent;

        if (Session.getCurrentSession().isClosed() || UserProfile.loadFromCache().getId() == 0) {
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
