package com.archirayan.starmakerapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.DailyCheckInActivity;
import com.archirayan.starmakerapp.activity.FindFriendActivity;
import com.archirayan.starmakerapp.activity.ProfileSettingsActivity;
import com.archirayan.starmakerapp.adapter.ProfilePagerAdapter;

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

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        AppBarLayout appbarLayout = (AppBarLayout) view.findViewById(R.id.materialup_appbar);
        mProfileImage = (ImageView) view.findViewById(R.id.materialup_profile_image);
        img_setting = view.findViewById(R.id.img_setting);
        img_findfreinds = view.findViewById(R.id.img_findfreinds);
        img_daily_checkin = view.findViewById(R.id.img_daily_checkin);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        ProfilePagerAdapter viewPagerAdapter = new ProfilePagerAdapter(getChildFragmentManager(), "song");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_layout);
        tabLayout.setupWithViewPager(viewPager);
        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileSettingsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });


        img_findfreinds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FindFriendActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        img_daily_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DailyCheckInActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();


        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing);

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
