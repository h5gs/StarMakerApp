package com.archirayan.starmakerapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.archirayan.starmakerapp.fragments.PostListFragment;
import com.archirayan.starmakerapp.fragments.SongListFragment;

/**
 * Created by archirayan on 23/2/18.
 */

public class ProfilePagerAdapter extends FragmentPagerAdapter
{

    String come;

    public ProfilePagerAdapter(FragmentManager fm, String come)
    {
        super(fm);

        this.come = come;
    }

    @Override
    public Fragment getItem(int position)
    {

        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new PostListFragment();
        } else if (position == 1)
        {
            fragment = new SongListFragment();
        } else if (position == 2)
        {
            fragment = new SongListFragment();
        } else if (position == 3)
        {
            fragment = new SongListFragment();
        } else if (position == 4)
        {
            fragment = new SongListFragment();
        } else if (position == 5)
        {
            fragment = new SongListFragment();
        } else if (position == 6)
        {
            fragment = new SongListFragment();
        } else if (position == 7)
        {
            fragment = new SongListFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {

        return 8;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;

        if (position == 0)
        {
            title = "Posts";
        } else if (position == 1)
        {
            title = "Drafts";
        } else if (position == 2)
        {
            title = "Collabs";
        } else if (position == 3)
        {
            title = "Songs";
        } else if (position == 4)
        {
            title = "Likes";
        } else if (position == 5)
        {
            title = "Plays";
        } else if (position == 6)
        {
            title = "Shares";
        } else if (position == 7)
        {
            title = "Favorites";
        }
        return title;
    }
}