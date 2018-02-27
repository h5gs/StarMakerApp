package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 22/2/18.
 */

public class YouAdapter extends RecyclerView.Adapter<YouAdapter.MyViewHolder> {

    Context context;
    String value;

    public YouAdapter(Context context, String value) {
        this.context = context;
        this.value = value;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.you_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (value.equals("invitation")) {

            holder.tvInvite.setVisibility(View.VISIBLE);
            holder.tvFollow.setText("JOIN");

        } else if (value.equals("recommendation")) {

            holder.tvFollow.setText("FOLLOW");
            holder.tvInvite.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {

        int val = 0;

        if (value.equals("invitation")) {
            val = 1;
        } else if (value.equals("recommendation")) {
            val = 6;
        }

        return val;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvInvite, tvFollow;

        MyViewHolder(View view) {
            super(view);

            tvInvite = itemView.findViewById(R.id.you_tvInvitation);
            tvFollow = itemView.findViewById(R.id.you_tvFollow);
        }
    }
}