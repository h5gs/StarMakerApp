package com.archirayan.starmakerapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.activity.Grantpermissons;
import com.archirayan.starmakerapp.activity.RecordingSongs;
import com.archirayan.starmakerapp.utils.Utility;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by archirayan on 21/2/18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.btn_sing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    final Dialog dialog = new Dialog(getApplicationContext());
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
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView btn_sing;

        MyViewHolder(View view) {
            super(view);
            btn_sing = itemView.findViewById(R.id.btn_sing);

        }
    }
}