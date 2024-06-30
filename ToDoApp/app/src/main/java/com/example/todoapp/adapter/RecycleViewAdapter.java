package com.example.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.todoapp.R;
import com.example.todoapp.model.Task;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Task> list;
    private ItemListener itemListener;
    public RecycleViewAdapter(){
        list = new ArrayList<>();
    }
    public  void  setList(List<Task> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Task getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Task item = list.get(position);
        holder.ten.setText(item.getTenCongViec());
        holder.gio.setText(item.getGioBatDau());
        holder.ngay.setText(item.getNgay());
        holder.tinhtrang.setText(item.getTinhTrang());
        Glide.with(holder.itemView.getContext())
                .load(R.drawable.logo)
                .into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,gio,ngay,tinhtrang;
        private CircleImageView circleImageView;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTencongviec);
            gio = itemView.findViewById(R.id.tvGiobatdau);
            ngay = itemView.findViewById(R.id.tvNgay);
            tinhtrang = itemView.findViewById(R.id.tvTinhtrang);
            circleImageView= itemView.findViewById(R.id.profile_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener!=null){
                itemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
