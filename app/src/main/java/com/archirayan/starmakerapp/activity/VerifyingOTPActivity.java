package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;

public class VerifyingOTPActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_mobilelogin;
    TextView txt_didnotgetotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_otp);
        btn_mobilelogin = findViewById(R.id.btn_mobilelogin);
        txt_didnotgetotp=findViewById(R.id.txt_didnotgetotp);
        btn_mobilelogin.setOnClickListener(this);
        txt_didnotgetotp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_mobilelogin:
                startActivity(new Intent(VerifyingOTPActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.txt_didnotgetotp:
                startActivity(new Intent(VerifyingOTPActivity.this, DidnotgetOTPActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(VerifyingOTPActivity.this, LoginwithMobileActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}