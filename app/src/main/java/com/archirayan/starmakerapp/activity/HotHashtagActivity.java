package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.HashtagAdapter;

public class HotHashtagActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_backhashtag;
    private RecyclerView recycler_hashtag;
    private HashtagAdapter hashtagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_hashtag);

        iv_backhashtag = findViewById(R.id.iv_backhashtag);

        recycler_hashtag = findViewById(R.id.recycler_hashtag);
        recycler_hashtag.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        hashtagAdapter = new HashtagAdapter(this);
        recycler_hashtag.setAdapter(hashtagAdapter);

        iv_backhashtag.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backhashtag:
                onBackPressed();
                break;
        }
    }
}
