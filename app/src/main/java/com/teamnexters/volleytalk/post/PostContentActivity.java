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
import android.widget.Toast;

import com.teamnexters.volleytalk.R;
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
