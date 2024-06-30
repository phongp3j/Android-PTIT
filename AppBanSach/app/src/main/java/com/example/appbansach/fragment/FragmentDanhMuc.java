package com.example.appbansach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appbansach.R;
import com.example.appbansach.admin.CategoryRecycleViewAdapter;
import com.example.appbansach.admin.ListBookActivity;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Category;

import java.util.List;

public class FragmentDanhMuc extends Fragment implements CategoryRecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    CategoryRecycleViewAdapter adapter;
    private SQLiteHelper db;
    private TextView tvdalam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danhmuc,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclView);
        tvdalam = view.findViewById(R.id.tongdanhmuc);
        adapter = new CategoryRecycleViewAdapter();
        db=  new SQLiteHelper(getContext());
        List<Category> list = db.getAllCategory();
        adapter.setList(list);
        tvdalam.setText("Tổng số danh mục: "+list.size());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        Category item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), ListBookActivity.class);
        intent.putExtra("category",item);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        List<Category> list = db.getAllCategory();
        adapter.setList(list);
        tvdalam.setText("Tổng số danh mục: "+list.size());
    }
}
