package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.adapter.MainAdapter;
import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.fragments.AwardFragment;
import com.archirayan.starmakerapp.fragments.NotificationFragment;
import com.archirayan.starmakerapp.fragments.ProfileFragment;
import com.archirayan.starmakerapp.fragments.RecordFragment;
import com.archirayan.starmakerapp.fragments.SongFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView rvList;
    MainAdapter mainAdapter;
    ImageView ivSong,ivAward,ivRecord,ivNotification,ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivSong = findViewById(R.id.main_song);
        ivAward = findViewById(R.id.main_award);
        ivRecord = findViewById(R.id.main_record);
        ivNotification = findViewById(R.id.main_notification);
        ivProfile = findViewById(R.id.main_profile);

        ivSong.setOnClickListener(this);
        ivAward.setOnClickListener(this);
        ivRecord.setOnClickListener(this);
        ivNotification.setOnClickListener(this);
        ivProfile.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, new RecordFragment()).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_song:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new SongFragment()).commit();
                break;

            case R.id.main_award:
                getSupportFragmentManager().beginTransaction().add(R.id.main_container, new AwardFragment()).commit();
                break;

            case R.id.main_record:
                getSupportFragmentManager().beginTransaction().add(R.id.main_container, new RecordFragment()).commit();
                break;

            case R.id.main_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new NotificationFragment()).commit();
                break;

            case R.id.main_profile:
               getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ProfileFragment()).commit();
                break;
        }
    }
}