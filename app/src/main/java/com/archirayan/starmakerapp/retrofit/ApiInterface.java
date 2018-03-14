package com.archirayan.starmakerapp.retrofit;



import java.io.File;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
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


    @FormUrlEncoded
    @POST("/sms.php")
    void MobileSmsOtp(
            @Field("phone_number") String phone_number,
            Callback<Response> user);


    @FormUrlEncoded
    @POST("/otp_match.php   ")
    void OtpMatch(
            @Field("otp") String otp,
            Callback<Response> user);


    @FormUrlEncoded
    @POST("/set_username.php")
    void setUserProfile(
            @Field("user_id") String user_id,
            @Field("username") String username,
            Callback<Response> user);
}

