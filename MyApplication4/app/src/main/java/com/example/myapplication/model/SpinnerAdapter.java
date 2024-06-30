package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.myapplication.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs = {R.drawable.car,R.drawable.airplan,R.drawable.motor};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
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
        View item = LayoutInflater.from(context).inflate(R.layout.item_imagesp,parent,false);
        ImageView img = item.findViewById(R.id.imagesp);
        img.setImageResource(imgs[position]);
        return item;
    }
}
