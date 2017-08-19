package com.teamnexters.volleytalk.album;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.config.Config;
import com.teamnexters.volleytalk.post.Post;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private List<Post> postImgList;
    private Context context;

    public AlbumAdapter(List<Post> postImgList, Context context) {
        this.postImgList = postImgList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_album, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideUrl glideUrl = new GlideUrl(Config.SERVER_IP + postImgList.get(position).getImg_url_thumb(), new LazyHeaders.Builder()
                .addHeader("Cookie", "userid=" + UserProfile.loadFromCache().getId())
                .build());

        Log.e("TEST", Config.SERVER_IP + postImgList.get(position).getImg_url());

        Glide.with(context)
                .load(glideUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.iv_item_rv);

    }

    @Override
    public int getItemCount() {
        return postImgList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_item_rv;

        public ViewHolder(View view) {
            super(view);

            iv_item_rv = (ImageView) view.findViewById(R.id.iv_item_rv);
        }
    }
}
