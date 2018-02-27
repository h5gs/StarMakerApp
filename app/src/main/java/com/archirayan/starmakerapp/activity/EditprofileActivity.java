package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.SuggestedStageNameAdapter;

public class EditprofileActivity extends AppCompatActivity
{
    RecyclerView recycler_suggestion;
    SuggestedStageNameAdapter followingAdapter;
Button btn_profile_save;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        btn_profile_save=findViewById(R.id.btn_profile_save);
        btn_profile_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(EditprofileActivity.this, FindYourFreindsActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });
        recycler_suggestion = findViewById(R.id.recycler_suggestion);
        followingAdapter = new SuggestedStageNameAdapter(EditprofileActivity.this);
        recycler_suggestion.setLayoutManager(new LinearLayoutManager(EditprofileActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recycler_suggestion.setAdapter(followingAdapter);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(EditprofileActivity.this,SignUpActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}