package com.archirayan.starmakerapp.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.PlaysongfullActivity;
import com.archirayan.starmakerapp.model.GetFollowResponse;
import com.archirayan.starmakerapp.model.GetSongList;
import com.archirayan.starmakerapp.model.SongListResponse;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.archirayan.starmakerapp.adapter.SongAdapter.MyViewHolder.txt_follow;
import static com.archirayan.starmakerapp.adapter.SongAdapter.MyViewHolder.txt_likenum;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by archirayan on 24/2/18.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {
    private static final String TAG = "SongListFragment";
    public static String str_like_Updated;
    Context context;
    private ArrayList<GetSongList> getSongLists;
    private ProgressDialog pd;
    private String str_Postid;
    private String str_Likes;
    private String str_Favorites;
    private String s_favorites;

    public SongAdapter(Context context, ArrayList<GetSongList> getSongLists) {
        this.context = context;
        this.getSongLists = getSongLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_trendinglist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.profile_name.setText(getSongLists.get(position).getTitle());
        holder.txt_caption.setText(getSongLists.get(position).getCaption());
        holder.txt_tags.setText(getSongLists.get(position).getHashTag());

        str_Postid = (getSongLists.get(position).getId());
        str_Likes = getSongLists.get(position).getLikes().toString();
        str_Favorites = getSongLists.get(position).getFavorite();
        // holder.iv_image_profile.setImageResource();
        if (getSongLists.get(position).getImgae().isEmpty()) {

            Picasso.with(context).load(R.drawable.men);
        } else {
            Picasso.with(context).load(getSongLists.get(position).getImgae()).placeholder(R.drawable.ic_placeholder).into(holder.profile_pic);
        }


        if (getSongLists.get(position).getImgae().isEmpty()) {

            Picasso.with(context).load(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(getSongLists.get(position).getImgae()).placeholder(R.drawable.ic_placeholder).into(holder.iv_image_profile);
        }


        if (str_Favorites.equals("0")) {
            s_favorites = "Added to Favorites";
        } else {
            s_favorites = "Removed to Favorites";
        }


        if (getSongLists.get(position).getLikeStatus().equals("1")) {
            holder.chk_like.setBackgroundResource(R.drawable.ic_like);
        } else {
            holder.chk_like.setBackgroundResource(R.drawable.ic_unlike_black);
        }


        if (!getSongLists.get(position).getLikes().toString().equals("")) {
            txt_likenum.setText(getSongLists.get(position).getLikes().toString());
        }

//
//        if (!getSongLists.get(position).getStar().toString().equals("")) {
//            holder.txt_starnum.setText(getSongLists.get(position).getStar());
//        }


//        String path = getSongLists.get(position).getVideo();
//
//        holder.iv_video.setVideoURI(Uri.parse(path));
//
//        holder.iv_start.setOnClickListener(new View.OnClickListener()
// {
//            @Override
//            public void onClick(View v) {
//                holder.iv_video.start();
//                holder.iv_start.setVisibility(View.GONE);
//                holder.iv_stop.setVisibility(View.VISIBLE);
//            }
//        });
//
//        holder.iv_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.iv_video.stopPlayback();
//                holder.iv_start.setVisibility(View.VISIBLE);
//                holder.iv_stop.setVisibility(View.GONE);
//            }
//        });


        for (int i = 0; i < getSongLists.get(position).getTopGifter().size(); i++)
        {
            if (!getSongLists.get(position).getTopGifter().get(i).getImage().equalsIgnoreCase("")) {
                Picasso.with(context).load(getSongLists.get(position).getTopGifter().get(i).getImage()).placeholder(R.drawable.men).into(holder.gifters);
            }

            if (!getSongLists.get(position).getTopGifter().get(i).getImage().equalsIgnoreCase("")) {
                Picasso.with(context).load(getSongLists.get(position).getTopGifter().get(i).getImage()).placeholder(R.drawable.men).into(holder.gifters1);
            }
            if (!getSongLists.get(position).getTopGifter().get(i).getImage().equalsIgnoreCase("")) {
                Picasso.with(context).load(getSongLists.get(position).getTopGifter().get(i).getImage()).placeholder(R.drawable.men).into(holder.gifters2);
            }
        }

        holder.fram_video.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                context.startActivity(new Intent(context.getApplicationContext(), PlaysongfullActivity.class));
            }
        });


        holder.chk_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    holder.chk_like.setBackgroundResource(R.drawable.ic_like);
                    AddRemoveLikesApi();
                } else {
                    holder.chk_like.setBackgroundResource(R.drawable.ic_unlike_black);
                    AddRemoveLikesApi();
                }
            }
        });


