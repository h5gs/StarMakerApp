package com.archirayan.starmakerapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.DailyCheckInActivity;
import com.archirayan.starmakerapp.activity.FindFriendActivity;
import com.archirayan.starmakerapp.activity.LiveActivity;
import com.archirayan.starmakerapp.activity.PartiesActivity;
import com.archirayan.starmakerapp.activity.ProfileSettingsActivity;
import com.archirayan.starmakerapp.adapter.ProfilePagerAdapter;
import com.archirayan.starmakerapp.adapter.SlideImageAdapter;
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
public class ProfileFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView img_setting, img_findfreinds, img_daily_checkin;
    private boolean mIsAvatarShown = true;
    private ImageView mProfileImage;
    private int mMaxScrollSize;
    private CollapsingToolbarLayout collapsingToolbarLayout;


  //  Image Slider
  private static final Integer[] XMEN = {R.drawable.img_splashtxt, R.drawable.images, R.drawable.images_one, R.drawable.images_two, R.drawable.img_splashtxt};
    private static ViewPager mPager;
    private static int currentPage = 0;
    ViewPager Pager;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        AppBarLayout appbarLayout = (AppBarLayout) view.findViewById(R.id.materialup_appbar);
        mProfileImage = (ImageView) view.findViewById(R.id.materialup_profile_image);
        img_setting = view.findViewById(R.id.img_setting);
        img_findfreinds = view.findViewById(R.id.img_findfreinds);
        img_daily_checkin = view.findViewById(R.id.img_daily_checkin);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        Pager = (ViewPager) view.findViewById(R.id.pager);

        ProfilePagerAdapter viewPagerAdapter = new ProfilePagerAdapter(getChildFragmentManager(), "song");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_layout);
        tabLayout.setupWithViewPager(viewPager);
        img_setting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getActivity(), ProfileSettingsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });


        img_findfreinds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getActivity(), FindFriendActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        img_daily_checkin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getActivity(), DailyCheckInActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();


        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing);




        // Image Slide Using View Pager ..
        Collections.addAll(XMENArray, XMEN);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SlideImageAdapter(getActivity(), XMENArray));
        // Auto start of Viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable()
        {
            public void run()
            {
                if (currentPage == XMEN.length)
                {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                handler.post(Update);
            }
        }, 5000, 5000);



        return view;
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 50 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }
}
