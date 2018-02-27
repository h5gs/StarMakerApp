package com.archirayan.starmakerapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.MainAdapter;
import com.archirayan.starmakerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongListRecorderFragment extends Fragment {

    RecyclerView itemrecoder_list;
    MainAdapter mainAdapter;


    public SongListRecorderFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean getUserVisibleHint() {

        if(isVisible()){
            mainAdapter = new MainAdapter(getActivity());
            itemrecoder_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

            itemrecoder_list.setAdapter(mainAdapter);
        }
        return isVisible();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list_recorder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemrecoder_list = view.findViewById(R.id.itemrecoder_list);

        mainAdapter = new MainAdapter(getActivity());
        itemrecoder_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        itemrecoder_list.setAdapter(mainAdapter);

    }
}
