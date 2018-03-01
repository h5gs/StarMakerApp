package com.archirayan.starmakerapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.HotLiveAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotLiveFragment extends Fragment {

    private GridView grid_hotlive;
    private HotLiveAdapter hotLiveAdapter;

    public HotLiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        grid_hotlive = view.findViewById(R.id.grid_hotlive);

        hotLiveAdapter = new HotLiveAdapter(getActivity());
        grid_hotlive.setAdapter(hotLiveAdapter);

    }
}
