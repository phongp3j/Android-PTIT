package com.example.recycleview.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cat> mlist;
    private CatItemListener catItemListener;
    public CatAdapter(List<Cat> mlist) {
        this.mlist = mlist;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
            Cat cat = mlist.get(position);
            if(cat != null){
                holder.img.setImageResource(cat.getImg());
                holder.name.setText(cat.getName());
            }
    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView name;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iimg);
            name = itemView.findViewById(R.id.iname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(catItemListener!= null){
                catItemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface CatItemListener{
        public void onItemClick(View view, int postion);
    }
}
