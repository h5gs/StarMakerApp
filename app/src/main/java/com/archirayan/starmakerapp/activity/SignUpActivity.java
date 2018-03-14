package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;
import com.rilixtech.CountryCodePicker;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_emaillogin;
    CountryCodePicker ccp;
    EditText edit_emailaddress;
    LinearLayout ll_emailaddress;
    private ProgressDialog pd;
    private ProgressDialog progress;

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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_emaillogin:
                if (edit_emailaddress.getText().toString().isEmpty()) {
                    edit_emailaddress.setError("Please enter email id");
                } else {
                    SignUp(edit_emailaddress.getText().toString());
//
//                    Handler handler = new Handler();
//                    final Handler handler1 = new Handler();
//                    handler.postDelayed(new Runnable()
//                    {
//                        @Override
//                        public void run()
//                        {
//                            ll_verify_progress.setVisibility(View.GONE);
//                            ll_verify_otp_sent.setVisibility(View.VISIBLE);
//                            handler1.postDelayed(new Runnable()
//                            {
//                                @Override
//                                public void run()
//                                {
//                                    ll_verify_otp_sent.setVisibility(View.GONE);
//                                    startActivity(new Intent(SignUpActivity.this, VerifyingEmailActivity.class));
//                                    finish();
//
//                                }
//                            }, 2000);
//                        }
//                    }, 5000);

                }
                break;
            default:
                break;
        }
    }

    private void SignUp(String email)
    {
        if (Utils.isConnectingToInternet(SignUpActivity.this)) {
            signupCallApi(email);
        } else {
            Toast.makeText(SignUpActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void signupCallApi(String email)
    {
        pd = new ProgressDialog(SignUpActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(true);
        pd.show();
        RestClient.getStarCreator().SignUp(email, new Callback<Response>()
        {
            @Override
            public void success(Response response, Response response2)
            {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    Log.v("", "===== Json =====: " + jsonObject.toString());
                    if (jsonObject.getString("status").toString().equals("true")) {
                        Toast.makeText(SignUpActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        Intent intent = new Intent(SignUpActivity.this, VerifyingEmailActivity.class);
                        Utils.WriteSharePrefrence(SignUpActivity.this, Constant.SIGNUP_PREF_EMAIL, edit_emailaddress.getText().toString());
                        startActivity(intent);
                        finish();
                    } else {
                        pd.dismiss();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(SignUpActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        //  emailVerificayion(edit_emailaddress.getText().toString());
                    }
                } catch (Exception e) {
                    pd.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                pd.dismiss();
                Toast.makeText(SignUpActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void emailVerificayion(String str_Email) {
        pd = new ProgressDialog(SignUpActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        try {
            RestClient.getStarCreator().VerifiedEmail(str_Email, new Callback<Response>() {

                @Override
                public void success(Response response, Response response2) {
                    pd.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                        Log.v("", "===== Json =====: " + jsonObject.toString());
                        if (jsonObject.getString("status").toString().equals("true")) {
                            JSONObject array = jsonObject.getJSONObject("data");
                            Toast.makeText(SignUpActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            Intent intent = new Intent(SignUpActivity.this, VerifiedEmailOtpActivity.class);
                            intent.putExtra("otp", jsonObject.getString("OTP"));
                            // Utils.WriteSharePrefrence(SignUpActivity.this, Constant.SIGNUP_PREF_OTP, jsonObject.getString("OTP"));
                            Utils.WriteSharePrefrence(SignUpActivity.this, Constant.USERID, array.getString("id"));
                            Utils.WriteSharePrefrence(SignUpActivity.this, Constant.SIGNUP_PREF_EMAIL, array.getString("email_address"));
                            startActivity(intent);
                            finish();
                        } else {
                            pd.dismiss();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(SignUpActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        pd.dismiss();
                        e.printStackTrace();
                        Toast.makeText(SignUpActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    pd.dismiss();
                    Toast.makeText(SignUpActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Hideprogress() {
        // TODO Auto-generated method stub
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    private void ShowProgress() {
        // TODO Auto-generated method stub
        progress = new ProgressDialog(SignUpActivity.this);
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
    }

}