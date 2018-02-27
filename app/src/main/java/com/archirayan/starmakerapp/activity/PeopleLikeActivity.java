package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.PeopleLikeAdapter;

public class PeopleLikeActivity extends AppCompatActivity {

    RecyclerView item_peoplelike_list;
    PeopleLikeAdapter peopleLikeAdapter;
    TextView txt_next_homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_like);

        item_peoplelike_list = findViewById(R.id.item_peoplelike_list);
        txt_next_homepage = findViewById(R.id.txt_next_homepage);
        txt_next_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PeopleLikeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });
        peopleLikeAdapter = new PeopleLikeAdapter(PeopleLikeActivity.this);
        item_peoplelike_list.setLayoutManager(new LinearLayoutManager(PeopleLikeActivity.this, LinearLayoutManager.VERTICAL, false));
        item_peoplelike_list.setAdapter(peopleLikeAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PeopleLikeActivity.this, EditprofileActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}