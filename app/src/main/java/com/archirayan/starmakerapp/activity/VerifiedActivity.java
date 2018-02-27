package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.LatestCollabsAdapter;
import com.archirayan.starmakerapp.adapter.LatestRecordingAdapter;
import com.archirayan.starmakerapp.adapter.NewSingersAdapter;

public class VerifiedActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView grid_latestrecordings;
    private LatestRecordingAdapter latestRecordingAdapter;
    private ImageView iv_backverified;
    private RecyclerView recycler_newSengers;
    private NewSingersAdapter newSingersAdapter;
    private RecyclerView recycler_latestcollabs;
    private LatestCollabsAdapter latestCollabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);

        grid_latestrecordings = findViewById(R.id.grid_latestrecordings);
        latestRecordingAdapter = new LatestRecordingAdapter(this);
        grid_latestrecordings.setAdapter(latestRecordingAdapter);

        recycler_newSengers = findViewById(R.id.recycler_newSengers);
        recycler_newSengers.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        newSingersAdapter = new NewSingersAdapter(this);
        recycler_newSengers.setAdapter(newSingersAdapter);

        recycler_latestcollabs = findViewById(R.id.recycler_latestcollabs);
        recycler_latestcollabs.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        latestCollabsAdapter = new LatestCollabsAdapter(this);
        recycler_latestcollabs.setAdapter(latestCollabsAdapter);

        iv_backverified = findViewById(R.id.iv_backverified);

        iv_backverified.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backverified:
                onBackPressed();
                break;
        }

    }
}
