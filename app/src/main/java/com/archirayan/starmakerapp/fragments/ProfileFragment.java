package com.archirayan.starmakerapp.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.DailyCheckInActivity;
import com.archirayan.starmakerapp.activity.FindFriendActivity;
import com.archirayan.starmakerapp.activity.ProfileSettingsActivity;
import com.archirayan.starmakerapp.adapter.ProfilePagerAdapter;
import com.archirayan.starmakerapp.adapter.SlideImageAdapter;
import com.archirayan.starmakerapp.model.EditUserProfileResponse;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.ImageFilePath;
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
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.
    private static final String TAG = "ProfileFragment";
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    //  Image Slider
    private static final Integer[] XMEN = {R.mipmap.ic_starcreator, R.drawable.images, R.drawable.images_one, R.drawable.images_two, R.drawable.img_splashtxt};
    private static ViewPager mPager;
    private static int currentPage = 0;
    public CircleImageView iv_profile_pic;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView img_setting, img_findfreinds, img_daily_checkin;
    ViewPager Pager;
    TextView txt_followers, txt_following, txt_post, txt_username, txt_usertitle;
    private boolean mIsAvatarShown = true;
    private ImageView mProfileImage;
    private int mMaxScrollSize;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private File outPutFile_pp = null;
    private int PICK_IMAGE_GALLERY_REQUEST = 1;
    private int PICK_IMAGE_CAMERA_REQUEST = 2;
    private ProgressDialog pd;
    private String userChoosenTask;
    private String imagePath;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        AppBarLayout appbarLayout = (AppBarLayout) view.findViewById(R.id.materialup_appbar);
        mProfileImage = (ImageView) view.findViewById(R.id.materialup_profile_image);
        img_setting = view.findViewById(R.id.img_setting);
        img_findfreinds = view.findViewById(R.id.img_findfreinds);
        img_daily_checkin = view.findViewById(R.id.img_daily_checkin);
        //iv_profile_pic = view.findViewById(R.id.iv_profile_pic);
        txt_followers = view.findViewById(R.id.txt_followers);
        txt_following = view.findViewById(R.id.txt_following);
        txt_post = view.findViewById(R.id.txt_post);
        txt_username = view.findViewById(R.id.txt_username);
        txt_usertitle = view.findViewById(R.id.txt_usertitle);


        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        Pager = (ViewPager) view.findViewById(R.id.pager);

        ProfilePagerAdapter viewPagerAdapter = new ProfilePagerAdapter(getChildFragmentManager(), "song");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_layout);
        tabLayout.setupWithViewPager(viewPager);

        if (Utils.isConnectingToInternet(getActivity())) {
            GetUserProfile();
        } else {
            Toast.makeText(getActivity(), R.string.no_internet_fount, Toast.LENGTH_SHORT).show();
        }
        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileSettingsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });


        img_findfreinds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FindFriendActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        img_daily_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DailyCheckInActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();


        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing);


        // TODO: 8/3/18  Upload Image (Camera & Gallerr)...

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption();
            }
        });


        // // TODO: 8/3/18   Image Slide Using View Pager ..
        Collections.addAll(XMENArray, XMEN);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SlideImageAdapter(getActivity(), XMENArray));
        // Auto start of Viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);


        return view;
    }

    private void selectOption() {
        final CharSequence[] items = {/*"Take Photo", */"Choose from Gallary"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());
//
//                if (items[item].equals("Take Photo"))
//                {
//                    userChoosenTask = "Take Photo";
//                    if (result)
//                        cameraIntent();
//
//                } else if (items[item].equals("Choose from Gallary")) {
                userChoosenTask = "Choose from Gallary";
                if (result)
                    galleryIntent();
            }
        });
        builder.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (userChoosenTask.equals("Take Photo"))
//                        cameraIntent();
//                    else
                    if (userChoosenTask.equals("Choose from Gallary"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
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
                imagePath = ImageFilePath.getPath(getActivity(), data.getData());
                UploadImage();
                Log.d("ImagePath", "Result" + imagePath);
            } else if (requestCode == REQUEST_CAMERA)
            {
                onCaptureImageResult(data);
              /*  Uri uri = data.getData();
                imagePath = ImageFilePath.getPath(EditprofileActivity.this,data.getData());
                getDriverProfilePic();*/
            }
        }
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
        mProfileImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mProfileImage.setImageBitmap(bm);
    }


    private void GetUserProfile() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));
        client.post(getActivity(), Constant.URL + "user_profile.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Profile()", "RESPONSE-" + response);
                //LoginResponse model = new Gson().fromJson(new String(String.valueOf(response)),LoginResponse.class);
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        txt_username.setText(object.getString("username"));
                        txt_followers.setText(object.getString("followers"));
                        txt_following.setText(object.getString("followings"));
                        txt_post.setText(object.getString("Post"));
                        txt_usertitle.setText(object.getString("username"));
                        if (object.getString("image").isEmpty()) {
                            Picasso.with(getActivity()).load(R.drawable.starmakermusic);
                        } else {
                            Picasso.with(getActivity()).load(object.getString("image")).placeholder(R.drawable.starmakermusic).into(mProfileImage);
                        }
                    } else {
                        Toast.makeText(getActivity(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("error", throwable.getMessage());
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 50 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }


    // TODO: 8/3/18  Upload Image ...
    private void selectImageOption() {
        final CharSequence[] items = {/*"Take a Photo", */"Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Change Photos");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {

//                if (items[item].equals("Take a Photo"))
//                {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    Uri outputFileUri = Uri.fromFile(new File(getActivity().getExternalCacheDir().getPath(), "profilePic.jpeg"));
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//                    UploadImage();
//                } else
                if (items[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_GALLERY_REQUEST);
                    UploadImage();
                } else if (items[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void UploadImage() {
        AsyncHttpClient client = new AsyncHttpClient();
        File file = new File(imagePath);
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));
        try {
            params.put("file", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "PicURL:" + Constant.URL + "change_profile_picture.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "change_profile_picture.php?", params, new JsonHttpResponseHandler() {
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
                Log.e(TAG, "Pic~Upload" + response);
                EditUserProfileResponse dmodel = new Gson().fromJson(new String(String.valueOf(response)), EditUserProfileResponse.class);
                if (dmodel.getStatus().equalsIgnoreCase("true")) {
                    if (dmodel.getDate().getImage().isEmpty()) {
                        Picasso.with(getActivity()).load(R.mipmap.ic_launch_starmaker);
                    } else {
                        Picasso.with(getActivity()).load(dmodel.getDate().getImage()).placeholder(R.mipmap.ic_launch_starmaker).into(mProfileImage);
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

}
