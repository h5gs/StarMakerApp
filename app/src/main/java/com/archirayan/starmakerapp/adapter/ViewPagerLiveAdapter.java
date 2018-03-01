package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.archirayan.starmakerapp.activity.LiveActivity;
import com.archirayan.starmakerapp.fragments.HotLiveFragment;
import com.archirayan.starmakerapp.fragments.NearbyLiveFragment;

/**
 * Created by archirayan on 1/3/18.
 */

public class ViewPagerLiveAdapter extends FragmentPagerAdapter{

    private Context context;

    public ViewPagerLiveAdapter(Context context, FragmentManager fm) {
        super(fm);
        context = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HotLiveFragment();
        } else {
            return new NearbyLiveFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
