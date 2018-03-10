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
import com.archirayan.starmakerapp.adapter.FavoriteListAdapter;
import com.archirayan.starmakerapp.model.FavoritesListResponse;
import com.archirayan.starmakerapp.model.MyFavoritesList;
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
public class FavoritesListFragment extends Fragment {
    private static final String TAG = "FavoritesListFragment";

    public RecyclerView favorites_recycler;
    FavoriteListAdapter favoriteListAdapter;
    ProgressDialog pd;
    ArrayList<MyFavoritesList> favorites_arraylists;

    public FavoritesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favorites_recycler = view.findViewById(R.id.favorites_recycler);
        getFavoritesLists();
    }

    private void getFavoritesLists() {
        favorites_arraylists = new ArrayList<>();
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(true);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(getActivity(), Constant.USERID));
        params.put("flag", "favorites");
        Log.e(TAG, "URL:" + Constant.URL + "demo1.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getActivity(), Constant.URL + "demo1.php?", params, new JsonHttpResponseHandler()
        {
            @Override
            public void onStart() {
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
                FavoritesListResponse model = new Gson().fromJson(new String(String.valueOf(response)), FavoritesListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true")) {
                    favorites_arraylists = model.getData();
                    // Partiton The Recycler view By Columne ..
                    Log.d("Arraylist_Size", "======= Size() ======= " + favorites_arraylists.size());
                    favoriteListAdapter = new FavoriteListAdapter(getActivity(), favorites_arraylists);
                    favorites_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    favorites_recycler.setAdapter(favoriteListAdapter);
                    favoriteListAdapter.notifyDataSetChanged();
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
