package com.example.de1.fragment;

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

import com.example.de1.R;
import com.example.de1.UpdateDeleteActivity;
import com.example.de1.adapter.RecycleViewAdapter;
import com.example.de1.dal.SQLiteHelper;
import com.example.de1.model.Item;

import java.util.List;

public class FragmentThongTin extends Fragment{
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history,container,false);
    }

}
