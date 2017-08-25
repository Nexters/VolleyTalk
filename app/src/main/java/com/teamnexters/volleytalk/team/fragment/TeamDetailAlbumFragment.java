package com.teamnexters.volleytalk.team.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.teamnexters.volleytalk.R;
import com.teamnexters.volleytalk.common.ApiService;
import com.teamnexters.volleytalk.team.DetailTeamActivity;
import com.teamnexters.volleytalk.team.adapter.DetailTeamAlbumAdapter;
import com.teamnexters.volleytalk.team.adapter.DetailTeamAllAdapter;
import com.teamnexters.volleytalk.team.adapter.TeamAdapter;
import com.teamnexters.volleytalk.team.model.PostAllModelRetro;
import com.teamnexters.volleytalk.team.model.TeamModel;
import com.teamnexters.volleytalk.team.model.TeamModelRetro;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.teamnexters.volleytalk.common.ApiService.API_URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamDetailAlbumFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamDetailAlbumFragment#newInstance} factory method to
 * create an instance of this fragment.∂
 */
public class TeamDetailAlbumFragment extends Fragment {
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

    private GridView gridView;

    private RadioButton rb_male_team;
    private RadioButton rb_female_team;
    private int list_count = 0;

    private ListView lv_allpost_mypage;
    private DetailTeamAlbumAdapter detailTeamAlbumAdapter;
    private PostAllModelRetro postAllModelRetro;


    private Call<TeamModelRetro> mainModelCall;

    public TeamDetailAlbumFragment() {
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
    public static TeamDetailAlbumFragment newInstance(String category_type, String language, int position) {
        TeamDetailAlbumFragment fragment = new TeamDetailAlbumFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container, false);

        initResources(view);

//        test();

        return view;
    }

    private void initResources(View view) {
        initRetrofit();
        initListView(view);

//        initRadioButtons(view);
//
//        initGridView(view);
    }

    private void initListView(View view) {
        gridView = (GridView) view.findViewById(R.id.gridView);

        detailTeamAlbumAdapter = new DetailTeamAlbumAdapter(getContext(), postAllModelRetro);
        gridView.setAdapter(detailTeamAlbumAdapter);
        gridView.setNestedScrollingEnabled(true);
    }

    private void initRetrofit() {
        /** Retrofit **/
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        postAllModelRetro = new PostAllModelRetro();
    }

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

//        if (mainModelCall.isExecuted()) {
//            mainModelCall.cancel();
//        }
    }


}
