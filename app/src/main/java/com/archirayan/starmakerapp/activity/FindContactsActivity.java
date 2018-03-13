package com.archirayan.starmakerapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.archirayan.starmakerapp.R;

public class FindContactsActivity extends AppCompatActivity {

    Button btn_findcontacts;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findcontacts);
        btn_findcontacts=findViewById(R.id.btn_findcontacts);
        btn_findcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(FindContactsActivity.this, FindContactsListActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });
    }
}