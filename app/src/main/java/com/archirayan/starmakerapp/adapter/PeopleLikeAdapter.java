package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 26/2/18.
 */

public class PeopleLikeAdapter extends RecyclerView.Adapter<PeopleLikeAdapter.MyViewHolder> {

    Context context;

    public PeopleLikeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PeopleLikeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_people_like, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(PeopleLikeAdapter.MyViewHolder holder, int position) {

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