package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.FindyourfreindsAdapter;

public class FindYourFreindsActivity extends AppCompatActivity {

    RecyclerView item_findyourfreinds_list;
    FindyourfreindsAdapter findyourfreindsAdapter;
    TextView txt_next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_freinds);



        txt_next = findViewById(R.id.txt_next);
        txt_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindYourFreindsActivity.this, PeopleLikeActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });

        item_findyourfreinds_list = findViewById(R.id.item_findyourfreinds_list);
        findyourfreindsAdapter = new FindyourfreindsAdapter(FindYourFreindsActivity.this);
        item_findyourfreinds_list.setLayoutManager(new LinearLayoutManager(FindYourFreindsActivity.this, LinearLayoutManager.VERTICAL, false));
        item_findyourfreinds_list.setAdapter(findyourfreindsAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FindYourFreindsActivity.this, EditprofileActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
}