//        holder.iv_action_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final CharSequence[] items = {s_favorites, "See fewer posts like this", "Report"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        boolean result = Utility.checkPermission(context);
//
//                        if (items[item].equals("Added to Favorites")) {
//                            AddRemoveFavorites();
//                        } else if (items[item].equals("Removed to Favorites")) {
//                            AddRemoveFavorites();
//                        } else if (items[item].equals("See fewer posts like this")) {
//                        } else if (items[item].equals("Report")) {
//
//                        }
//
//                    }
//                });
//                builder.show();
//            }
//        });

        txt_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowUnfollowedApi();
            }
        });


        holder.gifters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String s_follow = null;
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setItems(s_follow, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item)
//                    {
//                        boolean result = Utility.checkPermission(context);
//
//                if (s_follow.equals("Followed"))
// {
//                    FollowUnfollowedApi();
//                } else if (s_follow.equals("Unfollowed")) {
//                    FollowUnfollowedApi();
//                }
            }
        });
    }


    //// TODO: 10/3/18  Followed Unfollowed ...
    private void FollowUnfollowedApi() {
        pd = new ProgressDialog(context);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(context.getApplicationContext(), Constant.USERID));
        params.put("follow_user_id", str_Postid);

        Log.e(TAG, "DriverURL:" + Constant.URL + "follow.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getApplicationContext(), Constant.URL + "follow.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "LOGIN DriverRESPONSE-" + response);
                GetFollowResponse model = new Gson().fromJson(new String(String.valueOf(response)), GetFollowResponse.class);
                pd.dismiss();
                if (model.getStatus().equalsIgnoreCase("true")) {
                    getSongList();
                    Toast.makeText(context, model.getMsg().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, model.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
            }
        });
    }


    // // TODO: 10/3/18 Add  Favorites & Removed
    private void AddRemoveFavorites() {
        pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(context, Constant.USERID));
        params.put("post_id", str_Postid);
        Log.e(TAG, "URL:" + Constant.URL + "user_favorites.php?" + params);
        Log.e(TAG, params.toString());
        client.post(context, Constant.URL + "user_favorites.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "RESPONSE-" + response);
                SongListResponse model = new Gson().fromJson(new String(String.valueOf(response)), SongListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true")) {
                    Toast.makeText(context, model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
                pd.dismiss();

            }
        });


    }

    //// TODO: 10/3/18   Sanjay .. Add Like Or Unlike
    private void AddRemoveLikesApi() {
        pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(context, Constant.USERID));
        params.put("post_id", str_Postid);

        Log.e(TAG, "URL:" + Constant.URL + "like_unlike.php?" + params);
        Log.e(TAG, params.toString());
        client.post(context, Constant.URL + "like_unlike.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "RESPONSE-" + response);
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    str_like_Updated = jsonObject.getString("like");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SongListResponse model = new Gson().fromJson(new String(String.valueOf(response)), SongListResponse.class);
                pd.dismiss();
                if (model.getStatus().equals("true")) {
                    Toast.makeText(context, model.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());
                pd.dismiss();

            }
        });
    }

    @Override
    public int getItemCount() {
        return getSongLists.size();
    }

    private void getSongList() {
        pd = new ProgressDialog(context);
        pd.setCancelable(true);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id", Utils.ReadSharePrefrence(context, Constant.USERID));
        params.put("flag", "following");

        Log.e(TAG, "URL:" + Constant.URL + "song_list.php?" + params);
        Log.e(TAG, params.toString());
        client.post(context, Constant.URL + "song_list.php?", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "RESPONSE-" + response);
                SongListResponse model = new Gson().fromJson(new String(String.valueOf(response)), SongListResponse.class);
                pd.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, throwable.getMessage());

            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public static TextView txt_likenum, txt_follow;
        private final TextView txt_starnum;
        public CircleImageView profile_pic, gifters, gifters1, gifters2;
        public TextView profile_name, txt_levels, txt_tags, txt_st_arnum, txt_caption;
        public VideoView iv_video;
        public ImageView iv_start, iv_stop, iv_action_menu;
        public FrameLayout fram_video;
        public CheckBox chk_like;

        public ImageView iv_image_profile;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            profile_pic = itemView.findViewById(R.id.profile_pic);
            profile_name = itemView.findViewById(R.id.profile_name);
            txt_levels = itemView.findViewById(R.id.txt_levels);
            txt_tags = itemView.findViewById(R.id.txt_tags);
            iv_video = itemView.findViewById(R.id.iv_video);
            txt_likenum = itemView.findViewById(R.id.txt_likenum);
            txt_starnum = itemView.findViewById(R.id.txt_starnum);
            gifters = itemView.findViewById(R.id.gifters);
            gifters1 = itemView.findViewById(R.id.gifters1);
            gifters2 = itemView.findViewById(R.id.gifters2);


            iv_start = itemView.findViewById(R.id.iv_start);
            iv_stop = itemView.findViewById(R.id.iv_stop);
            fram_video = itemView.findViewById(R.id.fram_video);
            chk_like = itemView.findViewById(R.id.chk_like);
            iv_action_menu = itemView.findViewById(R.id.iv_action_menu);
            txt_follow = itemView.findViewById(R.id.txt_follow);
            iv_image_profile = itemView.findViewById(R.id.iv_image_profile);
            txt_caption = itemView.findViewById(R.id.txt_caption);
        }
    }
}