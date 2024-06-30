package com.example.myapplication.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private Context context;
    private List<Date> mList;
    private List<Date> listBackup;
    private DateItemListener mDateItem;
    public DateAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<Date> getBackup(){
        return listBackup;
    }

    public void setmList(List<Date> mList){
        this.mList = mList;
    }
    public void filterList(List<Date> filterList){
        mList=filterList;
        notifyDataSetChanged();
    }
    public void setClickListener(DateItemListener mDateItem){
        this.mDateItem = mDateItem;
    }

    public void update(int position, Date date){
        listBackup.set(position,date);
        mList.set(position,date);
        notifyDataSetChanged();
    }
    public Date getItem(int position){
        return mList.get(position);
    }
    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        Date date = mList.get(position);
        if(date == null){
            return;
        }
        holder.img.setImageResource(date.getImg());
        holder.tvName.setText(date.getName());
        holder.tvDate.setText(date.getDate());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa!!!");
                builder.setMessage("Ban co muon xoa "+ date.getName());
                builder.setIcon(R.drawable.cancel);
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
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void add(Date d){
        listBackup.add(d);
        mList.add(d);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class DateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName, tvDate;
        private Button remove;
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageitem);
            tvName = itemView.findViewById(R.id.txtname);
            tvDate = itemView.findViewById(R.id.txtMieuTa);
            remove = itemView.findViewById(R.id.btRemove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mDateItem!=null){
                mDateItem.onItemClick(itemView,getAdapterPosition());
            }
        }
    }
    public interface DateItemListener{
        void onItemClick(View view, int position);
    }
}
