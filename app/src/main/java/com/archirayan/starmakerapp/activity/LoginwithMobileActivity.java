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
import com.archirayan.starmakerapp.utils.Utils;
import com.rilixtech.CountryCodePicker;

public class LoginwithMobileActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_mobilelogin;
    CountryCodePicker ccp;
    EditText edit_phonenumber;
    LinearLayout ll_mobilenumber, ll_verify_progress, ll_verify_otp_sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwith_mobile);
        FindviewById();
        Init();

    }
    // // TODO: 22/2/18 Initialize the Variable ...


    private void Init()
    {
        ll_mobilenumber.setVisibility(View.VISIBLE);
        btn_mobilelogin.setOnClickListener(this);
    }

    // // TODO: 22/2/18  Allocate the All Element ID ...
    private void FindviewById() {
        ccp = findViewById(R.id.ccp);
        btn_mobilelogin = findViewById(R.id.btn_mobilelogin);
        edit_phonenumber = findViewById(R.id.edit_phonenumber);
        ll_mobilenumber = findViewById(R.id.ll_mobilenumber);
        ll_verify_progress = findViewById(R.id.ll_verify_progress);
        ll_verify_otp_sent = findViewById(R.id.ll_verify_otp_sent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mobilelogin:
                if (edit_phonenumber.getText().toString().isEmpty()) {
                    edit_phonenumber.setError("Please enter phone no.");
                } else {
                    Utils.hideSoftKeyboard(LoginwithMobileActivity.this);
                    ll_mobilenumber.setVisibility(View.GONE);
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
                                    startActivity(new Intent(LoginwithMobileActivity.this, VerifyingOTPActivity.class));
                                    finish();
                                }
                            }, 2000);
                        }
                    }, 2000);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginwithMobileActivity.this, WelcomeActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ll_mobilenumber.setVisibility(View.VISIBLE);
    }
}