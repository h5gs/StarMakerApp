package com.archirayan.starmakerapp.fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.CollabsongsListAdapter;
import com.archirayan.starmakerapp.adapter.SongAdapter;
import com.archirayan.starmakerapp.model.CollabsSongList;
import com.archirayan.starmakerapp.model.CollabsSongListResponse;
import com.archirayan.starmakerapp.model.SongListResponse;
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
public class CollabListRecorderFragment extends Fragment {

    private static final String TAG = "CollabListRecorderFragment";
    private RecyclerView collabrecoder_list;
    private CollabsongsListAdapter collabsongsListAdapter;
    private ArrayList<CollabsSongList> collabsSongLists;
    private ProgressDialog pd;

    public CollabListRecorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collab_list_recorder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        collabrecoder_list = view.findViewById(R.id.collabrecoder_list);
        collabrecoder_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        getCollabs();
    }

    @SuppressLint("LongLogTag")
    private void getCollabs() {
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(true);
        pd.show();
        collabsSongLists = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));

        Log.e(TAG, "URL:" + Constant.URL + "user_collab_post_list.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "user_collab_post_list.php?", params, new JsonHttpResponseHandler() {
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
                CollabsSongListResponse model = new Gson().fromJson(new String(String.valueOf(response)), CollabsSongListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true")){
                    collabsSongLists = model.getData();
                    collabsongsListAdapter = new CollabsongsListAdapter(getActivity(), collabsSongLists);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    collabrecoder_list.setLayoutManager(mLayoutManager);
                    collabrecoder_list.setItemAnimator(new DefaultItemAnimator());
                    collabrecoder_list.setAdapter(collabsongsListAdapter);
                    collabsongsListAdapter.notifyDataSetChanged();
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
