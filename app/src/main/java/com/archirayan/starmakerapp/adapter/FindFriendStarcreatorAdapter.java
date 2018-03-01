package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 28/2/18.
 */

public class FindFriendStarcreatorAdapter extends RecyclerView.Adapter<FindFriendStarcreatorAdapter.MyViewHolder> {

    Context context;

    public FindFriendStarcreatorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_findyourinvitefreind_list, parent, false);

        return new FindFriendStarcreatorAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(FindFriendStarcreatorAdapter.MyViewHolder holder, int position) {

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