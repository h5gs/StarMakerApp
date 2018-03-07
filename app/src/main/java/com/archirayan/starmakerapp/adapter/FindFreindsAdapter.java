package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 24/2/18.
 */

public class FindFreindsAdapter  extends RecyclerView.Adapter<FindFreindsAdapter.MyViewHolder> {

    Context context;

    public FindFreindsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_findfreind_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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