package com.teamnexters.volleytalk.team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.team.model.PostAllModelRetro;


/**
 * Created by urchin on 04/09/16.
 */
public class DetailTeamNewsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int layout;
    private Context context;
    private PostAllModelRetro postAllModelRetro;
    private int last_position = -1;

    public DetailTeamNewsAdapter(Context context, PostAllModelRetro postAllModelRetro) {
        this.context = context;
        this.postAllModelRetro = postAllModelRetro;
        this.layout = R.layout.item_lv_text_news;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        return postAllModelRetro.getList().size();
        return 100;
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

            viewHolder.tv_title_text_news = (TextView) convertView.findViewById(R.id.tv_title_text_news);
            viewHolder.tv_source_text_news = (TextView) convertView.findViewById(R.id.tv_source_text_news);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


//        final PostAllModel postAllModel = postAllModelRetro.getList().get(position);

        viewHolder.tv_title_text_news.setText("[V-리그]'연봉 5억원' 한선수 3년연속으로 가나다라다라나자더ㅗㅑ몯ㄹㅈ도랮ㄷㄹ");
        viewHolder.tv_source_text_news.setText("STN sports");

        if (position > last_position) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        }

        last_position = position;

        return convertView;
    }

    public class ViewHolder {
        TextView tv_title_text_news;
        TextView tv_source_text_news;
    }
}