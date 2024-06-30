package com.example.tablayoutcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tablayoutcrud.R;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private int[] imgs;

    public SpinnerAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spinner,parent,false);
        ImageView img = view.findViewById(R.id.img);
        img.setImageResource(imgs[position]);
        return view;
    }
}
