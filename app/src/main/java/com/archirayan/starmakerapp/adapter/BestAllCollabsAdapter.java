package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 27/2/18.
 */

public class BestAllCollabsAdapter extends RecyclerView.Adapter<BestAllCollabsAdapter.MyViewHolder>{

    private Context context;

    public BestAllCollabsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BestAllCollabsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bestcollabs_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(BestAllCollabsAdapter.MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
