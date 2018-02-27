package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.EventsAdapter;

public class EventsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_eventlist;
    private EventsAdapter eventsAdapter;
    private ImageView iv_backevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recycler_eventlist = findViewById(R.id.recycler_eventlist);
        recycler_eventlist.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        eventsAdapter = new EventsAdapter(this);
        recycler_eventlist.setAdapter(eventsAdapter);

        iv_backevents = findViewById(R.id.iv_backevents);

        iv_backevents.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backevents:
                onBackPressed();
                break;
        }

    }
}
