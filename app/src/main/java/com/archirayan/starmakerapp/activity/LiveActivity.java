package com.archirayan.starmakerapp.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.SliderAdapter;
import com.archirayan.starmakerapp.adapter.ViewPagerAdapter;
import com.archirayan.starmakerapp.adapter.ViewPagerLiveAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class LiveActivity extends AppCompatActivity {

    private static final Integer[] XMEN = {R.drawable.img_splashtxt, R.drawable.images, R.drawable.images_one, R.drawable.images_two, R.drawable.img_splashtxt};
    private static ViewPager mPager;
    private static int currentPage = 0;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        Collections.addAll(XMENArray, XMEN);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdapter(this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
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

        viewPager = (ViewPager) findViewById(R.id.record_viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),"Live");
        viewPager.setAdapter(viewPagerAdapter);

//        mScrollView = view.findViewById(R.id.scroll_view);

        tabLayout = (TabLayout) findViewById(R.id.record_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
