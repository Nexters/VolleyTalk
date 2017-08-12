package com.teamnexters.volleytalk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class SetNewPropertyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setnickname);

        // ID 중복체크 후에 저장 버튼 클릭 가능하게
        // 그 이후에 다시 editText 건드리면 저장버튼 클릭 불가능하게
        // 정상적으로 저장한 이후에는 팀 선택화면으로 넘어가기.
        // 팀 선택화면 -> R.layout.activity_setfollowteam
        //
    }


}
