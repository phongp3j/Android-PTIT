package com.example.crud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private Context context;
    private List<Cat> mList;
    private List<Cat> listBackup;
    private CatItemListener mCatItem;
    public CatAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public void add(Cat c){
        listBackup.add(c);
        mList.add(c);
        notifyDataSetChanged();
    }

    public List<Cat> getBackup(){
        return listBackup;
    }
    public void update(Cat c, int position){
        listBackup.set(position,c);
        mList.set(position,c);
        notifyDataSetChanged();
    }

    public void filterList(List<Cat> filter){
        mList = filter;
        notifyDataSetChanged();
    }
    public Cat getItem(int positon){
        return mList.get(positon);
    }
    public void setClickListener(CatItemListener mCatItem){
        this.mCatItem = mCatItem;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        if(cat == null){
            return;
        }
        holder.imageView.setImageResource(cat.getImage());
        holder.name.setText(cat.getName());
        holder.decribe.setText(cat.getDecribe());
        holder.price.setText(cat.getPrice()+"");
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setMessage("Ban co chac muon xoa " + cat.getName());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBackup.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                });
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               AlertDialog alert = builder.create();
               alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }


    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView name, decribe, price;
        private Button remove;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            decribe = itemView.findViewById(R.id.decribe);
            price = itemView.findViewById(R.id.price);
            remove = itemView.findViewById(R.id.remove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        if(mCatItem!=null){
            mCatItem.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface CatItemListener{
        void onItemClick(View view, int position);
    }
}
