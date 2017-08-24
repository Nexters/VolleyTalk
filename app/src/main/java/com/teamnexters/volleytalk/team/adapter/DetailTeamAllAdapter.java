package com.teamnexters.volleytalk.team.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.team.model.PostAllModel;
import com.teamnexters.volleytalk.team.model.TeamModel;
import com.teamnexters.volleytalk.team.model.PostAllModelRetro;

import static com.teamnexters.volleytalk.config.Config.JHC_DEBUG;


/**
 * Created by urchin on 04/09/16.
 */
public class DetailTeamAllAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int layout;
    private Context context;
    private PostAllModelRetro postAllModelRetro;
    private int last_position = -1;

    public DetailTeamAllAdapter(Context context, PostAllModelRetro postAllModelRetro) {
        this.context = context;
        this.postAllModelRetro = postAllModelRetro;
        this.layout = R.layout.item_lv_post_picture;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        return postAllModelRetro.getList().size();
        return 10;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return postAllModelRetro.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();

            viewHolder.iv_post_picture = (ImageView) convertView.findViewById(R.id.iv_post_picture);

            viewHolder.tv_post_title = (TextView) convertView.findViewById(R.id.tv_post_title);
            viewHolder.tv_post_foreword = (TextView) convertView.findViewById(R.id.tv_post_foreword);
            viewHolder.tv_post_date_writer = (TextView) convertView.findViewById(R.id.tv_post_date_writer);
            viewHolder.tv_num_like_post = (TextView) convertView.findViewById(R.id.tv_num_like_post);
            viewHolder.tv_num_reply_post = (TextView) convertView.findViewById(R.id.tv_num_reply_post);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


//        final PostAllModel postAllModel = postAllModelRetro.getList().get(position);

        viewHolder.tv_post_title.setText("제목");
        viewHolder.tv_post_foreword.setText("오늘 경기 꿀잼");
        viewHolder.tv_post_date_writer.setText("조현철");
        viewHolder.tv_num_like_post.setText("3");
        viewHolder.tv_num_reply_post.setText("5");

        if (position > last_position) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        }

        last_position = position;

        return convertView;
    }

    public class ViewHolder {
        ImageView iv_post_picture;
        TextView tv_post_title;
        TextView tv_post_foreword;
        TextView tv_post_date_writer;
        TextView tv_num_like_post;
        TextView tv_num_reply_post;
    }
}