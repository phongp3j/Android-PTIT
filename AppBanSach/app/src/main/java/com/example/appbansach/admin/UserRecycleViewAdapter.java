package com.example.appbansach.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRecycleViewAdapter extends RecyclerView.Adapter<UserRecycleViewAdapter.ItemViewHolder> {
    private List<User> list;
    private ItemUserListener itemListener;

    public UserRecycleViewAdapter(){
        list = new ArrayList<>();
    }

    public  void  setList(List<User> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public User getUser(int position){
        return list.get(position);
    }
    public void setItemListener(ItemUserListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        User user = list.get(position);
        holder.ten.setText("Tên: "+user.getFullname());
        holder.role.setText("Vai trò: "+user.getRole());
        holder.user.setText("TK: "+user.getEmail());
        holder.pass.setText("MK: "+user.getPass());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,user,pass, role;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTenuser);
            user = itemView.findViewById(R.id.tvUser);
            pass = itemView.findViewById(R.id.tvPass);
            role = itemView.findViewById(R.id.tvRole);
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
