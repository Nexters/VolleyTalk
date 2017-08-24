package com.teamnexters.volleytalk.team.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.team.model.TeamModel;
import com.teamnexters.volleytalk.team.model.TeamModelRetro;

import static com.teamnexters.volleytalk.config.Config.JHC_DEBUG;


/**
 * Created by urchin on 04/09/16.
 */
public class TeamAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int layout;
    private Context context;
    private TeamModelRetro teamModelRetro;
    private int last_position = -1;

    public TeamAdapter(Context context, TeamModelRetro teamModelRetro) {
        this.context = context;
        this.teamModelRetro = teamModelRetro;
        this.layout = R.layout.team_man;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return teamModelRetro.getList().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return teamModelRetro.getList().get(position);
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

            viewHolder.view_team = (View) convertView.findViewById(R.id.view_team);
            viewHolder.img_team = (ImageView) convertView.findViewById(R.id.img_team);
            viewHolder.txt_team = (TextView) convertView.findViewById(R.id.txt_team);
            viewHolder.btn_follow = (TextView) convertView.findViewById(R.id.btn_follow);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final TeamModel teamModel = teamModelRetro.getList().get(position);

        viewHolder.view_team.setBackgroundColor(teamModel.getTeamColor());
        Log.e(JHC_DEBUG, "COLOR : " + teamModel.getTeamColor());

        if (teamModel.getTeamImg() != 0) {
            Log.e(JHC_DEBUG, "IMG : " + teamModel.getTeamImg());
            Glide.with(context)
                    .load(teamModel.getTeamImg())
                    .apply(RequestOptions.fitCenterTransform())
                    .into(viewHolder.img_team);
        }

        viewHolder.txt_team.setText(teamModel.getTeamText());

        if (position > last_position) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        }

        last_position = position;

        return convertView;
    }

    public class ViewHolder {
        View view_team;
        ImageView img_team;
        TextView txt_team;
        TextView btn_follow;
    }
}