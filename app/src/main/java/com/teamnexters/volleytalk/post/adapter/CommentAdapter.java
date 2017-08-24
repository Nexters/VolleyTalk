package com.teamnexters.volleytalk.post.adapter;

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
import com.teamnexters.volleytalk.post.Comment;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 24..
 */

public class CommentAdapter extends BaseAdapter {

    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Comment selectedComment = (Comment) getItem(position);
        Context context = viewGroup.getContext();

        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lv_comment, viewGroup, false);
        }

        ImageView iv_user_thumbnail_comment_post = (ImageView) view.findViewById(R.id.iv_user_thumbnail_comment_post);
        ImageView iv_am_I_like_this_comment = (ImageView) view.findViewById(R.id.iv_am_I_like_this_comment);
        TextView tv_user_nickname_comment_post = (TextView) view.findViewById(R.id.tv_user_nickname_comment_post);
        TextView tv_createdAt_comment_post = (TextView) view.findViewById(R.id.tv_createdAt_comment_post);
        TextView tv_contents_comment_post = (TextView) view.findViewById(R.id.tv_contents_comment_post);

        tv_user_nickname_comment_post.setText(selectedComment.getUserid());
        tv_createdAt_comment_post.setText(selectedComment.getCreatedAt());
        tv_contents_comment_post.setText(selectedComment.getComment());

        if ( selectedComment.getUser() != null ) {
            Glide.with(context)
                    .load(selectedComment.getUser().getProfileimg_thumb())
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_user_thumbnail_comment_post);
        }

        return view;
    }
}
