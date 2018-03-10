package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.PlaysongfullActivity;
import com.archirayan.starmakerapp.model.GetSongList;
import com.github.rtoshiro.view.video.FullscreenVideoView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archirayan on 24/2/18.
 */

public class SongAdapter  extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    Context context;
    private ArrayList<GetSongList> getSongLists;

    public SongAdapter(Context context, ArrayList<GetSongList> getSongLists) {
        this.context = context;
        this.getSongLists = getSongLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        holder.profile_name.setText(getSongLists.get(position).getUsername());
        if (getSongLists.get(position).getProfile_picture().isEmpty())
        {
            Picasso.with(context).load(R.drawable.men);
        }
        else
        {
            Picasso.with(context).load(getSongLists.get(position).getProfile_picture()).placeholder(R.drawable.ic_placeholder).into(holder.profile_pic);
        }
        holder.txt_levels.setText(getSongLists.get(position).getCount());
        if (getSongLists.get(position).getHashtag().isEmpty()){
            holder.txt_tags.setVisibility(View.VISIBLE);
            holder.txt_tags.setText(getSongLists.get(position).getHashtag());
        }else {
            holder.txt_tags.setVisibility(View.GONE);
        }
        holder.txt_likenum.setText(getSongLists.get(position).getLikes());
        holder.txt_starnum.setText(getSongLists.get(position).getStars());
        String path = getSongLists.get(position).getVideo();
        holder.iv_video.setVideoURI(Uri.parse(path));

        holder.iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.iv_video.start();
                holder.iv_start.setVisibility(View.GONE);
                holder.iv_stop.setVisibility(View.VISIBLE);
            }
        });

        holder.iv_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.iv_video.resume();
                holder.iv_start.setVisibility(View.VISIBLE);
                holder.iv_stop.setVisibility(View.GONE);
            }
        });

       Picasso.with(context).load(getSongLists.get(position).getProfile_picture()).placeholder(R.drawable.men).into(holder.gifters);

        holder.fram_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), PlaysongfullActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return getSongLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profile_pic;
        private TextView profile_name;
        private TextView txt_levels;
        private TextView txt_tags;
        private VideoView iv_video;
        private TextView txt_likenum;
        private TextView txt_starnum;
        private CircleImageView gifters;
        private ImageView iv_start,iv_stop;
        private FrameLayout fram_video;

        public MyViewHolder(View itemView) {
            super(itemView);

            profile_pic = itemView.findViewById(R.id.profile_pic);
            profile_name = itemView.findViewById(R.id.profile_name);
            txt_levels = itemView.findViewById(R.id.txt_levels);
            txt_tags = itemView.findViewById(R.id.txt_tags);
            iv_video = itemView.findViewById(R.id.iv_video);
            txt_likenum = itemView.findViewById(R.id.txt_likenum);
            txt_starnum = itemView.findViewById(R.id.txt_starnum);
            gifters = itemView.findViewById(R.id.gifters);
            iv_start = itemView.findViewById(R.id.iv_start);
            iv_stop = itemView.findViewById(R.id.iv_stop);
            fram_video = itemView.findViewById(R.id.fram_video);

        }
    }
}
