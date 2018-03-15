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
 * Created by archirayan on 15/3/18.
 */

public class NewPopularListAdapter extends RecyclerView.Adapter<NewPopularListAdapter.MyViewHolder> {
    private ArrayList<MyPostsList> posts_arraylist;
    private Context context;

    public NewPopularListAdapter(Context context, int i) {
        this.context = context;
     //   this.posts_arraylist = posts_arraylist;
    }

    @Override

    public NewPopularListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newpopular_list, parent, false);

        return new NewPopularListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(NewPopularListAdapter.MyViewHolder holder, int position)
    {
//        holder.txt_posts_name.setText(posts_arraylist.get(position).getCaption());
//        holder.txt_total_plays.setText(posts_arraylist.get(position).getPlays());
//        if (posts_arraylist.get(position).getImgae().isEmpty()) {
//            Picasso.with(context).load(R.drawable.ic_placeholder);
//        } else {
//            Picasso.with(context).load(posts_arraylist.get(position).getImgae()).placeholder(R.drawable.ic_placeholder).into(holder.img_posts);
//        }
    }

    @Override
    public int getItemCount()
    {
        return 4;//posts_arraylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView img_posts;
        public TextView txt_posts_name;
        public TextView txt_total_plays;

        MyViewHolder(View view) {
            super(view);
            img_posts = view.findViewById(R.id.img_posts);
            txt_posts_name = view.findViewById(R.id.txt_posts_name);
            txt_total_plays = view.findViewById(R.id.txt_total_plays);
        }
    }
}