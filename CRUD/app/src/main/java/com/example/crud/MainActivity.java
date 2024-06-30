package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener,SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private EditText edname,eddecribe,edprice;
    private Button add,update;
    private CatAdapter adapter;
    private SearchView searchView;

    private int positionClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter = new CatAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat c=  new Cat();
                String name = edname.getText().toString();
                String decribe = eddecribe.getText().toString();
                String price1 = edprice.getText().toString();
                double price = 0;
                try {
                    price = Double.parseDouble(price1);
                    c.setImage(R.drawable.avt);
                    c.setName(name);
                    c.setDecribe(decribe);
                    c.setPrice(price);
                    adapter.add(c);
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai",Toast.LENGTH_LONG).show();
                }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat c=  new Cat();
                String name = edname.getText().toString();
                String decribe = eddecribe.getText().toString();
                String price1 = edprice.getText().toString();
                double price = 0;
                try {
                    price = Double.parseDouble(price1);
                    c.setImage(R.drawable.avt);
                    c.setName(name);
                    c.setDecribe(decribe);
                    c.setPrice(price);
                    adapter.update(c,positionClick);
                    add.setEnabled(true);
                    update.setEnabled(false);
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void init(){
        recyclerView = findViewById(R.id.recycleView);
        edname = findViewById(R.id.edName);
        eddecribe = findViewById(R.id.edDecribe);
        edprice = findViewById(R.id.edPrice);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        update.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        positionClick = position;
        add.setEnabled(false);
        update.setEnabled(true);
        Cat cat = adapter.getItem(position);
        edname.setText(cat.getName());
        eddecribe.setText(cat.getDecribe());
        edprice.setText(cat.getPrice()+"");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }
    public void filter(String s){
        List<Cat> filterlist = new ArrayList<>();
        for(Cat i : adapter.getBackup()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No data ",Toast.LENGTH_LONG).show();
        }
        else{
            adapter.filterList(filterlist);
        }
    }
}