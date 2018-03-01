package com.archirayan.starmakerapp.retrofit;



import java.util.ArrayList;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by dgohil on 6/17/15.
 */
public interface ApiInterface
{
    @FormUrlEncoded
    @POST("/login.php")
    void login(
            @Field("email_address") String email_address,
            @Field("password") String password,
            Callback<Response> user);

    @Multipart
    @POST("/send_mail_for_registration.php")
    void SignUp(
            @Part("email_address") String email_address,
            Callback<Response> user);


    @FormUrlEncoded
    @POST("/verified.php")
    void VerifiedEmail(
            @Field("email_address") String email_address,
            Callback<Response> user);


}

