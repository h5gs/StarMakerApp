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
import com.archirayan.starmakerapp.adapter.YouAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouFragment extends Fragment {

    RecyclerView rvPatry, rvRecommendation;
    YouAdapter youAdapter, youAdapterr;

    public YouFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_you, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPatry = view.findViewById(R.id.party_list);
        rvRecommendation = view.findViewById(R.id.recommendation_list);

        rvPatry.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvRecommendation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        rvPatry.setNestedScrollingEnabled(false);
        rvRecommendation.setNestedScrollingEnabled(false);

        youAdapter = new YouAdapter(getActivity(), "invitation");
        youAdapterr = new YouAdapter(getActivity(), "recommendation");

        rvPatry.setAdapter(youAdapter);
        rvRecommendation.setAdapter(youAdapterr);

    }

}
