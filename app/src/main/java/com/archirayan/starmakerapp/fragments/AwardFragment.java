package com.archirayan.starmakerapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.EventsActivity;
import com.archirayan.starmakerapp.activity.HotHashtagActivity;
import com.archirayan.starmakerapp.activity.VerifiedActivity;
import com.archirayan.starmakerapp.adapter.BestCollabsAdapter;
import com.archirayan.starmakerapp.adapter.BestSolosAdapter;
import com.archirayan.starmakerapp.adapter.MostSharedAdapter;
import com.archirayan.starmakerapp.adapter.SliderAdapter;
import com.archirayan.starmakerapp.adapter.TopFriendsAdapter;
import com.archirayan.starmakerapp.adapter.TopStarsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class AwardFragment extends Fragment implements View.OnClickListener {

    private static final Integer[] XMEN = {R.drawable.img_splashtxt, R.drawable.images, R.drawable.images_one, R.drawable.images_two, R.drawable.img_splashtxt};
    private static ViewPager mPager;
    private static int currentPage = 0;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private BestSolosAdapter bestSolosAdapter;
    private RecyclerView recycler_bestsolo;
    private RecyclerView recycler_mostshared;
    private MostSharedAdapter mostSharedAdapter;
    private RecyclerView recycler_bestcollabs;
    private BestCollabsAdapter bestCollabsAdapter;
    private RecyclerView recycler_beststars;
    private TopStarsAdapter topStarsAdapter;
    private RecyclerView recycler_topfriends;
    private TopFriendsAdapter topFriendsAdapter;
    private LinearLayout linear_events, linear_verified;
    private TextView txt_hashtagseeall;

    public AwardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_award, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Collections.addAll(XMENArray, XMEN);

        mPager = (ViewPager) view.findViewById(R.id.awad_pager);
        mPager.setAdapter(new SliderAdapter(getActivity(), XMENArray));
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.awad_indicator);
        indicator.setViewPager(mPager);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

        recycler_bestsolo = view.findViewById(R.id.recycler_bestsolo);
        recycler_bestsolo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        bestSolosAdapter = new BestSolosAdapter(getActivity());
        recycler_bestsolo.setAdapter(bestSolosAdapter);

        recycler_mostshared = view.findViewById(R.id.recycler_mostshared);
        recycler_mostshared.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        mostSharedAdapter = new MostSharedAdapter(getActivity());
        recycler_mostshared.setAdapter(mostSharedAdapter);

        recycler_bestcollabs = view.findViewById(R.id.recycler_bestcollabs);
        recycler_bestcollabs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        bestCollabsAdapter = new BestCollabsAdapter(getActivity());
        recycler_bestcollabs.setAdapter(bestCollabsAdapter);

        recycler_beststars = view.findViewById(R.id.recycler_beststars);
        recycler_beststars.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        topStarsAdapter = new TopStarsAdapter(getActivity());
        recycler_beststars.setAdapter(topStarsAdapter);

        recycler_topfriends = view.findViewById(R.id.recycler_topfriends);
        recycler_topfriends.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        topFriendsAdapter = new TopFriendsAdapter(getActivity());
        recycler_topfriends.setAdapter(topFriendsAdapter);

        linear_events = view.findViewById(R.id.linear_events);
        linear_verified = view.findViewById(R.id.linear_verified);

        txt_hashtagseeall = view.findViewById(R.id.txt_hashtagseeall);

        linear_events.setOnClickListener(this);
        linear_verified.setOnClickListener(this);
        txt_hashtagseeall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_events:
                startActivity(new Intent(getActivity(), EventsActivity.class));
                break;
            case R.id.linear_verified:
                startActivity(new Intent(getActivity(), VerifiedActivity.class));
                break;
            case R.id.txt_hashtagseeall:
                startActivity(new Intent(getActivity(), HotHashtagActivity.class));
                break;
        }
    }
}
