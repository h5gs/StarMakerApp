package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.MyPostsList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by archirayan on 10/3/18.
 */

public class SharesListAdapter extends RecyclerView.Adapter<SharesListAdapter.MyViewHolder> {
    private ArrayList<MyPostsList> favorites_arraylist;
    private Context context;

    public SharesListAdapter(Context context, ArrayList<MyPostsList> favorites_arraylists) {
        this.context = context;
        this.favorites_arraylist = favorites_arraylists;
    }

    @Override
    public SharesListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plays_list, parent, false);

        return new SharesListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SharesListAdapter.MyViewHolder holder, int position) {
        holder.txt_favorites_name.setText(favorites_arraylist.get(position).getCaption());
        holder.txt_favorites_total_plays.setText(favorites_arraylist.get(position).getPlays());
        if (favorites_arraylist.get(position).getImgae().isEmpty()) {
            Picasso.with(context).load(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(favorites_arraylist.get(position).getImgae()).placeholder(R.drawable.ic_placeholder).into(holder.img_posts);
        }
    }

    @Override
    public int getItemCount() {
        return favorites_arraylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_posts;
        public TextView txt_favorites_name;
        public TextView txt_favorites_total_plays;

        MyViewHolder(View view) {
            super(view);
            img_posts = view.findViewById(R.id.img_posts);
            txt_favorites_name = view.findViewById(R.id.txt_favorites_name);
            txt_favorites_total_plays = view.findViewById(R.id.txt_favorites_total_plays);
        }
    }
}