package com.example.listview.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listview.R;

public class dogAdapter extends ArrayAdapter<dog> {
    private Context context;
    private dog[] list;

    public dogAdapter(@NonNull Context context, dog[] list) {
        super(context, R.layout.item,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item,null, true);
        ImageView img = view.findViewById(R.id.img);
        TextView tv = view.findViewById(R.id.tv);
        dog tmp = list[position];
        img.setImageResource(tmp.getImg());
        tv.setText(tmp.getName());

        return view;
    }

    @Nullable
    @Override
    public dog getItem(int position) {
        return list[position];
    }
}
