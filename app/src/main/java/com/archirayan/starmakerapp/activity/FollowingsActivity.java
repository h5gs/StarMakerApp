package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.FollowersAdapter;
import com.archirayan.starmakerapp.adapter.FollowingsAdapter;

public class FollowingsActivity extends AppCompatActivity {

    RecyclerView item_peoplelike_list;
    FollowingsAdapter peopleLikeAdapter;
    TextView txt_next_homepage;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followings);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitleBar();
        item_peoplelike_list = findViewById(R.id.item_peoplelike_list);
        peopleLikeAdapter = new FollowingsAdapter(FollowingsActivity.this);
        item_peoplelike_list.setLayoutManager(new LinearLayoutManager(FollowingsActivity.this, LinearLayoutManager.VERTICAL, false));
        item_peoplelike_list.setAdapter(peopleLikeAdapter);
    }

    //Toolbar title
    public void setTitleBar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Followings");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}