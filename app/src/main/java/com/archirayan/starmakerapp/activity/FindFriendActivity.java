package com.archirayan.starmakerapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.FindFreindsAdapter;
import com.archirayan.starmakerapp.adapter.MainAdapter;

public class FindFriendActivity extends AppCompatActivity {
    ImageView img_back;
    RecyclerView rvList;
    FindFreindsAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });

        rvList = findViewById(R.id.item_list);
        mainAdapter = new FindFreindsAdapter(FindFriendActivity.this);
        rvList.setLayoutManager(new LinearLayoutManager(FindFriendActivity.this, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(mainAdapter);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
