package Team;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teamnexters.volleytalk.R;

import java.util.List;

/**
 * Created by smart on 2017-08-12.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Team_man> teamList;
    private Context context;

    public TeamAdapter(List<Team_man> teamList, Context context)
    {
        this.teamList = teamList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_man, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Glide.with(context)
                .load(teamList.get(position).getImgURL())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.teamimage);

    }
    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView teamimage;
        public TextView teamtext;
        public Button btn;

        public ViewHolder(View view) {
            super(view);

            btn=(Button) view.findViewById(R.id.follow);
            teamtext=(TextView)  view.findViewById(R.id.teamtext);
            teamimage = (ImageView) view.findViewById(R.id.teamimage);
        }
    }



}
