package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 16/3/18.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> {
    Context context;

    public FollowersAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FollowersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_followers_list, parent, false);

        return new FollowersAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(FollowersAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View view) {
            super(view);

        }
    }
}