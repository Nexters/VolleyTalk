package com.teamnexters.volleytalk.AllPost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teamnexters.volleytalk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 8. 2..
 */

public class AllPostFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_allpost = inflater.inflate(R.layout.fragment_allpost, container, false);

        ListView lv_allpost_mypage = (ListView) rootView_allpost.findViewById(R.id.lv_allpost_mypage);
        lv_allpost_mypage.setNestedScrollingEnabled(true);

        //테스트용
        List<Post> testList = new ArrayList<>();
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));
        testList.add(new Post("오늘 경기 꿀잼! 핵잼! 대한항공 짱짱짱!", "오늘 경기 꿀잼이였습니다. 직접관람하신 분들 계시나요?", "17.07.09", "김나나", 999, 13));

        AllPostAdapter adapter_allpost = new AllPostAdapter(testList);
        lv_allpost_mypage.setAdapter(adapter_allpost);

        return rootView_allpost;
    }
}
