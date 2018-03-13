package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.LoginResponse;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public ProgressDialog pd;
    Button btn_login;
    ImageView img_back_title_Dsignin;
    EditText edit_email, edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        }
        return super.onOptionsItemSelected(item);
    }

    private void init()
    {
        btn_login = findViewById(R.id.btn_login);

        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);

        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (edit_email.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                }
                else if (edit_password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    login(edit_email.getText().toString(), edit_password.getText().toString());
                }
            }
        });
    }

    private void login(String email, String password)
    {
        if (Utils.isConnectingToInternet(LoginActivity.this)) {
            loginCallApi(email, password);
        } else {
            Toast.makeText(LoginActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginCallApi(String email, String password)
    {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email_address",edit_email.getText().toString());
        params.put("password",edit_password.getText().toString());

        Log.e(TAG, "DriverURL:" + Constant.URL + "login.php?" + params);
        Log.e(TAG, params.toString());
        client.post(this, Constant.URL+"login.php?",params, new JsonHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
            }
            @Override
            public void onFinish()
            {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "LOGIN DriverRESPONSE-" + response);
                LoginResponse model = new Gson().fromJson(new String(String.valueOf(response)),LoginResponse.class);
                pd.dismiss();
                if (model.getStatus().equalsIgnoreCase("true"))
                {
                    Utils.WriteSharePrefrence(LoginActivity.this, Constant.USERID,model.getData().getId());
                    String Userid = Utils.ReadSharePrefrence(LoginActivity.this,Constant.USERID);
                    Log.e(TAG,"READ SHARED==>"+Userid);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, model.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}