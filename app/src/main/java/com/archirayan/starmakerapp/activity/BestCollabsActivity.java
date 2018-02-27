package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.BestAllCollabsAdapter;

public class BestCollabsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_backbestcollabs;
    private RecyclerView recycler_bestallcollabs;
    private BestAllCollabsAdapter bestAllCollabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_collabs);

        iv_backbestcollabs = findViewById(R.id.iv_backbestcollabs);
        recycler_bestallcollabs = findViewById(R.id.recycler_bestallcollabs);
        recycler_bestallcollabs.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        bestAllCollabsAdapter = new BestAllCollabsAdapter(this);
        recycler_bestallcollabs.setAdapter(bestAllCollabsAdapter);


        iv_backbestcollabs.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backbestcollabs:
                onBackPressed();
                break;
        }
    }
}
