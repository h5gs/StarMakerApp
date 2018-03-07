package com.archirayan.starmakerapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.Grantpermissons;
import com.archirayan.starmakerapp.activity.RecordingSongs;
import com.archirayan.starmakerapp.model.CollabsSongList;
import com.archirayan.starmakerapp.utils.Utility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by archirayan on 21/2/18.
 */

public class CollabsongsListAdapter extends RecyclerView.Adapter<CollabsongsListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CollabsSongList> collabsSongLists;

    public CollabsongsListAdapter(Context context, ArrayList<CollabsSongList> collabsSongLists) {
        this.context = context;
        this.collabsSongLists = collabsSongLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.btn_sing.setVisibility(View.GONE);
        holder.btn_join.setVisibility(View.VISIBLE);
        if (collabsSongLists.get(position).getImgae().isEmpty()){
            Picasso.with(context).load(R.drawable.starmakermusic);
        }else {
            Picasso.with(context).load(collabsSongLists.get(position).getImgae()).placeholder(R.drawable.starmakermusic).into(holder.iv_songicon);

        }
        holder.txt_songtital.setText(collabsSongLists.get(position).getTitle());
        holder.txt_caption.setText(collabsSongLists.get(position).getCaption());
        holder.txt_joined.setText(collabsSongLists.get(position).getJoined()+" "+"joined");
        holder.btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.sing_dialod);
                TextView txt_solo = dialog.findViewById(R.id.txt_solo);
                txt_solo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean result = Utility.checkPermission(context);
                        if (result){
                            context.startActivity(new Intent(getApplicationContext(),RecordingSongs.class));
                            dialog.dismiss();
                        }else {
                            context.startActivity(new Intent(getApplicationContext(), Grantpermissons.class));
                            dialog.dismiss();
                        }
                    }
                });
                TextView txt_startcollab = dialog.findViewById(R.id.txt_startcollab);
                txt_startcollab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean result = Utility.checkPermission(context);
                        if (result){
                            context.startActivity(new Intent(getApplicationContext(),RecordingSongs.class));
                            dialog.dismiss();
                        }else {
                            context.startActivity(new Intent(getApplicationContext(), Grantpermissons.class));
                            dialog.dismiss();
                        }
                    }
                });
                //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return collabsSongLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_songtital,txt_caption,txt_joined,btn_sing,btn_join;
        private ImageView iv_songicon;

        MyViewHolder(View view) {
            super(view);

            iv_songicon = itemView.findViewById(R.id.iv_songicon);
            txt_songtital = itemView.findViewById(R.id.txt_songtital);
            txt_caption = itemView.findViewById(R.id.txt_caption);
            txt_joined = itemView.findViewById(R.id.txt_joined);
            btn_sing = itemView.findViewById(R.id.btn_sing);
            btn_join = itemView.findViewById(R.id.btn_join);

        }
    }
}