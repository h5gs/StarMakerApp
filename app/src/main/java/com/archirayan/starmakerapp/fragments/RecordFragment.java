package com.archirayan.starmakerapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.LiveActivity;
import com.archirayan.starmakerapp.activity.PartiesActivity;
import com.archirayan.starmakerapp.adapter.SliderAdapter;
import com.archirayan.starmakerapp.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {

    private static final Integer[] XMEN = {R.drawable.img_splashtxt, R.drawable.images, R.drawable.images_one, R.drawable.images_two, R.drawable.img_splashtxt};
    private static ViewPager mPager;
    private static int currentPage = 0;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ImageView iv_backcard;
    CircleImageView iv_liveprofile;
    private CardView card_live, crad_parties;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
//    ScrollView mScrollView;

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Collections.addAll(XMENArray, XMEN);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdapter(getActivity(), XMENArray));
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
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

        viewPager = (ViewPager) view.findViewById(R.id.record_viewPager);

//        mScrollView = view.findViewById(R.id.scroll_view);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), "record");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.record_tabs);
        tabLayout.setupWithViewPager(viewPager);


        iv_backcard = view.findViewById(R.id.iv_backcard);

        iv_backcard.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.blink_in));

        iv_liveprofile = view.findViewById(R.id.iv_liveprofile);

        card_live = view.findViewById(R.id.card_live);
        crad_parties = view.findViewById(R.id.crad_parties);

        card_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LiveActivity.class));
            }
        });
        crad_parties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PartiesActivity.class));
            }
        });


//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//
//            int dragthreshold = 30;
//            int downX;
//            int downY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        downX = (int) event.getRawX();
//                        downY = (int) event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int distanceX = Math.abs((int) event.getRawX() - downX);
//                        int distanceY = Math.abs((int) event.getRawY() - downY);
//
//                        if (distanceY > distanceX && distanceY > dragthreshold) {
//                            mPager.getParent().requestDisallowInterceptTouchEvent(false);
//                            mScrollView.getParent().requestDisallowInterceptTouchEvent(true);
//                        } else if (distanceX > distanceY && distanceX > dragthreshold) {
//                            mPager.getParent().requestDisallowInterceptTouchEvent(true);
//                            mScrollView.getParent().requestDisallowInterceptTouchEvent(false);
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        mScrollView.getParent().requestDisallowInterceptTouchEvent(false);
//                        mPager.getParent().requestDisallowInterceptTouchEvent(false);
//                        break;
//                }
//                return false;
//            }
//        });

    }

}
