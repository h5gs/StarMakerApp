package com.archirayan.starmakerapp.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.HotLiveAdapter;
import com.archirayan.starmakerapp.adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class PartiesActivity extends AppCompatActivity {

    private static final Integer[] XMEN = {R.drawable.img_splashtxt, R.drawable.images, R.drawable.images_one, R.drawable.images_two, R.drawable.img_splashtxt};
    private static ViewPager mPager;
    private static int currentPage = 0;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private GridView grid_partieshotlist;
    private HotLiveAdapter hotLiveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        Collections.addAll(XMENArray, XMEN);

        mPager = (ViewPager) findViewById(R.id.awad_pager);
        mPager.setAdapter(new SliderAdapter(this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.awad_indicator);
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

        grid_partieshotlist = findViewById(R.id.grid_partieshotlist);
        hotLiveAdapter = new HotLiveAdapter(this);
        grid_partieshotlist.setAdapter(hotLiveAdapter);

    }
}
