package com.example.appbansach.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Order;
import com.example.appbansach.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderRecycleViewAdapter extends RecyclerView.Adapter<OrderRecycleViewAdapter.OrderViewHolder>{
    private List<Order> list;
    private Context context;
    private OrderRecycleViewAdapter.ItemListener itemListener;
    public OrderRecycleViewAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }
    public  void  setList(List<Order> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(OrderRecycleViewAdapter.ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Order getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order item = list.get(position);
        SQLiteHelper db = new SQLiteHelper(context);
        Book book = db.getBookById(item.getIdSach());
        User user = db.getUserById(item.getIdUser());
        holder.tvTen.setText(book.getTen());
        holder.tvNguoinhan.setText("Nguời nhận: "+item.getNguoinhan());
        holder.tvSdt.setText("SDT:"+item.getSdt());
        holder.tvDiachi.setText("Địa chỉ :"+item.getDiachi());
        Picasso.get().load(book.getUrl()).into(holder.img);
        holder.tvSoluong.setText("Số lượng: "+item.getSoluong());
        holder.tvTongtien.setText("Total: "+item.getGia());
        holder.tvTinhtrang.setText("Trạng thái: "+item.getTrangthai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvTen,tvNguoinhan,tvSdt,tvDiachi,tvSoluong,tvTongtien,tvTinhtrang;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvTen = itemView.findViewById(R.id.tvTensach);
            tvNguoinhan = itemView.findViewById(R.id.tvNguoinhan);
            tvSdt = itemView.findViewById(R.id.tvSdt);
            tvDiachi = itemView.findViewById(R.id.tvDiachi);
            tvSoluong = itemView.findViewById(R.id.tvSoluong);
            tvTongtien = itemView.findViewById(R.id.tvTongtien);
            tvTinhtrang = itemView.findViewById(R.id.tvTinhtrang);
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
