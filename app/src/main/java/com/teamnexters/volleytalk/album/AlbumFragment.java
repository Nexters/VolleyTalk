package com.teamnexters.volleytalk.album;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.post.Post;
import com.teamnexters.volleytalk.tool.NetworkModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class AlbumFragment extends Fragment {

    private Context context;
    private AlbumAdapter albumAdapter;
    private List<Post> postImgList;

    public static AlbumFragment newInstance(String type) {
        AlbumFragment instance = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString("Type", type);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();

        View rootView_album = inflater.inflate(R.layout.fragment_album, container, false);
//        RecyclerView rl_album = (RecyclerView) rootView_album.findViewById(R.id.rv_album);
//
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
//        rl_album.setLayoutManager(layoutManager);
//
//        //나중에 Resource -> dimen으로 수정
//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(7);
//        rl_album.addItemDecoration(itemDecoration);
//
//        //리스트 만들어서 적당히 테스트 데이터 집어넣기.
//        postImgList = new ArrayList<>();
//
//
//        albumAdapter = new AlbumAdapter(postImgList, context);
//        rl_album.setAdapter(albumAdapter);
//
//        getPostImgList();

        return rootView_album;
    }

    private void getPostImgList() {
        Bundle args = getArguments();
        String type = args.getString("Type");

        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<List<Post>>> call = networkModel.getPostThumbnailImageList(type, 1, 20);
        call.enqueue(new Callback<ResForm<List<Post>>>() {
            @Override
            public void onResponse(Call<ResForm<List<Post>>> call, Response<ResForm<List<Post>>> response) {
                ResForm<List<Post>> result = response.body();
                Log.e("TEST", result.getStatus());
                if ( result.getStatus().equals("true")) {
                    Log.e("TEST", "size " + result.getResData().size());
                    postImgList.addAll(result.getResData());
                    albumAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResForm<List<Post>>> call, Throwable t) {

            }
        });
    }


    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }


}
