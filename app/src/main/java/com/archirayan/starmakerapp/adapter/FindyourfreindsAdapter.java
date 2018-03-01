package com.archirayan.starmakerapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.model.ContactListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archirayan on 26/2/18.
 */

public class FindyourfreindsAdapter extends RecyclerView.Adapter<FindyourfreindsAdapter.MyViewHolder> {

    public static List<ContactListItem> mFilteredList;
    public static ArrayList<String> strCreditId1 = new ArrayList<>();
    public static ArrayList<ContactListItem> strCreditId = new ArrayList<>();
    public static String STR_ConatctName;
    private Context mContext;
    public String str_number;
    ContactListItem contactListItem;

    ArrayList<String> multiselect_list = new ArrayList<String>();
    private List<ContactListItem> contactList;
    private String str_id, str_id1;

    public FindyourfreindsAdapter(List<ContactListItem> contactList, Context mContext) {
        this.contactList = contactList;
        this.mContext = mContext;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_findyourinvitefreind_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        contactListItem = contactList.get(position);
        holder.txt_contectname.setText(contactListItem.getContactName());
        /*holder.ivContactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_number = contactList.get(position).getContactNumber();

                Log.d("str_number", "====== str_number======" + str_number);
                onCall();

            }
        });*/

        holder.btn_invite.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                str_id = contactList.get(position).getContactName();
                str_id1 = contactList.get(position).getContactNumber();
                contactListItem = new ContactListItem();
                contactListItem.setContactName(str_id);
                contactListItem.setContactNumber(str_id1);
                strCreditId.add(contactListItem);
                STR_ConatctName = TextUtils.join(",", strCreditId);
                Log.d("strCreditId", "========   Answer =====" + STR_ConatctName);
                ArrayList<String> numbersArrayList = new ArrayList<>();
                if (strCreditId.size() > 0)
                {
                    for (int i = 0; i < strCreditId.size(); i++)
                    {
                        numbersArrayList.add(String.valueOf(strCreditId.get(i).getContactNumber()));
                    }
                    String toNumbers = TextUtils.join(";", numbersArrayList);
                    for (String s : numbersArrayList) {
                        toNumbers = toNumbers + s + ";";
                    }
                    toNumbers = toNumbers.substring(0, toNumbers.length() - 1);
                    Uri sendSmsTo = Uri.parse("smsto:" + toNumbers);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, sendSmsTo);
                    intent.putExtra("sms_body", "hiii");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.getApplicationContext().startActivity(intent);
                }
                else
                {
                    Toast.makeText(mContext, "Please select your contact", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_contectname;
        private Button btn_invite;

        MyViewHolder(View view) {
            super(view);

            txt_contectname = itemView.findViewById(R.id.txt_contectname);
            btn_invite = itemView.findViewById(R.id.btn_invite);

        }
    }
}