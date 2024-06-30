package com.example.appbansach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.admin.BookRecycleViewAdapter;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.data.DataManager;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Order;
import com.example.appbansach.model.User;
import com.example.appbansach.user.OrderRecycleViewAdapter;
import com.example.appbansach.user.ViewBookActivity;

import java.util.List;

public class FragmentGioHang  extends Fragment implements OrderRecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    OrderRecycleViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_giohang,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclView);
        adapter = new OrderRecycleViewAdapter(getContext());
        db=  new SQLiteHelper(getContext());
        User user = db.getUserByEmail(DataManager.getInstance().getData());
        List<Order> list = db.getOrderByIdUser(user.getId());
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

}