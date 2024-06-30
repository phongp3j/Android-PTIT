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
import com.example.appbansach.admin.CategoryRecycleViewAdapter;
import com.example.appbansach.admin.UpdateDeleteUserActivity;
import com.example.appbansach.admin.UserRecycleViewAdapter;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Category;
import com.example.appbansach.model.User;

import java.util.List;

public class FragmentUser extends Fragment implements UserRecycleViewAdapter.ItemUserListener {
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    UserRecycleViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclView);
        adapter = new UserRecycleViewAdapter();
        db=  new SQLiteHelper(getContext());
        List<User> list = db.getAllUser();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        User item = adapter.getUser(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteUserActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        List<User> list = db.getAllUser();
        adapter.setList(list);
    }
}
