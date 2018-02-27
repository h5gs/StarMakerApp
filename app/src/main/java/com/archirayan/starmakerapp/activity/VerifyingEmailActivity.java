package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.Login;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class VerifyingEmailActivity extends AppCompatActivity
{
    Button btn_emailopen;
    TextView txt_resendemail;
    private ProgressDialog progress;
    private String str_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_email);
        btn_emailopen = findViewById(R.id.btn_emailopen);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            str_Email = bundle.getString("emailid");
        }

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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(VerifyingEmailActivity.this, SignUpActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        emailVerificayion(str_Email);
    }

    private void emailVerificayion(String str_Email)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.put("email_address", str_Email);
        Log.d("Login()", "http://web-medico.com/web1/StarCreator/APIs/verified.php?" + requestParams);
        client.post("http://web-medico.com/web1/StarCreator/APIs/verified.php?", requestParams, new JsonHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
                ShowProgress();
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
                Hideprogress();

            }

            @Override

            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                Log.d("Login()", "Login RESPONSE-" + response);
                if (!response.equals(""))
                {
                    Login login = new Gson().fromJson(new String(String.valueOf(response)), Login.class);
                    if (login.status.equals("true"))
                    {
                        Utils.WriteSharePrefrence(VerifyingEmailActivity.this, Constant.USERID, login.data.user_id);
                        Intent intent = new Intent(VerifyingEmailActivity.this, SignuppasswordActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(VerifyingEmailActivity.this, login.msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(VerifyingEmailActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
            {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
    private void Hideprogress() {
        // TODO Auto-generated method stub
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    private void ShowProgress() {
        // TODO Auto-generated method stub
        progress = new ProgressDialog(VerifyingEmailActivity.this);
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
    }
}