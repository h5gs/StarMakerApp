package com.archirayan.starmakerapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.utils.Utils;

import java.util.List;

public class ProfileSettingsActivity extends AppCompatActivity {

    ImageView img_back;
    TextView txt_user_logout;
    LinearLayout ll_laout_faq,ll_laout_about,ll_laout_feedback,ll_laout_editallprofile;
    private String str_emailSend;
    private String str_emailsubject;
    private String str_emailcontent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitleBar();
        init();


    }

    //Toolbar title
    public void setTitleBar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Setting");
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
        txt_user_logout = findViewById(R.id.txt_user_logout);
        ll_laout_faq=findViewById(R.id.ll_laout_faq);
        ll_laout_about=findViewById(R.id.ll_laout_about);
        ll_laout_feedback=findViewById(R.id.ll_laout_feedback);
        ll_laout_editallprofile=findViewById(R.id.ll_laout_editallprofile);

        txt_user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingsActivity.this);
                builder.setMessage("Are you sure you want to log out ?")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Utils.ClearaSharePrefrence(ProfileSettingsActivity.this);
                                Intent intent = new Intent(ProfileSettingsActivity.this, WelcomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        ll_laout_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ProfileSettingsActivity.this, FAQWebviewActivity.class);
                startActivity(intent);
            }
        });

        ll_laout_about.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ProfileSettingsActivity.this, AboutusActivity.class);
                startActivity(intent);
            }
        });

        ll_laout_feedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                str_emailSend="archirayan35@gmail.com";
                str_emailsubject="Star Creator_Android_1.0";
                str_emailcontent="Thank you for your feedback! The following information helps us iodentifybugs and solve your problems!";
                openGmail(ProfileSettingsActivity.this,str_emailSend,str_emailsubject,str_emailcontent);
            }
        });

        ll_laout_editallprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileSettingsActivity.this, EditAllProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }



    // // TODO: 28/2/18  Open Gmail Application ...
    public static void openGmail(ProfileSettingsActivity profileSettingsActivity, String str_emailSend, String str_emailsubject, String str_emailcontent)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, str_emailSend);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, str_emailsubject);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, str_emailcontent);
        final PackageManager pm = profileSettingsActivity.getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
        ResolveInfo best = null;
        for(final ResolveInfo info : matches)
            if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
                best = info;
        if (best != null)
            emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
        profileSettingsActivity.startActivity(emailIntent);
    }
}
