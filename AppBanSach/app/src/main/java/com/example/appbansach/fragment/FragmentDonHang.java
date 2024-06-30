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
import com.example.appbansach.admin.OrderUpdateActivity;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Order;
import com.example.appbansach.user.OrderRecycleViewAdapter;

import java.util.List;

public class FragmentDonHang extends Fragment implements OrderRecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    OrderRecycleViewAdapter adapter;
    List<Order> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_donhang,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclView);
        SQLiteHelper db = new SQLiteHelper(getContext());
        list = db.getAllOrder();
        adapter = new OrderRecycleViewAdapter(getContext());
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Order order = list.get(position);
        Intent intent = new Intent(getContext(), OrderUpdateActivity.class);
        intent.putExtra("order",order);
        startActivity(intent);
    }
}
