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
import com.archirayan.starmakerapp.adapter.HotSongsListAdapter;
import com.archirayan.starmakerapp.adapter.MainAdapter;
import com.archirayan.starmakerapp.model.HotSongListResponse;
import com.archirayan.starmakerapp.model.NewSongList;
import com.archirayan.starmakerapp.model.NewSongListAdaper;
import com.archirayan.starmakerapp.model.NewSongListRespons;
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
public class newListRecorderFragment extends Fragment {

    private static final String TAG = "newListRecorderFragment";
    private RecyclerView newrecoder_list;
    private NewSongListAdaper newSongListAdaper;
    private ArrayList<NewSongList> newSongLists;
    private ProgressDialog pd;


    public newListRecorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list_recorder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newrecoder_list = view.findViewById(R.id.newrecoder_list);
        newrecoder_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        getNewSongs();



    }

    private void getNewSongs() {
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(true);
        pd.show();
        newSongLists = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));

        Log.e(TAG, "URL:" + Constant.URL + "user_new_songs_list.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "user_new_songs_list.php?", params, new JsonHttpResponseHandler() {
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
                NewSongListRespons model = new Gson().fromJson(new String(String.valueOf(response)), NewSongListRespons.class);
                pd.dismiss();
                if (model.getStatus().equals("true")){
                    newSongLists = model.getData();
                    newSongListAdaper = new NewSongListAdaper(getActivity(), newSongLists);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    newrecoder_list.setLayoutManager(mLayoutManager);
                    newrecoder_list.setItemAnimator(new DefaultItemAnimator());
                    newrecoder_list.setAdapter(newSongListAdaper);
                    newSongListAdaper.notifyDataSetChanged();
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
