package com.teamnexters.volleytalk.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teamnexters.volleytalk.R;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 9..
 */

public class PostAdapter extends BaseAdapter {

    private List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // 사진이 있는 포스트와 없는 포스트 구분해서 레이아웃 적용하는 방향으로 수정 필요.
        final int pos = position;
        final Context context = viewGroup.getContext();

        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lv_post_picture, viewGroup, false);
        }

        ImageView iv_post_picture = (ImageView) view.findViewById(R.id.iv_post_picture);
        TextView tv_post_title = (TextView) view.findViewById(R.id.tv_post_title);
        TextView tv_post_foreword = (TextView) view.findViewById(R.id.tv_post_foreword);
        TextView tv_post_date_writer = (TextView) view.findViewById(R.id.tv_post_date_writer);
        TextView tv_num_like_post = (TextView) view.findViewById(R.id.tv_num_like_post);
        TextView tv_num_reply_post = (TextView) view.findViewById(R.id.tv_num_reply_post);

        Post selectedPost = (Post) getItem(pos);


        //테스트
        Glide.with(context)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ")
                .apply(RequestOptions.centerCropTransform())
                .into(iv_post_picture);

        tv_post_title.setText(selectedPost.getTitle());
        tv_post_foreword.setText(selectedPost.getDescription());
        tv_post_date_writer.setText(selectedPost.getDate() + "  " + selectedPost.getWriter());
        tv_num_like_post.setText(String.valueOf(selectedPost.getLike_count())); // 999+ 값이 들어가려면 String으로 변경해야하나?
        tv_num_reply_post.setText(String.valueOf(selectedPost.getReply_count()));

        return view;
    }
}
