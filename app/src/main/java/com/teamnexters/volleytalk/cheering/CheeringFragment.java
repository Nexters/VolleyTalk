package com.teamnexters.volleytalk.cheering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teamnexters.volleytalk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class CheeringFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView_cheering = inflater.inflate(R.layout.fragment_cheering, container, false);

        ListView lv_cheering = (ListView) rootView_cheering.findViewById(R.id.lv_cheering);


            //test
        List<Cheering> cheerings = new ArrayList<>();
        cheerings.add(new Cheering("AAAAAA", "BBBBB", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", "CCCCC"));
        cheerings.add(new Cheering("AAAAAA", "BBBBB", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", "CCCCC"));
        cheerings.add(new Cheering("AAAAAA", "BBBBB", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", "CCCCC"));
        cheerings.add(new Cheering("AAAAAA", "BBBBB", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", "CCCCC"));
        cheerings.add(new Cheering("AAAAAA", "민지영", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", "CCCCC"));

        CheeringAdapter adapter_cheering = new CheeringAdapter(cheerings);
        lv_cheering.setAdapter(adapter_cheering);

        return rootView_cheering;
    }

}
