package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.archirayan.starmakerapp.R;

public class DidnotgetOTPActivity extends AppCompatActivity {
    Button btn_sensmsagain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_didnotget_otp);
        btn_sensmsagain = findViewById(R.id.btn_sensmsagain);
        btn_sensmsagain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(DidnotgetOTPActivity.this, VerifyingOTPActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DidnotgetOTPActivity.this, VerifyingOTPActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }
}