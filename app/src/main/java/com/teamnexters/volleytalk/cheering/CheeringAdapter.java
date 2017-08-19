package com.teamnexters.volleytalk.cheering;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.response.model.UserProfile;
import com.teamnexters.volleytalk.R;

import java.util.List;

/**
 * Created by MIN on 2017. 8. 16..
 */

public class CheeringAdapter extends BaseAdapter {

    private static final int SELF_CHEERING = 0;
    private static final int OTHERS_CHEERING = 1;

    List<Cheering> cheeringList;

    public CheeringAdapter(List<Cheering> cheeringList) {
        setCheeringList(cheeringList);
    }

    public List<Cheering> getCheeringList() {
        return cheeringList;
    }

    public void setCheeringList(List<Cheering> cheeringList) {
        this.cheeringList = cheeringList;
    }

    @Override
    public int getCount() {
        return cheeringList.size();
    }

    @Override
    public Object getItem(int position) {
        return cheeringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (cheeringList.get(position).getUser().equals(UserProfile.loadFromCache().getNickname()))
            return SELF_CHEERING;
        else
            return OTHERS_CHEERING;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final Cheering selectedCheering = (Cheering) getItem(position);
        Context context = viewGroup.getContext();

        if( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            switch (getItemViewType(position)) {
                case SELF_CHEERING :
                    view = inflater.inflate(R.layout.item_cheering_self, viewGroup, false);
                    break;
                case OTHERS_CHEERING :
                    view = inflater.inflate(R.layout.item_cheering_others, viewGroup, false);
                    break;
            }
        }

        ImageView iv_profileImg_cheering = (ImageView) view.findViewById(R.id.iv_profileImg_cheering);
        TextView tv_content_cheering = (TextView) view.findViewById(R.id.tv_content_cheering);
        TextView tv_writer_cheering = (TextView) view.findViewById(R.id.tv_writer_cheering);

        tv_content_cheering.setText(selectedCheering.getContent());
        tv_writer_cheering.setText(selectedCheering.getUser() + " | " + selectedCheering.getTime());

        Glide.with(context)
                .load(selectedCheering.getImgURL())
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profileImg_cheering);

        return view;
    }
}
