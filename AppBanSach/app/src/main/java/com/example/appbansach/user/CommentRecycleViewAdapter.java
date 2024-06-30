package com.example.appbansach.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.model.Comment;
import com.example.appbansach.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentRecycleViewAdapter extends RecyclerView.Adapter<CommentRecycleViewAdapter.ItemViewHolder> {
    private List<Comment> list;
    private ItemUserListener itemListener;

    public CommentRecycleViewAdapter(){
        list = new ArrayList<>();
    }

    public  void  setList(List<Comment> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public Comment getComment(int position){
        return list.get(position);
    }
    public void setItemListener(ItemUserListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhgia,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Comment user = list.get(position);
        holder.ten.setText(user.getEmail());
        holder.danhgia.setText(user.getDanhgia());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,danhgia;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTenuser);
            danhgia = itemView.findViewById(R.id.tvDanhgia);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener!=null){
                itemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface ItemUserListener{
        void onItemClick(View view,int position);
    }
}
