package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.Login;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.github.rtoshiro.view.video.FullscreenVideoView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.google.android.gms.common.api.ResultCallback;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 007;
    public LoginButton loginButton;
    FullscreenVideoView videoLayout;
    RelativeLayout linear_fblogin;
    CircleImageView iv_loginwithmobile, iv_loginandsignup, iv_loginwithgoogle, iv_logoutwithgoogle;
    private String TAG = "LoginActivity";
    private ProgressDialog progress;
    private CallbackManager callbackManager;
    private String fbid;
    private String fbfirst_name;
    private String fblast_name;
    private String fbemail;
    private GoogleApiClient mGoogleApiClient;
    private String googleid, googlefirst_name, googlelast_name, googleemail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_welcome);
        FindViewById();

        printHashKey(WelcomeActivity.this);
        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);

        linear_fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });


        //// TODO: 26/2/18 Google Integration ...

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();





        //// TODO: 26/2/18 Facebook...
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);

            }

            @Override
            public void onCancel() {
                Toast.makeText(WelcomeActivity.this, "Login Denied", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(WelcomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


        iv_loginwithmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, LoginwithMobileActivity.class));
                finish();
            }
        });

        iv_loginwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
//                SpinKitView progressBar = (SpinKitView) findViewById(R.id.spin_kit);
//                DoubleBounce doubleBounce = new DoubleBounce();
//                progressBar.setIndeterminateDrawable(doubleBounce);
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                        finish();
//                    }
//                }, 2000);
//
//            }
                //});
            }
        });

        iv_loginandsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialoge();
            }
        });

        // // TODO: 22/2/18 Play The Video ...
        String path = "android.resource://" + getPackageName() + "/" + R.raw.splash_video;
        try {
            videoLayout.setVideoURI(Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoLayout.start();
        videoLayout.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoLayout.start();
            }
        });
    }


    //get user detail from FB
    protected void getUserDetails(final LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {

                        Log.e("FACEBOOK RESPONSE==>", response.toString());
                        try {
                            fbid = json_object.getString("id");
                            fbfirst_name = json_object.getString("first_name");
                            fblast_name = json_object.getString("last_name");
                            fbemail = json_object.getString("email");
                            Log.e(TAG, "FACEBOOK==>" + fbid + fbfirst_name + fblast_name + fbemail);
                            // getFacebooklogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Facebooklogin();
                    }

                });
        Bundle permission_param = new Bundle();
        //permission_param.putString("fields", "id,name,email,picture.width(120).height(120),oh");
        //permission_param.putString("fields", "id, name, email, gender, birthday");
        permission_param.putString("fields", "id,first_name,last_name,email,gender");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Call callbackManager.onActivityResult to pass login result to the LoginManager via callbackManager.
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void printHashKey(WelcomeActivity welcomeActivity)
    {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.e(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e)
        {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }


    private void openAlertDialoge() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(WelcomeActivity.this);
        builderSingle.setTitle("Use Email Address");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(WelcomeActivity.this, android.R.layout.simple_list_item_1);
        arrayAdapter.add("Log In");
        arrayAdapter.add("Sign Up");
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();

                } else {
                    startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
                    finish();

                }
            }
        });
        builderSingle.show();
    }

    // todo Define Id for All Controls ...
    private void FindViewById() {
        videoLayout = findViewById(R.id.videoview);
        linear_fblogin = findViewById(R.id.linear_fblogin);
        iv_loginwithmobile = findViewById(R.id.iv_loginwithmobile);
        iv_loginwithgoogle = findViewById(R.id.iv_loginwithgoogle);
        iv_loginandsignup = findViewById(R.id.iv_loginandsignup);

        iv_logoutwithgoogle = findViewById(R.id.iv_logoutwithgoogle);
    }


    //// TODO: 26/2/18 Facebook Login With APi   //API call for FacebookLogin
    private void Facebooklogin() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.put("email_address", fbemail);
        requestParams.put("social_token", fbid);
        Log.e(TAG, "http://web-medico.com/web1/StarCreator/APIs/login.php?" + requestParams);
        client.post("http://web-medico.com/web1/StarCreator/APIs/login.php?", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                ShowProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Hideprogress();

            }

            @Override

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "Login RESPONSE-" + response);

                if (!response.equals(""))
                {
                    Login login = new Gson().fromJson(new String(String.valueOf(response)), Login.class);
                    if (login.status.equals("true")) {
                        Utils.WriteSharePrefrence(WelcomeActivity.this, Constant.USERID, login.data.user_id);
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        intent.putExtra("username", fbfirst_name);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(WelcomeActivity.this, login.msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WelcomeActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
            }
        });

    }


    private void Hideprogress() {
        // TODO Auto-generated method stub
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    private void ShowProgress() {
        // TODO Auto-generated method stub
        progress = new ProgressDialog(WelcomeActivity.this);
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                        signIn();
                    }
                });
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personFirstName = acct.getGivenName();
            String personLastName = acct.getFamilyName();
            String email = acct.getEmail();
            String id = acct.getId();

            Log.e(TAG, "FirstName: " + personFirstName + ", email: " + email
                    + ", LastName: " + personLastName + ", id: " + id);

            googleid = acct.getId();
            googlefirst_name = acct.getGivenName();
            googlelast_name = acct.getFamilyName();
            googleemail = acct.getEmail();

            updateUI(true);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }

    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            iv_loginwithgoogle.setVisibility(View.GONE);
            iv_logoutwithgoogle.setVisibility(View.VISIBLE);
        } else {
            iv_loginwithgoogle.setVisibility(View.VISIBLE);
            iv_logoutwithgoogle.setVisibility(View.GONE);
        }

    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}