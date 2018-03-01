package com.archirayan.starmakerapp.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.Receiver.AlarmReceiver;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;

import org.json.JSONObject;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class VerifyingEmailActivity extends AppCompatActivity {
    Button btn_emailopen;
    TextView txt_resendemail;
    private ProgressDialog progress;


    public Intent intent;
    public Context context = this;
    public Calendar calendar;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_email);
        btn_emailopen = findViewById(R.id.btn_emailopen);
        calendar = Calendar.getInstance();
        //startTimer();
        //Utils.WriteSharePrefrence(context, Constant.SIGNUP_PREF_CHECK_EMAIL, "0");

        txt_resendemail = findViewById(R.id.txt_resendemail);
        txt_resendemail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(VerifyingEmailActivity.this, SignUpActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });

        btn_emailopen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });
    }


    // // TODO: 1/3/18 Broadcast Receivmer ...
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startTimer()
    {
        //startService(intent);
        AlarmManager alarmMgr0 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent0 = new Intent(this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1001, intent0, 0);
        intent0 = new Intent(this, VerifyingEmailActivity.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,30);
        alarmMgr0.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VerifyingEmailActivity.this, SignUpActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume()
    {
        super.onResume();
        emailVerificayion();
       // startTimer();
        Utils.WriteSharePrefrence(context, Constant.SIGNUP_PREF_CHECK_EMAIL, "0");
    }

    private void emailVerificayion()
    {
        progress = new ProgressDialog(VerifyingEmailActivity.this);
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
        RestClient.getMutualTransfer().VerifiedEmail(Utils.ReadSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL), new Callback<Response>()
        {
            @Override
            public void success(Response response, Response response2)
            {
                progress.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    if (jsonObject.getString("status").equals("true"))
                    {
                        JSONObject jsonObj=jsonObject.getJSONObject("data");
                        Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_USERID, jsonObj.getString("id"));
                        //Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL, jsonObj.getString("email_address"));
                        Intent intent = new Intent(VerifyingEmailActivity.this, VerifiedEmailOtpActivity
                                .class);
                        intent.putExtra("otp",jsonObject.getString("OTP"));
                        intent.putExtra("id",jsonObj.getString("id"));
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                progress.dismiss();
                Toast.makeText(VerifyingEmailActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}