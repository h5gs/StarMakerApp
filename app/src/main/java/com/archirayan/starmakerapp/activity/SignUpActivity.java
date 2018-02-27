package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.archirayan.starmakerapp.R;
import com.rilixtech.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_emaillogin;
    CountryCodePicker ccp;
    EditText edit_emailaddress;
    LinearLayout ll_emailaddress, ll_verify_progress, ll_verify_otp_sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FindviewById();
        Init();

    }
    // // TODO: 22/2/18 Initialize the Variable ...

    private void Init() {
        ll_emailaddress.setVisibility(View.VISIBLE);
        btn_emaillogin.setOnClickListener(this);

    }

    // // TODO: 22/2/18  Allocate the All Element ID ...
    private void FindviewById() {
        ccp = findViewById(R.id.ccp);
        btn_emaillogin = findViewById(R.id.btn_emaillogin);
        edit_emailaddress = findViewById(R.id.edit_emailaddress);
        ll_emailaddress = findViewById(R.id.ll_emailaddress);
        ll_verify_progress = findViewById(R.id.ll_verify_progress);
        ll_verify_otp_sent = findViewById(R.id.ll_verify_otp_sent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_emaillogin:
                if (edit_emailaddress.getText().toString().isEmpty()) {
                    edit_emailaddress.setError("Please enter email id");
                } else {
                    ll_emailaddress.setVisibility(View.GONE);
                    ll_verify_progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    final Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ll_verify_progress.setVisibility(View.GONE);
                            ll_verify_otp_sent.setVisibility(View.VISIBLE);
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ll_verify_otp_sent.setVisibility(View.GONE);
                                    startActivity(new Intent(SignUpActivity.this, VerifyingEmailActivity.class));
                                    finish();

                                }
                            }, 2000);
                        }
                    }, 5000);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}