package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.utils.Utility;

public class Grantpermissons extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_close;
    private TextView txt_okpermission;
    private boolean permissionTocameraAccepted = false;
    private boolean permissionTostorageAccepted = false;
    private boolean permissionToRecordAccepted = false;


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
                boolean result = Utility.checkPermission(Grantpermissons.this);
                if (result) {
                    startActivity(new Intent(Grantpermissons.this,LiveActivity.class));
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                permissionTocameraAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }

    }
}
