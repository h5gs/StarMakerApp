package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.chaos.view.PinView;

public class VerifiedEmailOtpActivity extends AppCompatActivity
{
    Button btn_otpcheck;
    TextView txt_emailname;
    PinView pinView;
    private ProgressDialog progress;
    private String str_Otp,str_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_email_otp);
        txt_emailname = findViewById(R.id.txt_emailname);
        pinView = findViewById(R.id.pinView);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            str_Otp = b.getString("otp");
            str_Id = b.getString("id");
        }

        btn_otpcheck = findViewById(R.id.btn_otpcheck);
        btn_otpcheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (pinView.getText().toString().equals(str_Otp))
                {
                    Intent intent = new Intent(VerifiedEmailOtpActivity.this, SignuppasswordActivity.class);
                    intent.putExtra("id_user",str_Id);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(VerifiedEmailOtpActivity.this, "Otp does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}