package com.archirayan.starmakerapp.Receiver;

/**
 * Created by archirayan on 1/3/18.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.archirayan.starmakerapp.activity.VerifiedEmailOtpActivity;
import com.archirayan.starmakerapp.activity.VerifyingEmailActivity;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import static android.content.Context.ALARM_SERVICE;

public class AlarmReceiver extends BroadcastReceiver
{
    public Context context;
    public String timestamp;

    @Override
    public void onReceive(final Context context, Intent intent)
    {
        this.context = context;
        nextAlarm(context);
        getCurrentTimeDate();
    }


    @TargetApi(Build.VERSION_CODES.N)
    private void nextAlarm(Context context)
    {
        Log.d("startTimer", " =============== Method called ================");
        AlarmManager alarmMgr0 = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent0 = new Intent(context, AlarmReceiver.class);
        //Making pending intet which is called by alarm
        PendingIntent sender = PendingIntent.getBroadcast(context, 1002, intent0, 0);
        intent0 = new Intent(context, VerifyingEmailActivity.class);
        //Seting local calender
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
        calendar.set(Calendar.SECOND, 0);
        alarmMgr0.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        if (Utils.ReadSharePrefrence(context, Constant.SIGNUP_PREF_CHECK_EMAIL).equals("0"))
        {
            emailVerificayion();
        }
    }

    private void getCurrentTimeDate()
    {
        DateFormat df = new SimpleDateFormat("h:mm a");
        timestamp = String.valueOf((df.format(Calendar.getInstance().getTime())));
    }

    private void emailVerificayion()
    {
        RestClient.getMutualTransfer().VerifiedEmail(Utils.ReadSharePrefrence(context, Constant.SIGNUP_PREF_EMAIL), new Callback<Response>()
        {
            @Override
            public void success(Response response, Response response2)
            {
                try {
                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    if (jsonObject.getString("status").equals("true"))
                    {
                        Utils.WriteSharePrefrence(context, Constant.SIGNUP_PREF_CHECK_EMAIL, "1");
                        JSONObject jsonObj=jsonObject.getJSONObject("data");
                        Utils.WriteSharePrefrence(context, Constant.SIGNUP_PREF_USERID, jsonObj.getString("id"));
                        //Utils.WriteSharePrefrence(context, Constant.SIGNUP_PREF_EMAIL, jsonObj.getString("email_address"));
                        Intent intent = new Intent(context, VerifiedEmailOtpActivity.class);
                        intent.putExtra("otp",jsonObject.getString("OTP"));
                        intent.putExtra("id",jsonObj.getString("id"));
                        context.startActivity(intent);
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
            }
        });
    }
}