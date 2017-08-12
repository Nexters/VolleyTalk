package com.teamnexters.volleytalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 1..
 */


public class LoginActivity extends AppCompatActivity {

    private SessionCallback callback;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {

                } else {

                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }

            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                userProfile.saveUserToCache();
                toSetPageOrMain(userProfile);
            }
        });
    }

    public void toSetPageOrMain(final UserProfile userProfile) {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<isAlreadyUser> call = networkModel.isAlreadySignedUpUser(userProfile.getId(), userProfile.getNickname(), userProfile.getEmail(), userProfile.getProfileImagePath());
        call.enqueue(new Callback<isAlreadyUser>() {
            @Override
            public void onResponse(Call<isAlreadyUser> call, Response<isAlreadyUser> response) {
                if(response.body() != null ) {
                    isAlreadyUser user = response.body();

                    Log.e("USER", user.getStatus());

                    if(user.getStatus().equals("new")) {
                        //나중에 수정.
                        redirectMainActivity();
                    } else if (user.getStatus().equals("exist")) {
                        redirectMainActivity();
                    } else if (user.getStatus().equals("false")) {
                        //status -> false면 망한 거
                    }
                }
            }

            @Override
            public void onFailure(Call<isAlreadyUser> call, Throwable t) {

            }
        });
    }

    protected void redirectMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectSetNicknameActivity() {

    }

}