package com.archirayan.starmakerapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.Login;
import com.archirayan.starmakerapp.model.LoginResponse;
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
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 007;
    public ProgressDialog pd;
    public LoginButton loginButton;
    Button btn_login;
    ImageView img_back_title_Dsignin;
    // TODO: 13/3/18  Facebook
    EditText edit_email, edit_password;
    RelativeLayout linear_fblogin;
    FullscreenVideoView videoLayout;
    CircleImageView iv_loginwithmobile, iv_loginandsignup, iv_loginwithgoogle, iv_logoutwithgoogle;
    private ProgressDialog progress;
    private CallbackManager callbackManager;
    private String fbid;
    private String fbfirst_name;
    private String fblast_name;
    private String fbemail;
    private GoogleApiClient mGoogleApiClient;
    private String googleid, googlefirst_name, googlelast_name, googleemail;
    //// TODO: 12/3/18   New Designing
    private Button btn_createaccount;
    private TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        init();

        printHashKey(LoginActivity.this);
        callbackManager = CallbackManager.Factory.create();


        //// TODO: 26/2/18 Google Integration ...

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        loginButton = findViewById(R.id.login_button);

        linear_fblogin.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });


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
                Toast.makeText(LoginActivity.this, "Login Denied", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        iv_loginwithmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, LoginwithMobileActivity.class));
                finish();
            }
        });

        iv_loginwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        btn_login = findViewById(R.id.btn_login);
        linear_fblogin = findViewById(R.id.linear_fblogin);
        iv_loginwithmobile = findViewById(R.id.iv_loginwithmobile);

        iv_loginwithgoogle = findViewById(R.id.iv_loginwithgoogle);

        iv_logoutwithgoogle = findViewById(R.id.iv_logoutwithgoogle);

        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_email.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                } else if (edit_password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    login(edit_email.getText().toString(), edit_password.getText().toString());
                }
            }
        });
    }

    private void login(String email, String password) {
        if (Utils.isConnectingToInternet(LoginActivity.this)) {
            loginCallApi(email, password);
        } else {
            Toast.makeText(LoginActivity.this, "Please Check the Internet Connection? Try Again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginCallApi(String email, String password) {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email_address", edit_email.getText().toString());
        params.put("password", edit_password.getText().toString());

        Log.e(TAG, "DriverURL:" + Constant.URL + "login.php?" + params);
        Log.e(TAG, params.toString());
        client.post(this, Constant.URL + "login.php?", params, new JsonHttpResponseHandler() {
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
                Log.e(TAG, "LOGIN DriverRESPONSE-" + response);
                LoginResponse model = new Gson().fromJson(new String(String.valueOf(response)), LoginResponse.class);
                pd.dismiss();
                if (model.getStatus().equalsIgnoreCase("true")) {
                    Utils.WriteSharePrefrence(LoginActivity.this, Constant.USERID, model.getData().getId());
                    String Userid = Utils.ReadSharePrefrence(LoginActivity.this, Constant.USERID);
                    Log.e(TAG, "READ SHARED==>" + Userid);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, model.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
            }
        });

    }


    // TODO: 13/3/18 Facebook Login

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


    private void printHashKey(LoginActivity welcomeActivity) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.e(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
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

                if (!response.equals("")) {
                    Login login = new Gson().fromJson(new String(String.valueOf(response)), Login.class);
                    if (login.status.equals("true")) {
                        Utils.WriteSharePrefrence(LoginActivity.this, Constant.USERID, login.data.user_id);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", fbfirst_name);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, login.msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
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
        progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    // TODO: 14/3/18 Googhle Signing and Signout


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
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

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