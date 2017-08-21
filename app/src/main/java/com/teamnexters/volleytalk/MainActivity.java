package com.teamnexters.volleytalk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.TextView;

import com.teamnexters.volleytalk.follow.FollowFragment;
import com.teamnexters.volleytalk.news.NewsFragment;
import com.teamnexters.volleytalk.team.TeamFragment;
import com.teamnexters.volleytalk.team.TeamFragment2;
import com.teamnexters.volleytalk.player.PlayerFragment;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private Context context;

    private TabLayout tabLayout;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ImageView iv_to_my_page = (ImageView) toolbar.findViewById(R.id.iv_to_my_page);
        iv_to_my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        changeTabsFont();
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
                    fragment = Fragment.instantiate(context, NewsFragment.class.getName());
                    break;
                case 1:
                    fragment = Fragment.instantiate(context, TeamFragment.class.getName());
                    break;
                case 2:
                    fragment = Fragment.instantiate(context, PlayerFragment.class.getName());
                    break;
                case 3:
                    fragment = Fragment.instantiate(context, FollowFragment.class.getName());
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "News";
                case 1:
                    return "Team";
                case 2:
                    return "Player";
                case 3:
                    return "Follow";
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
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf"));
                }
            }
        }
    }

}
