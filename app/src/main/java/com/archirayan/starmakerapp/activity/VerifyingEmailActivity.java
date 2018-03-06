//package com.archirayan.starmakerapp.activity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.archirayan.starmakerapp.R;
//import com.archirayan.starmakerapp.retrofit.RestClient;
//import com.archirayan.starmakerapp.utils.Constant;
//import com.archirayan.starmakerapp.utils.Util;
//import com.archirayan.starmakerapp.utils.Utils;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import cz.msebera.android.httpclient.Header;
//import retrofit.Callback;
//import retrofit.RetrofitError;
//import retrofit.client.Response;
//
//
//public class VerifyingEmailActivity extends AppCompatActivity {
//    Button btn_emailopen;
//    TextView txt_resendemail;
//    private ProgressDialog progress;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_verifying_email);
//        btn_emailopen = findViewById(R.id.btn_emailopen);
//
//        txt_resendemail = findViewById(R.id.txt_resendemail);
//        txt_resendemail.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                startActivity(new Intent(VerifyingEmailActivity.this, SignUpActivity.class));
//                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
//                finish();
//            }
//        });
//
//        btn_emailopen.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setType("text/plain");
//                startActivity(emailIntent);
//            }
//        });
//    }
//
//
//    @Override
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        startActivity(new Intent(VerifyingEmailActivity.this, SignUpActivity.class));
//        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
//        finish();
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        emailVerificayion();
//    }
//
//    private void emailVerificayion()
//    {
//        progress = new ProgressDialog(VerifyingEmailActivity.this);
//        progress.setMessage("Please wait...");
//        progress.setCancelable(false);
//        progress.show();
//        Log.e("MAIL()", "===== () ======" + Utils.ReadSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL));
//        RestClient.getMutualTransfer().VerifiedEmail(Utils.ReadSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL), new Callback<Response>() {
//            @Override
//            public void success(Response response, Response response2) {
//                progress.dismiss();
//                Log.e("Responce()", "===== () ======" + response);
//                try {
//                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
//
//                    if (jsonObject.getString("status").equals("true"))
//                    {
//
//                        JSONObject jsonObj = jsonObject.getJSONObject("data");
//                        Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_USERID, jsonObj.getString("id"));
//                        Intent intent = new Intent(VerifyingEmailActivity.this, VerifiedEmailOtpActivity
//                                .class);
//                        intent.putExtra("otp", jsonObject.getString("OTP"));
//                        intent.putExtra("id", jsonObj.getString("id"));
//                        startActivity(intent);
//                        finish();
//                    } else {
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                progress.dismiss();
//                Toast.makeText(VerifyingEmailActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//
////        AsyncHttpClient client = new AsyncHttpClient();
////        RequestParams requestParams = new RequestParams();
////        requestParams.put("email_address", Utils.ReadSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL));
////        Log.e("Respoonce()", "http://web-medico.com/web1/StarCreator/APIs/verified.php?" + requestParams);
////        client.post("http://web-medico.com/web1/StarCreator/APIs/verified.php?", requestParams, new JsonHttpResponseHandler() {
////            @Override
////            public void onStart() {
////                super.onStart();
////                ShowProgress();
////            }
////
////            @Override
////            public void onFinish() {
////                super.onFinish();
////                Hideprogress();
////
////            }
////
////            @Override
////            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
////                super.onSuccess(statusCode, headers, response);
////                Log.e("Result()", "Login RESPONSE-" + response);
////
////                if (!response.equals("")) {
//////                    Login login = new Gson().fromJson(new String(String.valueOf(response)), Login.class);
//////                    if (login.status.equals("true")) {
//////                        Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.USERID, login.data.user_id);
//////                        Intent intent = new Intent(VerifyingEmailActivity.this, MainActivity.class);
//////                        intent.putExtra("username", fbfirst_name);
//////                        startActivity(intent);
//////                        finish();
//////                    }
//////                    else
//////                    {
//////                        Toast.makeText(VerifyingEmailActivity.this, login.msg, Toast.LENGTH_SHORT).show();
//////                    }
////
////
////                    JSONObject jsonObject = null;
////                    try {
////                        jsonObject = new JSONObject(String.valueOf(response));
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////
////                    if (jsonObject.getString("status").equals("true"))
////                    {
////                        JSONObject jsonObj = jsonObject.getJSONObject("data");
////                        // Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_USERID, jsonObj.getString("id"));
////                        Intent intent = new Intent(VerifyingEmailActivity.this, VerifiedEmailOtpActivity.class);
////                        intent.putExtra("otp", jsonObject.getString("OTP"));
////                        intent.putExtra("id", jsonObj.getString("id"));
////                        startActivity(intent);
////                        finish();
////                    } else {
////                    }
////                } else {
////                    Toast.makeText(VerifyingEmailActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
////                }
////
////
////            }
////
////            @Override
////            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
////                super.onFailure(statusCode, headers, responseString, throwable);
////                Log.e("Error()", throwable.getMessage());
////            }
////        });
//
//    private void Hideprogress() {
//        // TODO Auto-generated method stub
//        if (progress.isShowing()) {
//            progress.dismiss();
//        }
//    }
//
//    private void ShowProgress() {
//        // TODO Auto-generated method stub
//        progress = new ProgressDialog(VerifyingEmailActivity.this);
//        progress.setMessage("Please wait...");
//        progress.setCancelable(false);
//        progress.show();
//    }
//
//
//    //    {
////        progress = new ProgressDialog(VerifyingEmailActivity.this);
////        progress.setMessage("Please wait...");
////        progress.setCancelable(false);
////        progress.show();
////        Log.e("MAIL()", "===== () ======" + Utils.ReadSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL));
////        RestClient.getMutualTransfer().VerifiedEmail(Utils.ReadSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL), new Callback<Response>() {
////            @Override
////            public void success(Response response, Response response2) {
////                progress.dismiss();
////                Log.e("Responce()", "===== () ======" + response);
////                try {
////                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
////
////                    if (jsonObject.getString("status").equals("true"))
////                    {
////
////                        JSONObject jsonObj = jsonObject.getJSONObject("data");
////                        Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_USERID, jsonObj.getString("id"));
////                        Intent intent = new Intent(VerifyingEmailActivity.this, VerifiedEmailOtpActivity
////                                .class);
////                        intent.putExtra("otp", jsonObject.getString("OTP"));
////                        intent.putExtra("id", jsonObj.getString("id"));
////                        startActivity(intent);
////                        finish();
////                    } else {
////                    }
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////
////            @Override
////            public void failure(RetrofitError error) {
////                progress.dismiss();
////                Toast.makeText(VerifyingEmailActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
//}




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
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
               // Intent intent = new Intent(VerifyingEmailActivity.this, EditprofileActivity.class);
              //  startActivity(intent);
            }
        });
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
       // // startTimer();
        //Utils.WriteSharePrefrence(context, Constant.SIGNUP_PREF_CHECK_EMAIL, "0");
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

                JSONObject jsonObject = null;
                try
                {
                    jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    if (jsonObject.getString("status").equals("true"))
                        {
                            JSONObject jsonObj=jsonObject.getJSONObject("data");
                            Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_USERID, jsonObj.getString("id"));
                            Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.SIGNUP_PREF_EMAIL, jsonObj.getString("email_address"));
                            Intent intent = new Intent(VerifyingEmailActivity.this, VerifiedEmailOtpActivity
                                    .class);
                            intent.putExtra("otp",jsonObject.getString("OTP"));
                            intent.putExtra("id",jsonObj.getString("id"));
                            startActivity(intent);
                            finish();
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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