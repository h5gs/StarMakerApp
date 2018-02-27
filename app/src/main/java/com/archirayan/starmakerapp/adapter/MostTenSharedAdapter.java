package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 27/2/18.
 */

public class MostTenSharedAdapter extends BaseAdapter{

    public MostTenSharedAdapter(Context context) {
        this.context = context;
    }

    private Context context;

    @Override
    public int getCount() {
        return 6;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.showlist_item,null);

        return view;
    }
}
