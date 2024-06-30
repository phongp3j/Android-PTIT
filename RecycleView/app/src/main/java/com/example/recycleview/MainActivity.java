package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recycleview.model.Cat;
import com.example.recycleview.model.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener {
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private List<Cat> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list.add(new Cat(R.drawable.cat1,"Phong 1"));
        list.add(new Cat(R.drawable.cat2,"Phong 2"));
        catAdapter = new CatAdapter(list);
        catAdapter.setCatItemListener(this);
        recyclerView = findViewById(R.id.rview);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(catAdapter);
    }

    @Override
    public void onItemClick(View view, int postion) {
        Cat c = list.get(postion);
        Toast.makeText(this,c.getName(),Toast.LENGTH_SHORT).show();
    }
}