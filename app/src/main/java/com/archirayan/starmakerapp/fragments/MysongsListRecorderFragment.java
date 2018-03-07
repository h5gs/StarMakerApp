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
import com.archirayan.starmakerapp.adapter.MainAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MysongsListRecorderFragment extends Fragment {

    RecyclerView itemrecoder_list;
    MainAdapter mainAdapter;

    public MysongsListRecorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mysongs_list_recorder, container, false);
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
