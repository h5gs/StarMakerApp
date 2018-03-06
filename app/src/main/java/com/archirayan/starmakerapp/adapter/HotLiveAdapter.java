package com.archirayan.starmakerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.archirayan.starmakerapp.R;

/**
 * Created by archirayan on 1/3/18.
 */

public class HotLiveAdapter extends BaseAdapter{

    private Context context;

    public HotLiveAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 15;
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
        view = inflater.inflate(R.layout.hot_live_list,null);

        return view;

    }
}
