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
import android.widget.ImageView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utils;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    public ProgressDialog pd;
    Button btn_login;
    ImageView img_back_title_Dsignin;
    EditText edit_email, edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        img_back_title_Dsignin = findViewById(R.id.img_back_title_Dsignin);

        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        img_back_title_Dsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_email.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                } else if (edit_password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    login(edit_email.getText().toString(), edit_password.getText().toString());
                }
            }
        });
    }

    private void login(String email, String password) {
        if (Utils.isConnectingToInternet(LoginActivity.this)) {
            loginCallApi(email, password);
        } else {
            Toast.makeText(LoginActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginCallApi(String email, String password) {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(true);
        pd.show();

        try {
            RestClient.getMutualTransfer().login(email, password, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    pd.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                        Log.v("", "===== Json =====: " + jsonObject.toString());
                        if (jsonObject.getString("status").toString().equals("true")) {

                            Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

                        } else {
                            pd.dismiss();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        pd.dismiss();
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    pd.dismiss();
                    Toast.makeText(LoginActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}