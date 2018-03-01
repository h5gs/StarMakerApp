package com.archirayan.starmakerapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.archirayan.starmakerapp.fragments.FollowingFragment;
import com.archirayan.starmakerapp.fragments.HotLiveFragment;
import com.archirayan.starmakerapp.fragments.NearbyLiveFragment;
import com.archirayan.starmakerapp.fragments.SongListFragment;
import com.archirayan.starmakerapp.fragments.SongListRecorderFragment;
import com.archirayan.starmakerapp.fragments.YouFragment;

/**
 * Created by archirayan on 22/2/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String come;

    public ViewPagerAdapter(FragmentManager fm, String come) {
        super(fm);
        this.come = come;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (come) {
            case "notification":
                if (position == 0) {
                    fragment = new FollowingFragment();
                } else if (position == 1) {
                    fragment = new YouFragment();
                }

                break;
            case "song":
                if (position == 0) {
                    fragment = new SongListFragment();
                } else if (position == 1) {
                    fragment = new SongListFragment();
                } else if (position == 2) {
                    fragment = new SongListFragment();
                }
                break;
            case "record":
                if (position == 0) {
                    fragment = new SongListRecorderFragment();
                } else if (position == 1) {
                    fragment = new SongListRecorderFragment();
                } else if (position == 2) {
                    fragment = new SongListRecorderFragment();
                } else if (position == 3) {
                    fragment = new SongListRecorderFragment();
                }
                break;
            case "Live":
                if (position == 0) {
                    fragment = new HotLiveFragment();
                }else if (position == 1) {
                    fragment = new NearbyLiveFragment();
                }
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {

        int val = 0;

        switch (come) {
            case "notification":
                val = 2;
                break;
            case "song":
                val = 3;
                break;
            case "record":
                val = 4;
                break;
            case "Live":
                val = 2;
                break;
        }

        return val;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (come) {
            case "notification":
                if (position == 0) {
                    title = "Following";
                } else if (position == 1) {
                    title = "You";
                }
                break;
            case "song":
                if (position == 0) {
                    title = "Following";
                } else if (position == 1) {
                    title = "Trending";
                } else if (position == 2) {
                    title = "Nearby";
                }
                break;
            case "record":
                if (position == 0) {
                    title = "HOT";
                } else if (position == 1) {
                    title = "COLLAB";
                } else if (position == 2) {
                    title = "NEW";
                } else if (position == 3) {
                    title = "MY SONGS";
                }
                break;
            case "Live":
                if (position == 0) {
                    title = "HOT";
                }else if(position == 1){
                    title = "NEARBY";
                }
                break;
        }
        return title;
    }
}
