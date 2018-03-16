package com.archirayan.starmakerapp.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.archirayan.starmakerapp.R;

public class PlaysongfullActivity extends AppCompatActivity {

    VideoView iv_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playsongfull);

//
//        iv_video=findViewById(R.id.iv_video);
//        String path = getSongLists.get(position).getVideo();
//        iv_video.setVideoURI(Uri.parse(path));

    }
}
