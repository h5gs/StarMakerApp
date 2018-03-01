package com.archirayan.starmakerapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;

public class Grantpermissons extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_close;
    private TextView txt_okpermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grantpermissons);

        iv_close = findViewById(R.id.iv_close);
        txt_okpermission = findViewById(R.id.txt_okpermission);

        iv_close.setOnClickListener(this);
        txt_okpermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                onBackPressed();
                break;
            case R.id.txt_okpermission:

        }
    }
}
