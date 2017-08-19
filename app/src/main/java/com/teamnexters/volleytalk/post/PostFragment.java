package com.teamnexters.volleytalk.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.album.AlbumFragment;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.tool.NetworkModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 2..
 */

public class PostFragment extends Fragment {

    private int postStart;
    private List<Post> postList;
    private PostAdapter adapter_allpost;

    public static PostFragment newInstance(String type, String seq) {
        PostFragment instance = new PostFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("seq", seq);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_allpost = inflater.inflate(R.layout.fragment_allpost, container, false);

        ListView lv_allpost_mypage = (ListView) rootView_allpost.findViewById(R.id.lv_allpost_mypage);
        lv_allpost_mypage.setNestedScrollingEnabled(true);

        postList = new ArrayList<>();
        adapter_allpost = new PostAdapter(getContext(), postList);
        lv_allpost_mypage.setAdapter(adapter_allpost);

        lv_allpost_mypage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), PostContentActivity.class);
                intent.putExtra("post", (Post) adapterView.getItemAtPosition(i));
                startActivity(intent);
            }
        });

        return rootView_allpost;
    }

    @Override
    public void onStart() {
        super.onStart();
        postStart = 1;
        getPostList();
    }

    public void getPostList() {
        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Bundle args = getArguments();
        String type = args.getString("type");
        String seq = args.getString("seq");

        //현재 team밖에 안 됨... type값으로 나중에 수정
        Call<ResForm<List<Post>>> call = networkModel.getPostList("team", seq,  1, 20);
        call.enqueue(new Callback<ResForm<List<Post>>>() {
            @Override
            public void onResponse(Call<ResForm<List<Post>>> call, Response<ResForm<List<Post>>> response) {
                ResForm<List<Post>> result = response.body();
                if(result.getStatus().equals("true")) {
                    //addAll로 하면 계속 추가됨.....
                    postList.addAll(result.getResData());
                    adapter_allpost.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), result.getErrMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResForm<List<Post>>> call, Throwable t) {

            }
        });
    }
}
