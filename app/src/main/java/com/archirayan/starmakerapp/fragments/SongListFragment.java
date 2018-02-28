package com.archirayan.starmakerapp.fragments;

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
import com.archirayan.starmakerapp.activity.EditprofileActivity;
import com.archirayan.starmakerapp.adapter.SongAdapter;
import com.archirayan.starmakerapp.model.GetSongList;
import com.archirayan.starmakerapp.model.SongListResponse;
import com.archirayan.starmakerapp.model.SuggestedResponse;
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
public class SongListFragment extends Fragment
{

    private static final String TAG = "SongListFragment";
    RecyclerView rvList;
    SongAdapter songAdapter;
    private ArrayList<GetSongList> getSongList;
    private ProgressDialog pd;

    public SongListFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean getUserVisibleHint() {

        if (isVisible()) {
            songAdapter = new SongAdapter(getActivity(),getSongList);
            rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            rvList.setAdapter(songAdapter);
        }
        return isVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList = view.findViewById(R.id.item_list);
        getSongList();
        //songAdapter = new SongAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //rvList.setAdapter(songAdapter);
    }

    private void getSongList() {
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(true);
        pd.show();
        getSongList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id", "1");

        Log.e(TAG, "URL:" + Constant.URL + "get_user_profile.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "get_user_profile.php?", params, new JsonHttpResponseHandler() {
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
                SongListResponse model = new Gson().fromJson(new String(String.valueOf(response)), SongListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true")){
                    getSongList = model.getData();
                    songAdapter = new SongAdapter(getActivity(), getSongList);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    rvList.setLayoutManager(mLayoutManager);
                    rvList.setItemAnimator(new DefaultItemAnimator());
                    rvList.setAdapter(songAdapter);
                    songAdapter.notifyDataSetChanged();
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
