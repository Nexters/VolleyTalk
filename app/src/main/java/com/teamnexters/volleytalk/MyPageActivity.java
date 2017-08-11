package com.teamnexters.volleytalk;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
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

import com.teamnexters.volleytalk.AllPost.AllPostFragment;
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
                    fragment = Fragment.instantiate(context, AllPostFragment.class.getName());
                    break;
                case 1:
                    fragment = Fragment.instantiate(context, AllPostFragment.class.getName());
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "전체글";
                case 1:
                    return "앨범";
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

