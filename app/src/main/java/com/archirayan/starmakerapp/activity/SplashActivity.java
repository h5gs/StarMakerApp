package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;

public class SplashActivity extends AppCompatActivity
{
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run() {
                if (!Utils.ReadSharePrefrence(SplashActivity.this, Constant.USERID).equals(""))
                {
                    Intent in = new Intent(SplashActivity.this, MainActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();
                } else {
                    Intent in = new Intent(SplashActivity.this, WelcomeActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }



}