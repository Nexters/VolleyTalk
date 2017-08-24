package com.teamnexters.volleytalk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.album.AlbumFragment;
import com.teamnexters.volleytalk.post.PostContentActivity;
import com.teamnexters.volleytalk.post.PostFragment;
import com.teamnexters.volleytalk.setting.SettingFragment;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by MIN on 2017. 8. 2..
 */


// 코드 정리 곧 할게요...
public class MyPageActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private Context context;

    private TabLayout tabLayout;

    private ImageView iv_round_profile_mypage;
    private TextView tv_user_name_mypage, tv_num_heart_mypage, tv_num_follower_mypage;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        context = getApplicationContext();

        AppBarLayout appbarlayout_mypage = (AppBarLayout) findViewById(R.id.appbarlayout_mypage);

        final LinearLayout user_content_in_ctl_mypage = (LinearLayout) appbarlayout_mypage.findViewById(R.id.user_content_in_ctl_mypage);

        Toolbar toolbar = (Toolbar) appbarlayout_mypage.findViewById(R.id.toolbar_mypage);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container_mypage);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs_mypage);
        tabLayout.setupWithViewPager(mViewPager);
        changeTabsFont();

        //화면 올릴 경우 user_content_in_ctl_mypage안의 내용 안 보이게.
        appbarlayout_mypage.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state == State.COLLAPSED) {
                    user_content_in_ctl_mypage.setVisibility(View.INVISIBLE);
                } else if (state == State.EXPANDED) {
                    user_content_in_ctl_mypage.setVisibility(View.VISIBLE);
                }
            }
        });

        ImageView iv_back_mypage = (ImageView) toolbar.findViewById(R.id.iv_back_mypage);
        iv_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /*
        ImageView iv_setting_mypage = (ImageView) toolbar.findViewById(R.id.iv_setting_mypage);
        iv_setting_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //세팅 페이지 없애고 어떻게?

            }
        });
        */

        iv_round_profile_mypage = (ImageView) appbarlayout_mypage.findViewById(R.id.iv_round_profile_mypage);
        tv_user_name_mypage = (TextView) appbarlayout_mypage.findViewById(R.id.tv_user_name_mypage);

        tv_num_heart_mypage = (TextView) appbarlayout_mypage.findViewById(R.id.tv_num_heart_mypage);
        tv_num_follower_mypage = (TextView) appbarlayout_mypage.findViewById(R.id.tv_num_follower_mypage);


        /*
        FloatingActionButton fab_allpost = (FloatingActionButton) findViewById(R.id.fab_allpost);
        fab_allpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WriteActivity.class);
                startActivity(intent);
            }
        });
        */

        getMyInfo();

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public Fragment fragment;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            fragment = null;

            switch (position) {
                case 0:
                    //내 포스트 가져오기 생기면 수정하기.
                    return PostFragment.newInstance("player", "1");
                case 1:
                    return AlbumFragment.newInstance("player");
                case 2:
                    fragment = Fragment.instantiate(context, SettingFragment.class.getName());
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "전체글";
                case 1:
                    return "앨범";
                case 2:
                    return "설정";
            }
            return null;
        }
    }

    private void changeTabsFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSans-Regular.ttf"));
                }
            }
        }
    }

    private void getMyInfo() {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<User>> call = networkModel.getUserInfo(String.valueOf(UserProfile.loadFromCache().getId()));
        call.enqueue(new Callback<ResForm<User>>() {
            @Override
            public void onResponse(Call<ResForm<User>> call, Response<ResForm<User>> response) {
                if (response.body() != null) {
                    ResForm<User> result = response.body();

                    if (result.getStatus().equals("true")) {
                        User myInfo = result.getResData();

                        if( myInfo.getProfileimg_thumb() != null ) {
                            Glide.with(context)
                                    .load(myInfo.getProfileimg_thumb())
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(iv_round_profile_mypage);
                        }

                        tv_user_name_mypage.setText(myInfo.getNickname());
                        tv_num_heart_mypage.setText(String.valueOf(myInfo.getLikecount()));
                        tv_num_follower_mypage.setText(String.valueOf(myInfo.getFollowercount()));


                    } else {
                        Toast.makeText(context, result.getErrMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<User>> call, Throwable t) {

            }
        });
    }

}

