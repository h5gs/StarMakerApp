package com.archirayan.starmakerapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class newListRecorderFragment extends Fragment {


    public newListRecorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list_recorder, container, false);
    }

}
