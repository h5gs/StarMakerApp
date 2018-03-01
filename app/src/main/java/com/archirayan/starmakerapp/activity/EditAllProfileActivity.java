package com.archirayan.starmakerapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;

public class EditAllProfileActivity extends AppCompatActivity
{
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_all_profile);
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
