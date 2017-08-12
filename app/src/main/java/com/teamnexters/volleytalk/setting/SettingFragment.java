package com.teamnexters.volleytalk.setting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.SplashActivity;

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
                        PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        System.exit(0);

                    }
                });
            }
        });

        return rootView_setting;
    }
}
