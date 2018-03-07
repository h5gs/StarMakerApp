package com.archirayan.starmakerapp.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.data.FrameToRecord;
import com.archirayan.starmakerapp.data.RecordFragment;
import com.archirayan.starmakerapp.util.CameraHelper;
import com.archirayan.starmakerapp.util.FixedRatioCroppedTextureView;
import com.archirayan.starmakerapp.utils.Utility;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameFilter;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import static com.facebook.FacebookSdk.getApplicationContext;
import static java.lang.Thread.State.WAITING;

public class RecordingSongs extends AppCompatActivity {

    private static final String LOG_TAG = "RecordingSongs";
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private ImageView main_start;
    private TextView tv,txt_video;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recording_songs);

        tv = (TextView) findViewById(R.id.txt_count);
        pb = (ProgressBar) findViewById(R.id.pb);
        txt_video = findViewById(R.id.txt_video);
        final Dialog dialog = new Dialog(RecordingSongs.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.headphone_recommend_dialog);
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        TextView txt_know = dialog.findViewById(R.id.txt_know);
        txt_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startProgressbar();
                txt_video.setVisibility(View.VISIBLE);
            }
        });
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        main_start = findViewById(R.id.main_start);

    }

    private void startProgressbar() {
        // Set the progress status zero on each button click
        progressStatus = 0;

        // Start the lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    // Update the progress status
                    progressStatus +=1;

                    // Try to sleep the thread for 20 milliseconds
                    try{
                        Thread.sleep(20);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);
                            // Show the progress on TextView
                            tv.setText(progressStatus+"");
                            // If task execution completed
                            if(progressStatus == 100){
                                // Set a message of completion
                                pb.setVisibility(View.GONE);
                                tv.setVisibility(View.GONE);
                                main_start.setVisibility(View.VISIBLE);

                            }
                        }
                    });
                }
            }
        }).start(); // Start the operation
    }
}
