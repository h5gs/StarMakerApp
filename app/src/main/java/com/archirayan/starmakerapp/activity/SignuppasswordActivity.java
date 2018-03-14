package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.Password;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignuppasswordActivity extends AppCompatActivity {

    ImageView img_back;
    Button btn_password_signup;
    EditText edit_password;
    String str_Id;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppassword);
        img_back = findViewById(R.id.img_back);
        btn_password_signup = findViewById(R.id.btn_password_signup);
        edit_password = findViewById(R.id.edit_password);


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        str_Id = b.getString("id_user");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignuppasswordActivity.this, SignUpActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });


        btn_password_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_password.getText().toString().isEmpty()) {
                    edit_password.setError("Please enter Password");
                } else if (edit_password.getText().length() <= 6) {
                    edit_password.setError("Please enter minimum 6 character");
                } else {
                    passwordsetSignUp();
                }
            }
        });
    }

    private void passwordsetSignUp() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.put("user_id", str_Id);
        requestParams.put("password", edit_password.getText().toString());
        Log.d("Login()", "http://web-medico.com/web1/StarCreator/APIs/set_password.php?" + requestParams);
        client.post("http://web-medico.com/web1/StarCreator/APIs/set_password.php?", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                ShowProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Hideprogress();

            }

            @Override

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Login()", "Login RESPONSE-" + response.toString());
                if (!response.equals("")) {
                    Password password = new Gson().fromJson(new String(String.valueOf(response)), Password.class);
                    if (password.status.equals("true")) {
                        Utils.WriteSharePrefrence(SignuppasswordActivity.this, Constant.USERID, str_Id);
                        Toast.makeText(SignuppasswordActivity.this, password.msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignuppasswordActivity.this, EditprofileActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignuppasswordActivity.this, password.msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignuppasswordActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignuppasswordActivity.this, SignUpActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }

    private void Hideprogress() {
        // TODO Auto-generated method stub
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    private void ShowProgress() {
        // TODO Auto-generated method stub
        progress = new ProgressDialog(SignuppasswordActivity.this);
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
    }
}