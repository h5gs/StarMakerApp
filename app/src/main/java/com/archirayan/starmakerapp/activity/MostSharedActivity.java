package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.MostAllSharedAdapter;
import com.archirayan.starmakerapp.adapter.MostTenSharedAdapter;

public class MostSharedActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_backmostshared;
    private GridView grid_mostTen,grid_allmostshared;
    private MostTenSharedAdapter mostTenSharedAdapter;
    private MostAllSharedAdapter mostAllSharedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_shared);

        iv_backmostshared = findViewById(R.id.iv_backmostshared);

        grid_mostTen = findViewById(R.id.grid_mostTen);
        mostTenSharedAdapter = new MostTenSharedAdapter(this);
        grid_mostTen.setAdapter(mostTenSharedAdapter);

        grid_allmostshared = findViewById(R.id.grid_allmostshared);
        mostAllSharedAdapter = new MostAllSharedAdapter(this);
        grid_allmostshared.setAdapter(mostAllSharedAdapter);

        iv_backmostshared.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backmostshared:
                onBackPressed();
                break;
        }

    }
}
