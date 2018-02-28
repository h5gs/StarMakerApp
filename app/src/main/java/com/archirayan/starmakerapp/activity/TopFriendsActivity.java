package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.TopAllFriendsAdapter;

public class TopFriendsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_topallfriends;
    private ImageView iv_backtopfriends;
    private TopAllFriendsAdapter topAllFriendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_friends);

        iv_backtopfriends = findViewById(R.id.iv_backtopfriends);

        recycler_topallfriends = findViewById(R.id.recycler_topallfriends);
        recycler_topallfriends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        topAllFriendsAdapter = new TopAllFriendsAdapter(this);
        recycler_topallfriends.setAdapter(topAllFriendsAdapter);
        iv_backtopfriends.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backtopfriends:
                onBackPressed();
                break;
        }
    }
}
