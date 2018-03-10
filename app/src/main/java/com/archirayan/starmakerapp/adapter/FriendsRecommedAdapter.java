package com.archirayan.starmakerapp.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.LoginActivity;
import com.archirayan.starmakerapp.activity.MainActivity;
import com.archirayan.starmakerapp.model.FollowList;
import com.archirayan.starmakerapp.model.GetFollowResponse;
import com.archirayan.starmakerapp.model.LoginResponse;
import com.archirayan.starmakerapp.utils.Constant;
import com.archirayan.starmakerapp.utils.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by archirayan on 5/3/18.
 */

public class FriendsRecommedAdapter extends RecyclerView.Adapter<FriendsRecommedAdapter.MyViewHolder>{

    private static final String TAG = "FriendsRecommedAdapter";
    private Context context;
    private ArrayList<FollowList> followLists;
    private ProgressDialog pd;
    private String follow_id;

    public FriendsRecommedAdapter(Context context, ArrayList<FollowList> followLists) {
        this.context = context;
        this.followLists = followLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friendsrecommend_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txt_profilename.setText(followLists.get(position).getUsername());
        holder.txt_comment.setText("hiii");

        if (followLists.get(position).getImage().isEmpty()) {
            Picasso.with(context).load(R.drawable.ic_back_round);
        } else {
            Picasso.with(context).load(followLists.get(position).getImage()).placeholder(R.drawable.ic_back_round).into(holder.iv_profile);
        }
        holder.iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.card_view.setVisibility(View.GONE);
            }
        });

        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow_id = followLists.get(position).getId().toString();
                if (follow_id.toString().isEmpty()){
                    Toast.makeText(context, "You not Successafully Follow", Toast.LENGTH_SHORT).show();
                }else {
                    getfollow();
                }
            }
        });

    }

    private void getfollow()
    {
        pd = new ProgressDialog(context);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_id",Utils.ReadSharePrefrence(context.getApplicationContext(),Constant.USERID));
        params.put("follow_user_id",follow_id.toString());

        Log.e(TAG, "DriverURL:" + Constant.URL + "follow.php?" + params);
        Log.e(TAG, params.toString());
        client.post(getApplicationContext(), Constant.URL+"follow.php?",params, new JsonHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
            }
            @Override
            public void onFinish()
            {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "LOGIN DriverRESPONSE-" + response);
                GetFollowResponse model = new Gson().fromJson(new String(String.valueOf(response)),GetFollowResponse.class);
                pd.dismiss();
                if (model.getStatus().equalsIgnoreCase("true")) {
                    Toast.makeText(context, model.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }else {
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

    @Override
    public int getItemCount()
    {
        return followLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_cancle;
        private CircleImageView iv_profile;
        private TextView txt_profilename,txt_comment;
        private Button btn_follow;
        private CardView card_view;
        public MyViewHolder(View itemView) {
            super(itemView);

            iv_cancle = itemView.findViewById(R.id.iv_cancle);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            txt_profilename = itemView.findViewById(R.id.txt_profilename);
            txt_comment = itemView.findViewById(R.id.txt_comment);
            btn_follow = itemView.findViewById(R.id.btn_follow);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
