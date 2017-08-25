package com.teamnexters.volleytalk;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kakao.auth.Session;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class SetNewPropertyActivity extends AppCompatActivity {

    private Button btn_go_to_next_page_welcome;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setnickname);

        // ID 중복체크 후에 저장 버튼 클릭 가능하게
        // 그 이후에 다시 editText 건드리면 저장버튼 클릭 불가능하게
        // 정상적으로 저장한 이후에는 팀 선택화면으로 넘어가기.
        // 팀 선택화면 -> R.layout.activity_setfollowteam


        final EditText et_nickname_welcome = (EditText) findViewById(R.id.et_nickname_welcome);
        Button btn_not_duplicated_welcome = (Button) findViewById(R.id.btn_not_duplicated_welcome);
        btn_go_to_next_page_welcome = (Button) findViewById(R.id.btn_go_to_next_page_welcome);

        GradientDrawable btn_background = (GradientDrawable) getResources().getDrawable(R.drawable.rounded_rectangle);
        btn_background.setColor(Color.parseColor("#4990e2"));
        btn_not_duplicated_welcome.setBackground(btn_background);

        btn_background.setColor(Color.parseColor("#ccff8800"));
        btn_go_to_next_page_welcome.setBackground(btn_background);
        btn_go_to_next_page_welcome.setEnabled(false);


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
                updateNickname(et_nickname_welcome.getText().toString());
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
                        Toast.makeText(getApplicationContext(), "이미 사용 중인 닉네임입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show();
                        btn_go_to_next_page_welcome.setEnabled(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<ExistNickname>> call, Throwable t) {
                Session.getCurrentSession().close();
                Toast.makeText(getApplicationContext(), "서버에 이상이 생겼습니다. 다음에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void updateNickname(String nickname) {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<DefaultData>> call = networkModel.updateNickname(nickname);
        call.enqueue(new Callback<ResForm<DefaultData>>() {
            @Override
            public void onResponse(Call<ResForm<DefaultData>> call, Response<ResForm<DefaultData>> response) {
                ResForm<DefaultData> result = response.body();

                if (result.getStatus().equals("true")) {
                    if(result.getResData().getRes().equals("success")) {
                        Toast.makeText(getApplicationContext(), "성공적으로 닉네임이 저장되었습니다", Toast.LENGTH_SHORT).show();
                    }
                    // 아닌 경우에는...?
                }
            }

            @Override
            public void onFailure(Call<ResForm<DefaultData>> call, Throwable t) {

            }
        });
    }

}
