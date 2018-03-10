package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.MyFavoritesList;
import com.archirayan.starmakerapp.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by archirayan on 9/3/18.
 */

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder> {
    private ArrayList<MyFavoritesList> favorites_arraylist;
    private Context context;

    public FavoriteListAdapter(Context context, ArrayList<MyFavoritesList> favorites_arraylists) {
        this.context = context;
        this.favorites_arraylist = favorites_arraylists;
    }

    @Override
    public FavoriteListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorites_list, parent, false);

        return new FavoriteListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(FavoriteListAdapter.MyViewHolder holder, int position) {
        holder.txt_favorites_name.setText(favorites_arraylist.get(position).getCaption());
        holder.txt_favorites_total_plays.setText(favorites_arraylist.get(position).getPlays());
        holder.txt_favorites_date.setText(Utils.parseDateToddMMyyyy(favorites_arraylist.get(position).getDate()));
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
        public TextView txt_favorites_date;
        public TextView txt_favorites_singername;

        MyViewHolder(View view) {
            super(view);
            img_posts = view.findViewById(R.id.img_posts);
            txt_favorites_name = view.findViewById(R.id.txt_favorites_name);
            txt_favorites_total_plays = view.findViewById(R.id.txt_favorites_total_plays);
            txt_favorites_date = view.findViewById(R.id.txt_favorites_date);
            txt_favorites_singername = view.findViewById(R.id.txt_favorites_singername);
        }
    }
}