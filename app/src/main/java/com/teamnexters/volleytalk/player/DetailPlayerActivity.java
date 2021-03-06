package com.teamnexters.volleytalk.player;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.WriteActivity;
import com.teamnexters.volleytalk.cheering.Cheering;
import com.teamnexters.volleytalk.cheering.CheeringFragment;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.album.AlbumFragment;
import com.teamnexters.volleytalk.post.PostFragment;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by MIN on 2017. 8. 11..
 */

public class DetailPlayerActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FloatingActionButton fab_player;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private Context context;
    private Player who;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailplayer);

        Intent intent = getIntent();
        who = (Player) intent.getSerializableExtra("who");

        context = getApplicationContext();

        ImageView iv_follow_detail_player = (ImageView) findViewById(R.id.iv_follow_detail_player);
        ImageView iv_heart_detail_player = (ImageView) findViewById(R.id.iv_heart_detail_player);

        TextView tv_name_detail_player = (TextView) findViewById(R.id.tv_name_detail_player);
        TextView tv_back_detail_player = (TextView) findViewById(R.id.tv_back_detail_player);
        TextView tv_physical_detail_player = (TextView) findViewById(R.id.tv_physical_detail_player);
        TextView tv_num_heart_detail_player = (TextView) findViewById(R.id.tv_num_heart_detail_player);
        TextView tv_num_feed_detail_player = (TextView) findViewById(R.id.tv_num_feed_detail_player);

        tv_name_detail_player.setText(who.getName());
        tv_back_detail_player.setText("No." + who.getBacknumber());
        tv_physical_detail_player.setText(who.getPhysical());
        tv_num_heart_detail_player.setText(who.getLikecount());
        tv_num_feed_detail_player.setText(who.getPostcount());

        if (!who.getFollow().isEmpty()) {
            Glide.with(this)
                    .load(R.mipmap.ico_raw_star_on)
                    .into(iv_follow_detail_player);
        }

        Toolbar toolbar_detail_player = (Toolbar) findViewById(R.id.toolbar_detail_player);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container_detail_player);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);

        tabLayout = (TabLayout) findViewById(R.id.tabs_detail_player);
        tabLayout.setupWithViewPager(mViewPager);
        changeTabsFont();

        ImageView iv_back_detail_player = (ImageView) toolbar_detail_player.findViewById(R.id.iv_back_detail_player);
        iv_back_detail_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_follow_detail_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //follow 하기, 취소하기.
            }
        });

        iv_heart_detail_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //like 하기, 취소하기
            }
        });

        fab_player = (FloatingActionButton) findViewById(R.id.fab_detail_player);
        fab_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                intent.putExtra("type", "player");
                intent.putExtra("seq", who.getSeq());
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
                    return PostFragment.newInstance("player", String.valueOf(who.getSeq()));
                case 1:
                    return AlbumFragment.newInstance("player");
                case 2:
                    return CheeringFragment.newInstance(who.getSeq());
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
                    return "응원하기";
            }
            return null;
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                case 1:
                    fab_player.show();
                    break;
                case 2:
                    fab_player.hide();
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




    private void changeTabsFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "NotoSans-Regular.ttf"));
                }
            }
        }
    }
}
