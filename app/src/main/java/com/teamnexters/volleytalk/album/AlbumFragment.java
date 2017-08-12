package com.teamnexters.volleytalk.album;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamnexters.volleytalk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class AlbumFragment extends Fragment {

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();

        View rootView_album = inflater.inflate(R.layout.fragment_album, container, false);
        RecyclerView rl_album = (RecyclerView) rootView_album.findViewById(R.id.rv_album);



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        rl_album.setLayoutManager(layoutManager);

        /*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rl_album.getContext(),
                rl_album.getlayoutManager.getOrientation());
        rl_album.addItemDecoration(dividerItemDecoration);
        */

        //리스트 만들어서 적당히 테스트 데이터 집어넣기.
        List<Album> testList = new ArrayList<>();
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 1));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 2));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 3));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 4));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 5));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 6));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 7));
        testList.add(new Album("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYuOvt6wBrcmOygN2bzKbs2T1BcDJiWIS_HSqd4aWqqSmQ53OPrQ", 8));

        AlbumAdapter albumAdapter = new AlbumAdapter(testList, context);
        rl_album.setAdapter(albumAdapter);

        return rootView_album;
    }
}
