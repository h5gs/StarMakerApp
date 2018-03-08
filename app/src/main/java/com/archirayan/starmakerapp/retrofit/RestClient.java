package com.archirayan.starmakerapp.retrofit;

import android.app.Activity;

import com.archirayan.starmakerapp.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


/**
 * Created by dgohil on 6/17/15.
 */
public class RestClient extends Activity
{
    private static ApiInterface REST_CLIENT_STARCREATOR;

    static {
        setupRestClient();
    }

    private RestClient() {
    }

    private static void setupRestClient()
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory()) // This is the important line ;)
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        REST_CLIENT_STARCREATOR = buildAdapter(Constant.URL, gson).create(ApiInterface.class);
    }

    private static RestAdapter buildAdapter(String endPoint, Gson gson)
    {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(endPoint)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    public static ApiInterface getStarCreator() {
        return REST_CLIENT_STARCREATOR;
    }


}
