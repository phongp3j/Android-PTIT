package com.example.myapplication.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private Context context;
    private SachItemListener mSachItem;

    private List<Sach> mList;
    private List<Sach> listBackup;

    public SachAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<Sach> getBackup(){
        return listBackup;
    }

    public void setmList(List<Sach> mList){
        this.mList = mList;
    }

    public void filterList(List<Sach> filterList){
        mList=filterList;
        notifyDataSetChanged();
    }
    public void setClickListener(SachItemListener mSachItem){
        this.mSachItem = mSachItem;
    }

    public void update(int position, Sach sach){
        listBackup.set(position,sach);
        mList.set(position,sach);
        notifyDataSetChanged();
    }
    public Sach getItem(int position){
        return mList.get(position);
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = mList.get(position);
        if(sach == null){
            return;
        }
        holder.tvTen.setText(sach.getTen());
        holder.tvGio.setText(sach.getGio());
        if(sach.isCb1()){
            holder.cb1.setChecked(true);
            holder.cb1.setClickable(false);
        }
        else{
            holder.cb1.setEnabled(false);
            holder.cb1.setClickable(false);
        }
        if(sach.isCb2()){
            holder.cb2.setChecked(true);
            holder.cb2.setClickable(false);
        }
        else{
            holder.cb2.setEnabled(false);
            holder.cb2.setClickable(false);
        }
        if(sach.isCb3()){
            holder.cb3.setChecked(true);
            holder.cb3.setClickable(false);
        }
        else{
            holder.cb3.setClickable(false);
        }
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa!!!");
                builder.setMessage("Ban co muon xoa "+ sach.getTen());
                builder.setIcon(R.drawable.cancel24px);
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
    public void add(Sach d){
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

    public class SachViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTen,tvTacgia,tvGio;
        private CheckBox cb1,cb2,cb3;
        private Button remove;

        public SachViewHolder(@NonNull View itemView){
            super(itemView);
            tvTen=itemView.findViewById(R.id.tvTen);
            tvGio = itemView.findViewById(R.id.tvGio);
            cb1=itemView.findViewById(R.id.cbphephan);
            cb2=itemView.findViewById(R.id.cbsuthat);
            cb3=itemView.findViewById(R.id.cbchambiem);
            remove=itemView.findViewById(R.id.btremove);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mSachItem!=null){
                mSachItem.onItemClick(itemView,getAdapterPosition());
            }
        }
    }
    public interface SachItemListener{
        void onItemClick(View view, int position);
    }
}
