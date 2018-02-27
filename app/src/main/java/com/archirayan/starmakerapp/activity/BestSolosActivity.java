package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.BestAllSolosAdapter;
import com.archirayan.starmakerapp.adapter.BestTenSolosAdapter;

public class BestSolosActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_backbestsolos;
    private GridView grid_bestTensolos,grid_allbestsolos;
    private BestTenSolosAdapter bestTenSolosAdapter;
    private BestAllSolosAdapter bestAllSolosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_solos);

        iv_backbestsolos = findViewById(R.id.iv_backbestsolos);

        grid_bestTensolos = findViewById(R.id.grid_bestTensolos);
        bestTenSolosAdapter = new BestTenSolosAdapter(this);
        grid_bestTensolos.setAdapter(bestTenSolosAdapter);

        grid_allbestsolos = findViewById(R.id.grid_allbestsolos);
        bestAllSolosAdapter = new BestAllSolosAdapter(this);
        grid_allbestsolos.setAdapter(bestAllSolosAdapter);

        iv_backbestsolos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backbestsolos:
                onBackPressed();
                break;
        }
    }
}
