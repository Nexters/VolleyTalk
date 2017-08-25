package com.teamnexters.volleytalk.post;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.DefaultData;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.post.adapter.CommentAdapter;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.teamnexters.volleytalk.ui_element.NonScrollListView;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class PostContentActivity extends AppCompatActivity {

    Post post;
    String type;
    ImageView iv_i_like_this_post;
    List<Comment> commentList;
    CommentAdapter adapter_comment_post;

    EditText et_comment_content;
    Button btn_apply_comment, btn_cancel_comment;
    LinearLayout ll_comment_form;
    TextView tv_num_comment_in_this_post;

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
        post = (Post) intent.getSerializableExtra("post");
        type = intent.getStringExtra("type");

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

        NonScrollListView lv_comment_post = (NonScrollListView) findViewById(R.id.lv_comment_post);
        commentList = new ArrayList<>();
        adapter_comment_post = new CommentAdapter(commentList);
        lv_comment_post.setAdapter(adapter_comment_post);

        tv_num_comment_in_this_post = (TextView) findViewById(R.id.tv_num_comment_in_this_post);
        tv_num_comment_in_this_post.setText(String.valueOf(commentList.size()));

        iv_i_like_this_post = (ImageView) findViewById(R.id.iv_i_like_this_post);

        LinearLayout ll_like_this_post = (LinearLayout) findViewById(R.id.ll_like_this_post);
        LinearLayout ll_add_comment_in_this_post = (LinearLayout) findViewById(R.id.ll_add_comment_in_this_post);
        ll_comment_form = (LinearLayout) findViewById(R.id.ll_comment_form);

        ll_like_this_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeThisPost();
            }
        });

        ll_add_comment_in_this_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_comment_form.setVisibility(View.VISIBLE);
            }
        });

        btn_cancel_comment = (Button) findViewById(R.id.btn_cancel_comment);
        btn_cancel_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_comment_form.setVisibility(View.GONE);
            }
        });

        et_comment_content = (EditText) findViewById(R.id.et_comment_content);

        btn_apply_comment = (Button) findViewById(R.id.btn_apply_comment);
        btn_apply_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyComment(et_comment_content.getText().toString());
            }
        });

        getCommentList();

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

    private void likeThisPost() {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<DefaultData>> call =  networkModel.applyLike("playerpost", post.getSeq());
        call.enqueue(new Callback<ResForm<DefaultData>>() {
            @Override
            public void onResponse(Call<ResForm<DefaultData>> call, Response<ResForm<DefaultData>> response) {
                if ( response.body() != null ) {
                    ResForm<DefaultData> result = response.body();

                    if(result.getStatus().equals("true")) {
                        if (result.getResData() == null)
                            iv_i_like_this_post.setImageResource(R.mipmap.ico_heart_gray);
                        else
                            iv_i_like_this_post.setImageResource(R.mipmap.ico_heart_on);
                    } else {
                        Toast.makeText(getApplicationContext(), result.getErrMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<DefaultData>> call, Throwable t) {

            }
        });
    }

    private void getCommentList() {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<List<Comment>>> call = networkModel.getCommentList(type, post.getSeq());

        call.enqueue(new Callback<ResForm<List<Comment>>>() {
            @Override
            public void onResponse(Call<ResForm<List<Comment>>> call, Response<ResForm<List<Comment>>> response) {
                if ( response.body() != null ) {
                    ResForm<List<Comment>> result = response.body();
                    if (result.getStatus().equals("true")) {
                        commentList.addAll(result.getResData());
                        adapter_comment_post.notifyDataSetChanged();
                        tv_num_comment_in_this_post.setText(String.valueOf(commentList.size()));

                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<List<Comment>>> call, Throwable t) {

            }
        });
    }

    private void applyComment(String comment) {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<DefaultData>> call = networkModel.applyComment(type, post.getSeq(), comment);
        call.enqueue(new Callback<ResForm<DefaultData>>() {
            @Override
            public void onResponse(Call<ResForm<DefaultData>> call, Response<ResForm<DefaultData>> response) {
                if (response.body() != null) {
                    ResForm<DefaultData> result = response.body();

                    if (result.getStatus().equals("true")) {
                        et_comment_content.setText("");
                        ll_comment_form.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<DefaultData>> call, Throwable t) {

            }
        });

    }
}
