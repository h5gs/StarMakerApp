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

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.SongAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongListFragment extends Fragment
{

    RecyclerView rvList;
    SongAdapter songAdapter;

    public SongListFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean getUserVisibleHint() {

        if (isVisible()) {
            songAdapter = new SongAdapter(getActivity());
            rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

            rvList.setAdapter(songAdapter);
        }
        return isVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList = view.findViewById(R.id.item_list);

        songAdapter = new SongAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        rvList.setAdapter(songAdapter);

    }
}
