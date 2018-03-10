package com.archirayan.starmakerapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.PlaysListAdapter;
import com.archirayan.starmakerapp.adapter.SharesListAdapter;
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
public class SharesListsFragment extends Fragment {

    private static final String TAG = "SharesListsFragment";
    public RecyclerView shares_recycler;
    SharesListAdapter sharesListAdapter;
    ProgressDialog pd;
    ArrayList<MyPostsList> shares_arraylists;
    public SharesListsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shares, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shares_recycler = view.findViewById(R.id.shares_recycler);
        getSharesLists();

    }

    private void getSharesLists() {

        shares_arraylists = new ArrayList<>();
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(true);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id",Utils.ReadSharePrefrence(getActivity(), Constant.USERID));
        params.put("flag", "shares");
        Log.e(TAG, "URL:" + Constant.URL + "demo1.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "demo1.php?", params, new JsonHttpResponseHandler() {
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
                PostListResponse model = new Gson().fromJson(new String(String.valueOf(response)), PostListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true")
                        ) {
                    shares_arraylists = model.getData();
                    // Partiton The Recycler view By Columne ..
                    Log.d("Arraylist_Size", "======= Size() ======= " + shares_arraylists.size());
                    sharesListAdapter = new SharesListAdapter(getActivity(), shares_arraylists);
                    shares_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    shares_recycler.setAdapter(sharesListAdapter);
                    sharesListAdapter.notifyDataSetChanged();
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
