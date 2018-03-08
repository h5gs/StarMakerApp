package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 8/3/18.
 */

public class PostListAdapter  extends  RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    Context context;
    String value;

    public PostListAdapter(Context context, String value) {
        this.context = context;
        this.value = value;
    }

    public PostListAdapter(FragmentActivity activity)
    {
    }

    @Override

    public PostListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        MyViewHolder(View view)
        {
            super(view);
        }
    }
}