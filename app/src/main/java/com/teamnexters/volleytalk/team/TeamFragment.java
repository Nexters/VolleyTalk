package com.teamnexters.volleytalk.team;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.common.ApiService;
import com.teamnexters.volleytalk.team.adapter.TeamAdapter;
import com.teamnexters.volleytalk.team.model.TeamModel;
import com.teamnexters.volleytalk.team.model.TeamModelRetro;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.teamnexters.volleytalk.common.ApiService.API_URL;
import static com.teamnexters.volleytalk.config.Config.JHC_DEBUG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.∂
 */
public class TeamFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private Retrofit retrofit;
    private ApiService apiService;

//    private VideoGridAdapter adapter;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CATEGORY_TYPE = "CATEGORY_TYPE";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String POSITION = "POSITION";

    // TODO: Rename and change types of parameters
    private String category_type;
    private String language;
    private int position;

    private OnFragmentInteractionListener mListener;

    private TeamModelRetro manTeamModelRetro;
    private TeamModelRetro womanTeamModelRetro;
    private TeamAdapter teamAdapter;
    private GridView gridView;

    private RadioButton rb_male_team;
    private RadioButton rb_female_team;
    private int list_count = 0;


    private Call<TeamModelRetro> mainModelCall;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category_type Parameter 1.
     * @return A new instance of fragment SurveyItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String category_type, String language, int position) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_TYPE, category_type);
        args.putString(LANGUAGE, language);
        args.putInt(POSITION, position);
        fragment.setArguments(args);

        Log.e("JHC_DEBUG", "RECENT FRAGMENT newInstance");

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category_type = getArguments().getString(CATEGORY_TYPE);
            language = getArguments().getString(LANGUAGE);
            position = getArguments().getInt(POSITION);
        }

        Log.e("JHC_DEBUG", "RECENT FRAGMENT onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();

        if (teamAdapter != null) {
            teamAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        initResources(view);

        test();

        return view;
    }

    private void test() {
        testMan();
        testWoman();
    }

    private void testMan() {

        String[] manTeamList = getContext().getResources().getStringArray(R.array.team_male_list);
        int[] manTeamColor = getContext().getResources().getIntArray(R.array.team_male_color_list);
        TypedArray imgs = getContext().getResources().obtainTypedArray(R.array.team_img_male_list);

        for (int i = 0; i < 7; i++) {
            TeamModel teamModel = testItem(i + 1, manTeamColor[i], imgs.getResourceId(i, -1), manTeamList[i]);
            manTeamModelRetro.getList().add(teamModel);
        }

        teamAdapter.notifyDataSetChanged();
    }

    private void testWoman() {

        String[] womanTeamList = getContext().getResources().getStringArray(R.array.team_female_list);
        int[] womanTeamColor = getContext().getResources().getIntArray(R.array.team_female_color_list);
        TypedArray imgs = getContext().getResources().obtainTypedArray(R.array.team_img_female_list);

        for (int i = 0; i < 7; i++) {
            TeamModel teamModel = testItem(i + 1, womanTeamColor[i], imgs.getResourceId(i, -1), womanTeamList[i]);
            womanTeamModelRetro.getList().add(teamModel);
        }

        teamAdapter.notifyDataSetChanged();
    }

    private TeamModel testItem(int seq, int teamColor, int imgUrl, String txtTeam) {
        TeamModel teamModel = new TeamModel();
        teamModel.setSeq(seq);
        teamModel.setTeamColor(teamColor);
        teamModel.setTeamImg(imgUrl);
        teamModel.setTeamText(txtTeam);
        return teamModel;
    }

    private void initResources(View view) {
        initRetrofit();

        initRadioButtons(view);

        initGridView(view);
    }

    private void initRetrofit() {
        /** Retrofit **/
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        manTeamModelRetro = new TeamModelRetro();
        womanTeamModelRetro = new TeamModelRetro();
    }

    private void initRadioButtons(View view) {
        rb_male_team = (RadioButton) view.findViewById(R.id.rb_male_team);
        rb_female_team = (RadioButton) view.findViewById(R.id.rb_female_team);

        rb_male_team.setOnCheckedChangeListener(checkedChangeListener);
        rb_female_team.setOnCheckedChangeListener(checkedChangeListener);
    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.rb_male_team:
                    if (isChecked) {
                        teamAdapter = new TeamAdapter(getContext(), manTeamModelRetro);
                        gridView.setAdapter(teamAdapter);
                    }
                    break;

                case R.id.rb_female_team:
                    if (isChecked) {
                        teamAdapter = new TeamAdapter(getContext(), womanTeamModelRetro);
                        gridView.setAdapter(teamAdapter);
                    }
                    break;
            }
        }
    };

    private void initGridView(View view) {
        gridView = (GridView) view.findViewById(R.id.gridView);
        teamAdapter = new TeamAdapter(getContext(), manTeamModelRetro);
        gridView.setAdapter(teamAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailTeamActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void getList(String start) {
//        String category = "'" + category_type + "'";
//        Log.e(JHC_DEBUG, "<<<<< CATEGORY : " + category);
//        Log.e(JHC_DEBUG, "<<<<< SORT FLAG : " + getSortType());
//        Log.e(JHC_DEBUG, "<<<<< START : " + start);
//
//        mainModelCall = apiService.getVideoList(category, null, getSortType(), "D", start, String.valueOf(MAX_LIST_COUNT), null);
//        mainModelCall.enqueue(new Callback<TeamModelRetro>() {
//            @Override
//            public void onResponse(Call<TeamModelRetro> call, Response<TeamModelRetro> response) {
//
//                if (response == null) {
//                    Log.e("JHC_DEBUG", "RESPONSE IS NULL");
//                    return;
//                }
//
//                if (!response.isSuccessful()) {
//                    Log.e("JHC_DEBUG", response.body().getCode());
//                    return;
//                }
//
//                if (!response.body().isSuccessful()) {
//                    Log.e("JHC_DEBUG", response.body().getVideoList().toString());
//                    return;
//                }
//
//                Log.e("JHC_DEBUG", "데이터 가져오기에 성공했습니다. " + response.body().getCode() + " / " + response.body().getMessage());
//
//                if (list_count == 0) {
//                    manTeamModelRetro = response.body();
//                    teamAdapter = new TeamAdapter2(getActivity(), manTeamModelRetro);
//                    gridView.setAdapter(teamAdapter);
//                } else {
//                    for (int i = 0; i < response.body().getVideoList().size(); i++) {
//                        manTeamModelRetro.getVideoList().add(response.body().getVideoList().get(i));
//                    }
//                }
//
//                list_count += MAX_LIST_COUNT;
//                mLockListView = false;
//
//                teamAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<TeamModelRetro> call, Throwable t) {
//                Log.e("JHC_DEBUG", "데이터 가져오기에 실패했습니다. " + t.getMessage());
//            }
//        });
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        /*
        if (mainModelCall.isExecuted()) {
            mainModelCall.cancel();
        }
        */
    }
}
