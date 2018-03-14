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
import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginwithMobileActivity extends AppCompatActivity implements View.OnClickListener, CountryCodePicker.OnCountryChangeListener {
    public static String str_countCode;
    public ProgressDialog pd;
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
    private void Init() {
        if (!Utils.ReadSharePrefrence(LoginwithMobileActivity.this, Constant.LoginWith_MobileNO).equals("")) {
            startActivity(new Intent(LoginwithMobileActivity.this, VerifyingOTPActivity.class));
            finish();
        } else {

        }
        ll_mobilenumber.setVisibility(View.VISIBLE);
        btn_mobilelogin.setOnClickListener(this);
    }

    // // TODO: 22/2/18  Allocate the All Element ID ...
    private void FindviewById() {
        ccp = findViewById(R.id.ccp);
        ccp.setOnCountryChangeListener(this);
        btn_mobilelogin = findViewById(R.id.btn_mobilelogin);
        edit_phonenumber = findViewById(R.id.edit_phonenumber);
        ll_mobilenumber = findViewById(R.id.ll_mobilenumber);
        ll_verify_progress = findViewById(R.id.ll_verify_progress);
        ll_verify_otp_sent = findViewById(R.id.ll_verify_otp_sent);
        str_countCode = ccp.getDefaultCountryCode();
        Log.d("Country", "========= To ===========" + str_countCode);
    }


    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mobilelogin:
                if (edit_phonenumber.getText().toString().isEmpty()) {
                    edit_phonenumber.setError("Please enter phone no.");
                } else {
                    Utils.hideSoftKeyboard(LoginwithMobileActivity.this);
                    LoginMobileSmsGatway(str_countCode, edit_phonenumber.getText().toString());

//                    ll_mobilenumber.setVisibility(View.GONE);
//                    ll_verify_progress.setVisibility(View.VISIBLE);
//                    Handler handler = new Handler();
//                    final Handler handler1 = new Handler();
//                    handler.postDelayed(new Runnable()
//                    {
//                        @Override
//                        public void run() {
//                            ll_verify_progress.setVisibility(View.GONE);
//                            ll_verify_otp_sent.setVisibility(View.VISIBLE);
//                            handler1.postDelayed(new Runnable()
//                            {
//                                @Override
//                                public void run()
//                                {
//                                    ll_verify_otp_sent.setVisibility(View.GONE);
//                                    startActivity(new Intent(LoginwithMobileActivity.this, VerifyingOTPActivity.class));
//                                    finish();
//                                }
//                            }, 2000);
//                        }
//                    }, 2000);
                }
                break;
            default:
                break;
        }
    }

    private void LoginMobileSmsGatway(String str_countCode, String mobile) {
        if (Utils.isConnectingToInternet(LoginwithMobileActivity.this)) {
            SendSMS(mobile);
        } else {
            Toast.makeText(LoginwithMobileActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void SendSMS(String mobile) {
        pd = new ProgressDialog(LoginwithMobileActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Log.d("CountryANdNumber()", "=============  Tame Right cho ============" + str_countCode + mobile);
        RestClient.getStarCreator().MobileSmsOtp(str_countCode + mobile, new Callback<Response>()
        {
            @Override
            public void success(Response response, Response response2)
            {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    Log.v("", "===== Json =====: " + jsonObject.toString());
                    if (jsonObject.getString("status").toString().equals("true"))
                    {
                        Utils.WriteSharePrefrence(LoginwithMobileActivity.this, Constant.LoginWith_MobileNO, edit_phonenumber.getText().toString());
                        Utils.WriteSharePrefrence(LoginwithMobileActivity.this, Constant.USERID, edit_phonenumber.getText().toString());
                        Toast.makeText(LoginwithMobileActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        startActivity(new Intent(LoginwithMobileActivity.this, VerifyingOTPActivity.class));
                        finish();
                    } else {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(LoginwithMobileActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    pd.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                pd.dismiss();
                Toast.makeText(LoginwithMobileActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onCountrySelected(Country country) {
        str_countCode = country.getPhoneCode();
        Log.d("Country", "========= To ===========" + str_countCode);
    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}