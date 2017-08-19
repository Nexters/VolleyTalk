package com.teamnexters.volleytalk.post;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class PostContentActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_postcontent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_postcontent);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("post");


        ImageView iv_back_postcontent = (ImageView) findViewById(R.id.iv_back_postcontent);
        iv_back_postcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tv_title_postcontent = (TextView) findViewById(R.id.tv_title_postcontent);
        tv_title_postcontent.setText(post.getTitle());

        TextView tv_writer_date_postcontent = (TextView) findViewById(R.id.tv_writer_date_postcontent);
        tv_writer_date_postcontent.setText(post.getUserid() + "    "  + post.getCreatedAt());

        if (post.getImg_url() != null) {
            ImageView iv_photo_postcontent = (ImageView) findViewById(R.id.iv_photo_postcontent);

            GlideUrl glideUrl = new GlideUrl(Config.SERVER_IP + post.getImg_url(), new LazyHeaders.Builder()
                    .addHeader("Cookie", "userid=" + UserProfile.loadFromCache().getId())
                    .build());

            Glide.with(this)
                    .load(glideUrl)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(iv_photo_postcontent);

            iv_photo_postcontent.setVisibility(View.VISIBLE);
        }

        TextView tv_content_postcontent = (TextView) findViewById(R.id.tv_content_postcontent);
        tv_content_postcontent.setText(post.getContents());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call_112:
                mailToManager();
                break;
            case R.id.action_edit:
                break;
            case R.id.action_delete:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void mailToManager() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:" + getResources().getString(R.string.qna_email)));
        intent.putExtra(Intent.EXTRA_SUBJECT, "[문의] : ");
        try {
            startActivity(Intent.createChooser(intent, "문의 메일을 보냅니다."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "설치된 이메일 어플리케이션이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
