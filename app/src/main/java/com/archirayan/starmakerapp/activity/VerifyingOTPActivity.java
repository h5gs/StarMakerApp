package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;
import com.chaos.view.PinView;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class VerifyingOTPActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_mobilelogin;
    TextView txt_didnotgetotp, txt_mobileno;
    PinView pinView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_otp);
        btn_mobilelogin = findViewById(R.id.btn_mobilelogin);
        txt_didnotgetotp = findViewById(R.id.txt_didnotgetotp);
        pinView = findViewById(R.id.pinView);
        txt_mobileno = findViewById(R.id.txt_mobileno);
        btn_mobilelogin.setOnClickListener(this);
        txt_didnotgetotp.setOnClickListener(this);


        if (!Utils.ReadSharePrefrence(VerifyingOTPActivity.this, Constant.LoginWith_MobileNO).equals(""))
        {
            txt_mobileno.setText(Utils.ReadSharePrefrence(VerifyingOTPActivity.this, Constant.LoginWith_MobileNO));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mobilelogin:
                if (pinView.getText().toString().equals("")) {
                    Toast.makeText(VerifyingOTPActivity.this, "Please Enter Otp", Toast.LENGTH_SHORT).show();
                } else {
                    Otp_MatchSMS(pinView.getText().toString());
                }
                break;
            case R.id.txt_didnotgetotp:
                startActivity(new Intent(VerifyingOTPActivity.this, DidnotgetOTPActivity.class));
                finish();
                break;
        }
    }

    private void Otp_MatchSMS(String otp) {
        if (Utils.isConnectingToInternet(VerifyingOTPActivity.this)) {
            SendOTP(otp);
        } else {
            Toast.makeText(VerifyingOTPActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void SendOTP(String otp) {
        pd = new ProgressDialog(VerifyingOTPActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        RestClient.getStarCreator().OtpMatch(otp, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    Log.v("", "===== Json =====: " + jsonObject.toString());
                    if (jsonObject.getString("status").toString().equals("true")) {
                        Toast.makeText(VerifyingOTPActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        startActivity(new Intent(VerifyingOTPActivity.this, EditprofileActivity.class));
                        finish();
                    } else {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(VerifyingOTPActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    pd.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                pd.dismiss();
                Toast.makeText(VerifyingOTPActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}