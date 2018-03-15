package com.archirayan.starmakerapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.PostListAdapter;
import com.archirayan.starmakerapp.adapter.RecentListAdapter;
import com.archirayan.starmakerapp.model.MyPostsList;
import com.archirayan.starmakerapp.model.PostListResponse;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostRecentFragment extends Fragment {


    private static final String TAG = "PostListFragment";

    public RecyclerView recent_recycler;
    RecentListAdapter postListAdapter;
    ProgressDialog pd;
    ArrayList<MyPostsList> posts_arraylists;
    public MostRecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_recent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recent_recycler = view.findViewById(R.id.recent_recycler);
        getPostLists();
    }

    private void getPostLists()
    {
        posts_arraylists = new ArrayList<>();
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(true);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));
        params.put("flag", "posts");
        Log.e(TAG, "URL:" + Constant.URL + "demo1.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "demo1.php?", params, new JsonHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "RESPONSE-" + response);
                PostListResponse model =new Gson().fromJson(new String(String.valueOf(response)), PostListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true"))
                {
                    posts_arraylists=model.getData();
                    // Partiton The Recycler view By Columne ..
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                    recent_recycler.setLayoutManager(gridLayoutManager);
                    postListAdapter = new RecentListAdapter(getActivity(),posts_arraylists);
                    recent_recycler.setAdapter(postListAdapter);
                    postListAdapter.notifyDataSetChanged();
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