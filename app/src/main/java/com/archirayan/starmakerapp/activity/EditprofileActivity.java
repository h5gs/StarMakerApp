package com.archirayan.starmakerapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.EditUserProfileResponse;
import com.archirayan.starmakerapp.model.SuggestedName;
import com.archirayan.starmakerapp.model.SuggestedResponse;
import com.archirayan.starmakerapp.retrofit.RestClient;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.ImageFilePath;
import com.archirayan.starmakerapp.utils.Util;
import com.archirayan.starmakerapp.utils.Utility;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EditprofileActivity extends AppCompatActivity {

    private static final String TAG = "EditprofileActivity";
    RecyclerView recycler_suggestion;
    SuggestedStageNameAdapter followingAdapter;
    Button btn_profile_save;
    private EditText editTextEmail;
    private String edit_vlue;
    private ArrayList<SuggestedName> suggestedNames;
    private RelativeLayout rl_profilepic;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private CircleImageView iv_uplodepic;
    private String imagePath;
    private Toolbar toolbar;
    private ProgressDialog pd;
    private String user_id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        rl_profilepic = findViewById(R.id.rl_profilepic);

        rl_profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });

        iv_uplodepic = findViewById(R.id.iv_uplodepic);
        btn_profile_save = findViewById(R.id.btn_profile_save);
        btn_profile_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(EditprofileActivity.this, FindContactsActivity.class));
//                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
//                finish();


                if (editTextEmail.getText().toString().isEmpty()) {
                    editTextEmail.setError("Please enter phone no.");
                } else {
                    Utils.hideSoftKeyboard(EditprofileActivity.this);
                    setUserProfile(user_id,editTextEmail.getText().toString());
                }
            }
        });
        recycler_suggestion = findViewById(R.id.recycler_suggestion);
        recycler_suggestion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        editTextEmail = findViewById(R.id.editTextEmail);
        edit_vlue = editTextEmail.getText().toString();
        // editTextEmail.setText(Utils.ReadSharePrefrence(EditprofileActivity.this,Constant.SUGGESTEDNAME));

        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                getSuggetion();

            }
        });
    }

    private void setUserProfile(String user_id, String username) {
        if (Utils.isConnectingToInternet(EditprofileActivity.this))
        {
            set_Profile(user_id,username);
        }
        else
        {
            Toast.makeText(EditprofileActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void set_Profile(String user_id, String username)
    {
        pd = new ProgressDialog(EditprofileActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        RestClient.getStarCreator().setUserProfile(user_id, username, new Callback<Response>()
        {
            @Override
            public void success(Response response, Response response2)
            {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(Util.getString(response.getBody().in()));
                    Log.v("", "===== Json =====: " + jsonObject.toString());
                    if (jsonObject.getString("status").toString().equals("true"))
                    {
                        Toast.makeText(EditprofileActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        startActivity(new Intent(EditprofileActivity.this, VerifyingOTPActivity.class));
                        finish();
                    }
                    else
                    {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(EditprofileActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    pd.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                pd.dismiss();
                Toast.makeText(EditprofileActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getSuggetion() {
        suggestedNames = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("text", editTextEmail.getText().toString());

        Log.e(TAG, "URL:" + Constant.URL + "suggestion_text.php?" + params);
        Log.e(TAG, params.toString());
        client.post(this, Constant.URL + "suggestion_text.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "RESPONSE-" + response);
                SuggestedResponse model = new Gson().fromJson(new String(String.valueOf(response)), SuggestedResponse.class);
                if (model.getStatus().equals("true")) {
                    suggestedNames = model.getData();
                    followingAdapter = new SuggestedStageNameAdapter(EditprofileActivity.this, suggestedNames);
                    //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(EditprofileActivity.this);
                    //recycler_suggestion.setLayoutManager(mLayoutManager);
                    // recycler_suggestion.setItemAnimator(new DefaultItemAnimator());
                    recycler_suggestion.setAdapter(followingAdapter);
                    followingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditprofileActivity.this, SignUpActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Gallary"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallary"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditprofileActivity.this);
        builder.setTitle("Choose Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(EditprofileActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Gallary")) {
                    userChoosenTask = "Choose from Gallary";
                    if (result)
                        galleryIntent();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
                Uri imageUri = data.getData();
                imagePath = ImageFilePath.getPath(EditprofileActivity.this, data.getData());
                getDriverProfilePic();
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
              /*  Uri uri = data.getData();
                imagePath = ImageFilePath.getPath(EditprofileActivity.this,data.getData());
                getDriverProfilePic();*/
            }
        }
    }

    private void getDriverProfilePic() {
        AsyncHttpClient client = new AsyncHttpClient();
        File file = new File(imagePath);
        RequestParams params = new RequestParams();
        params.put("user_id", "1");
        try {
            params.put("file", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "PicURL:" + Constant.URL + "change_profile_picture.php?" + params);
        Log.e(TAG, params.toString());
        client.post(this, Constant.URL + "change_profile_picture.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {

                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "Pic~" + response);
                EditUserProfileResponse dmodel = new Gson().fromJson(new String(String.valueOf(response)), EditUserProfileResponse.class);
                if (dmodel.getStatus().equalsIgnoreCase("true")) {
                    if (dmodel.getDate().getImage().isEmpty()) {
                        Picasso.with(EditprofileActivity.this).load(R.mipmap.ic_launch_starmaker);
                    } else {
                        Picasso.with(EditprofileActivity.this).load(dmodel.getDate().getImage()).placeholder(R.mipmap.ic_launch_starmaker).into(iv_uplodepic);

                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        iv_uplodepic.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        iv_uplodepic.setImageBitmap(bm);
    }

    public class SuggestedStageNameAdapter extends RecyclerView.Adapter<SuggestedStageNameAdapter.MyViewHolder> {

        Context context;
        private ArrayList<SuggestedName> suggestedNames;

        public SuggestedStageNameAdapter(Context context, ArrayList<SuggestedName> suggestedNames) {
            this.context = context;
            this.suggestedNames = suggestedNames;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_suggested_stagename, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SuggestedStageNameAdapter.MyViewHolder holder, final int position) {
            holder.txt_suggeted_name.setText(suggestedNames.get(position).getTest());

            holder.txt_suggeted_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editTextEmail.setText(suggestedNames.get(position).getTest());
                }
            });
        }

        @Override
        public int getItemCount() {
            return suggestedNames.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView txt_suggeted_name;

            MyViewHolder(View view) {
                super(view);
                txt_suggeted_name = itemView.findViewById(R.id.txt_suggeted_name);

            }
        }
    }
}