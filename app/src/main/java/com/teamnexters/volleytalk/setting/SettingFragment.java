package com.teamnexters.volleytalk.setting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.SplashActivity;
import com.teamnexters.volleytalk.tool.NetworkModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class SettingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_setting = inflater.inflate(R.layout.fragment_setting, container, false);

        RelativeLayout item_share_setting = (RelativeLayout) rootView_setting.findViewById(R.id.item_share_setting);
        item_share_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addCategory(Intent.CATEGORY_DEFAULT);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "VolleyTalk\n" +
                        "https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
                Log.e("PACKAGE", getContext().getPackageName());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "공유"));
            }
        });

        RelativeLayout item_logout_setting = (RelativeLayout) rootView_setting.findViewById(R.id.item_logout_setting);
        item_logout_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        //세션 종료 후 앱 재시작
                        Session.getCurrentSession().close();

                        Intent mStartActivity = new Intent(getContext(), SplashActivity.class);
                        int mPendingIntentId = 123456;
                        PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        System.exit(0);

                    }
                });
            }
        });

        RelativeLayout item_qna_setting = (RelativeLayout) rootView_setting.findViewById(R.id.item_qna_setting);
        item_qna_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + getResources().getString(R.string.qna_email))); // or just "mailto:" for blank
                intent.putExtra(Intent.EXTRA_SUBJECT, "[문의] : ");
                try {
                    startActivity(Intent.createChooser(intent, "문의 메일을 보냅니다."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "설치된 이메일 어플리케이션이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RelativeLayout item_dropOut_user_setting = (RelativeLayout) rootView_setting.findViewById(R.id.item_dropOut_user_setting);
        item_dropOut_user_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDropOut();

                //세션 종료 후 앱 재시작
                Session.getCurrentSession().close();

                Intent mStartActivity = new Intent(getContext(), SplashActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        });

        return rootView_setting;
    }


    private void requestDropOut() {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<String>> call =  networkModel.dropOut(String.valueOf(UserProfile.loadFromCache().getId()));

        call.enqueue(new Callback<ResForm<String>>() {
            @Override
            public void onResponse(Call<ResForm<String>> call, Response<ResForm<String>> response) {
                if ( response.body() != null ) {
                    ResForm<String> result = response.body();

                    if ( result.getStatus().equals("true") ) {
                        Log.e("TEST", result.getResData());
                        Toast.makeText(getContext(), "정상적으로 탈퇴되었습니다.", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), result.getErrMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<String>> call, Throwable t) {

            }
        });

    }
}
