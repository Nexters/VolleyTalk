package com.teamnexters.volleytalk.ui_pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnexters.volleytalk.R;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by MIN on 2017. 7. 30..
 */

public class DetailNewsActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailnews);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detailNews);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("link");
        String title = Html.fromHtml(intent.getExtras().getString("title")).toString();

        ImageView iv_back_detailNews = (ImageView) toolbar.findViewById(R.id.iv_back_detailNews);
        iv_back_detailNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tv_title_detailNews = (TextView) toolbar.findViewById(R.id.tv_title_detailNews);
        tv_title_detailNews.setText(title);

        webView = (WebView) findViewById(R.id.wv_detail_news);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }


}

