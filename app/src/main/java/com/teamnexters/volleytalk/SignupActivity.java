package com.teamnexters.volleytalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import java.util.Map;

/**
 * Created by MIN on 2017. 8. 1..
 */


/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 */
public class SignupActivity extends AppCompatActivity {

    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Test", "onCreate _Signup");
        requestMe();
    }

    protected void showSignup() {
        //여기서 닉네임 팀 설정하기
        setContentView(R.layout.layout_usermgmt_signup);
        Button signupButton = (Button) findViewById(R.id.buttonSignup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                requestSignUp(null);
            }
        });
    }

    private void requestSignUp(final Map<String, String> properties) {
        UserManagement.requestSignup(new ApiResponseCallback<Long>() {
            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(Long result) {
                requestMe();
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                final String message = "UsermgmtResponseCallback : failure : " + errorResult;
                com.kakao.util.helper.log.Logger.w(message);
                //KakaoToast.makeToast(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }
        }, properties);
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() { //유저의 정보를 받아오는 함수
        Log.e("Test", "requestMe");
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("Test", "onSessionClosed");
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                Log.e("Test", "onNotSignedUp");
                showSignup();
            } // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e("Test", "onSuccess");
                //성공 시 userProfile 형태로 반환
                Logger.e("UserProfile : " + userProfile);
                redirectMainActivity(); // 로그인 성공시 MainActivity로
            }
        });
    }

    private void redirectMainActivity() {
        Log.e("Test", "redirectMainActivity");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    protected void redirectLoginActivity() {
        Log.e("Test", "redirectLoginActivity");
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}
