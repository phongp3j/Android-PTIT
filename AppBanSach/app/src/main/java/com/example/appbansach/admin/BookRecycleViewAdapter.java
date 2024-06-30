package com.example.appbansach.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appbansach.R;
import com.example.appbansach.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookRecycleViewAdapter extends RecyclerView.Adapter<BookRecycleViewAdapter.BookAdminViewHolder>{
    private List<Book> list;
    private BookRecycleViewAdapter.ItemListener itemListener;
    public BookRecycleViewAdapter(){
        list = new ArrayList<>();
    }
    public  void  setList(List<Book> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(BookRecycleViewAdapter.ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Book getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public BookAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new BookAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdminViewHolder holder, int position) {
        Book item = list.get(position);
        holder.tvTen.setText(item.getTen());
        holder.tvDaban.setText("Đã bán: "+item.getDaban());
        holder.tvGia.setText("Giá :"+item.getGia()+"VND");
        holder.tvMota.setText(item.getMota());
        Picasso.get().load(item.getUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookAdminViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvTen,tvMota,tvGia,tvDaban;
        public BookAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvTen = itemView.findViewById(R.id.tvTensach);
            tvMota = itemView.findViewById(R.id.tvMota);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvDaban = itemView.findViewById(R.id.tvDaban);
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
