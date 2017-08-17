package com.teamnexters.volleytalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

        Call<ResForm<DefaultData>> call = networkModel.isAlreadySignedUpUser(String.valueOf(userProfile.getId()), userProfile.getNickname(), userProfile.getEmail(), userProfile.getProfileImagePath());
        call.enqueue(new Callback<ResForm<DefaultData>>() {
            @Override
            public void onResponse(Call<ResForm<DefaultData>> call, Response<ResForm<DefaultData>> response) {
                if(response.body() != null ) {
                    ResForm<DefaultData> result = response.body();

                    if(result.getStatus().equals("true")) {
                        if(result.getResData().getStatus().equals("exist")) {
                            redirectMainActivity();
                        } else {
                            redirectSetNicknameActivity();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), result.getErrMsg(), Toast.LENGTH_LONG).show();
                        Session.getCurrentSession().close();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<DefaultData>> call, Throwable t) {

            }
        });
    }

    protected void redirectMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    protected void redirectSetNicknameActivity() {
        Intent intent = new Intent(this, SetNewPropertyActivity.class);
        startActivity(intent);
        finish();
    }

}