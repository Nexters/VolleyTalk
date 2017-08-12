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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teamnexters.volleytalk.album.AlbumFragment;
import com.teamnexters.volleytalk.post.PostFragment;
import com.teamnexters.volleytalk.setting.SettingFragment;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by MIN on 2017. 8. 2..
 */


// 코드 정리 곧 할게요...
public class MyPageActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private Context context;

    private TabLayout tabLayout;

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

        ImageView iv_setting_mypage = (ImageView) toolbar.findViewById(R.id.iv_setting_mypage);
        iv_setting_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //세팅 페이지 없애고 어떻게?
            }
        });

        ImageView iv_round_profile_mypage = (ImageView) appbarlayout_mypage.findViewById(R.id.iv_round_profile_mypage);
        Glide.with(context)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ")
                .apply(RequestOptions.circleCropTransform())
                .into(iv_round_profile_mypage);


        FloatingActionButton fab_allpost = (FloatingActionButton) findViewById(R.id.fab_allpost);
        fab_allpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WriteActivity.class);
                startActivity(intent);
            }
        });

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
                    fragment = Fragment.instantiate(context, PostFragment.class.getName());
                    break;
                case 1:
                    fragment = Fragment.instantiate(context, AlbumFragment.class.getName());
                    break;
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
}

