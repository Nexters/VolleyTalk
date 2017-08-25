package com.teamnexters.volleytalk.post.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.post.Post;
import com.teamnexters.volleytalk.tool.NetworkModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 9..
 */

public class PostAdapter extends BaseAdapter {

    private static final int ITEM_NO_PIC = 0;
    private static final int ITEM_INCLUDING_PIC = 1;

    private Context context;
    private List<Post> postList;


    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
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
    public int getItemViewType(int position) {
        if (postList.get(position).getImg_url() == null)
            return ITEM_NO_PIC;
        else
            return ITEM_INCLUDING_PIC;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Post selectedPost = (Post) getItem(position);

        Context context = viewGroup.getContext();

        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            switch (getItemViewType(position)) {
                case ITEM_NO_PIC :
                    view = inflater.inflate(R.layout.item_lv_post_no_pic, viewGroup, false);
                    break;
                case ITEM_INCLUDING_PIC :
                    view = inflater.inflate(R.layout.item_lv_post_picture, viewGroup, false);
                    break;
            }
        }

        TextView tv_post_title = (TextView) view.findViewById(R.id.tv_post_title);
        TextView tv_post_foreword = (TextView) view.findViewById(R.id.tv_post_foreword);
        TextView tv_post_date_writer = (TextView) view.findViewById(R.id.tv_post_date_writer);
        TextView tv_num_like_post = (TextView) view.findViewById(R.id.tv_num_like_post);
        TextView tv_num_reply_post = (TextView) view.findViewById(R.id.tv_num_reply_post);


        if (getItemViewType(position) == ITEM_INCLUDING_PIC)  {

            ImageView iv_post_picture = (ImageView) view.findViewById(R.id.iv_post_picture);


            //thumbnail 이미지 오류. 일반 이미지는 정상 작동
            /*
                E/MediaMetadataRetrieverJNI: getFrameAtTime: videoFrame is a NULL pointer
                E/MediaMetadataRetrieverJNI: invalid file descriptor
                E/Glide: class com.bumptech.glide.load.engine.GlideException: Failed to load resource
             */
            GlideUrl glideUrl = new GlideUrl(Config.SERVER_IP + selectedPost.getImg_url(), new LazyHeaders.Builder()
                                .addHeader("Cookie", "userid=" + UserProfile.loadFromCache().getId())
                                .build());

            Glide.with(context)
                    .load(glideUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(iv_post_picture);
        }

        tv_post_title.setText(selectedPost.getTitle());
        tv_post_foreword.setText(selectedPost.getContents());
        tv_post_date_writer.setText(selectedPost.getCreatedAt() + "  " + selectedPost.getUserid());
        tv_num_like_post.setText(String.valueOf(selectedPost.getLike_count())); // 999+ 값이 들어가려면 String으로 변경해야하나?
        tv_num_reply_post.setText(String.valueOf(selectedPost.getReply_count()));

        return view;
    }

}
