package com.example.spinner_object.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.spinner_object.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] list = {R.drawable.dog,R.drawable.ic_launcher_background};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image,parent,false);
        ImageView img = view.findViewById(R.id.img);
        img.setImageResource(list[position]);
        return view;
    }
}
