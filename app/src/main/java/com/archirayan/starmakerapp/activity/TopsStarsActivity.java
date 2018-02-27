package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.TopAllStarsAdapter;

public class TopsStarsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_topallstars;
    private ImageView iv_backtopstars;
    private TopAllStarsAdapter topAllStarsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops_stars);

        iv_backtopstars = findViewById(R.id.iv_backtopstars);

        recycler_topallstars = findViewById(R.id.recycler_topallstars);
        recycler_topallstars.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        topAllStarsAdapter = new TopAllStarsAdapter(this);
        recycler_topallstars.setAdapter(topAllStarsAdapter);

        iv_backtopstars.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_backtopstars:
                onBackPressed();
                break;
        }
    }
}
