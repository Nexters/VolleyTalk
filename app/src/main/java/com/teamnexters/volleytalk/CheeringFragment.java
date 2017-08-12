package com.teamnexters.volleytalk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class CheeringFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_cheering = inflater.inflate(R.layout.fragment_cheering, container, false);
        return rootView_cheering;
    }
}
