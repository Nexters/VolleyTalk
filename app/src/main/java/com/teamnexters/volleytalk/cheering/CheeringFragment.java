package com.teamnexters.volleytalk.cheering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.ResForm;
import com.teamnexters.volleytalk.album.AlbumFragment;
import com.teamnexters.volleytalk.tool.NetworkModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class CheeringFragment extends Fragment {

    private List<Cheering> cheerings;
    private CheeringAdapter adapter_cheering;

    private EditText et_cheering;
    private Button btn_apply_cheering;

    public static CheeringFragment newInstance(int seq) {
        CheeringFragment instance = new CheeringFragment();
        Bundle args = new Bundle();
        args.putInt("seq", seq);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_cheering = inflater.inflate(R.layout.fragment_cheering, container, false);

        et_cheering = (EditText) rootView_cheering.findViewById(R.id.et_cheering);
        btn_apply_cheering = (Button) rootView_cheering.findViewById(R.id.btn_apply_cheering);
        btn_apply_cheering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Click", "click");
                applyCheering();
            }
        });

        ListView lv_cheering = (ListView) rootView_cheering.findViewById(R.id.lv_cheering);

        cheerings = new ArrayList<>();
        adapter_cheering = new CheeringAdapter(cheerings);
        lv_cheering.setAdapter(adapter_cheering);

        requestCheeringList();

        return rootView_cheering;
    }


    private void requestCheeringList() {
        Bundle args = getArguments();
        int seq = args.getInt("seq");

        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<ResForm<List<Cheering>>> call = networkModel.getCheeringList(seq);
        call.enqueue(new Callback<ResForm<List<Cheering>>>() {
            @Override
            public void onResponse(Call<ResForm<List<Cheering>>> call, Response<ResForm<List<Cheering>>> response) {
                if (response.body() != null) {
                    ResForm<List<Cheering>> result = response.body();

                    if(result.getStatus().equals("true")) {
                        cheerings.addAll(result.getResData());
                        adapter_cheering.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), result.getErrMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResForm<List<Cheering>>> call, Throwable t) {

            }
        });
    }

    private void applyCheering() {
        Bundle args = getArguments();
        int seq = args.getInt("seq");

        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);
        Call<String> call = networkModel.applyCheering(seq, et_cheering.getText().toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if ( response.body() != null ) {
                        requestCheeringList();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }




}
