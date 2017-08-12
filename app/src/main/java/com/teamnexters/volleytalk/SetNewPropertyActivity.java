package com.teamnexters.volleytalk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teamnexters.volleytalk.news.News;
import com.teamnexters.volleytalk.tool.NetworkModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class SetNewPropertyActivity extends AppCompatActivity {

    private Button btn_go_to_next_page_welcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setnickname);

        // ID 중복체크 후에 저장 버튼 클릭 가능하게
        // 그 이후에 다시 editText 건드리면 저장버튼 클릭 불가능하게
        // 정상적으로 저장한 이후에는 팀 선택화면으로 넘어가기.
        // 팀 선택화면 -> R.layout.activity_setfollowteam
        //

        final EditText et_nickname_welcome = (EditText) findViewById(R.id.et_nickname_welcome);
        Button btn_not_duplicated_welcome = (Button) findViewById(R.id.btn_not_duplicated_welcome);
        btn_go_to_next_page_welcome = (Button) findViewById(R.id.btn_go_to_next_page_welcome);

        //요청보낼 때 쿠키 포함하기
        btn_not_duplicated_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDuplicatedNickname(et_nickname_welcome.getText().toString());
            }
        });

        btn_go_to_next_page_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_setfollowteam);
            }
        });

    }


    public void checkDuplicatedNickname(String nickname) {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<ExistNickname>> call = networkModel.existNickname(nickname);
        call.enqueue(new Callback<ResForm<ExistNickname>>() {
            @Override
            public void onResponse(Call<ResForm<ExistNickname>> call, Response<ResForm<ExistNickname>> response) {
                ResForm<ExistNickname> resList = response.body();
                if (resList.getStatus().equals("true")) {
                    if(resList.getResData().getExist().equals("true")) {
                        Log.e("TEST", resList.getResData().getExist());
                        btn_go_to_next_page_welcome.setClickable(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<ExistNickname>> call, Throwable t) {

            }
        });
    }





}
