package com.archirayan.starmakerapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.archirayan.starmakerapp.R;

public class EditAllProfileActivity extends AppCompatActivity
{
    ImageView img_back;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_all_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitleBar();
        init();

    }

    //Toolbar title
    public void setTitleBar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edite Profile");
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

    }



    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
