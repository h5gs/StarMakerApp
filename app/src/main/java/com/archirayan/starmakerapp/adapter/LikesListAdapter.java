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
 * Created by archirayan on 9/3/18.
 */

public class LikesListAdapter extends RecyclerView.Adapter<LikesListAdapter.MyViewHolder> {
    private ArrayList<MyPostsList> likes_arraylist;
    private Context context;

    public LikesListAdapter(Context context, ArrayList<MyPostsList> likes_arraylist) {
        this.context = context;
        this.likes_arraylist = likes_arraylist;
    }

    @Override
    public LikesListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_likes_list, parent, false);

        return new LikesListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(LikesListAdapter.MyViewHolder holder, int position) {
        holder.txt_favorites_name.setText(likes_arraylist.get(position).getCaption());
        holder.txt_favorites_total_plays.setText(likes_arraylist.get(position).getPlays());
        if (likes_arraylist.get(position).getImgae().isEmpty()) {
            Picasso.with(context).load(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(likes_arraylist.get(position).getImgae()).placeholder(R.drawable.ic_placeholder).into(holder.img_posts);
        }
    }

    @Override
    public int getItemCount() {
        return likes_arraylist.size();
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