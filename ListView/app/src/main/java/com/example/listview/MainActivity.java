package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview.model.dog;
import com.example.listview.model.dogAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView lview;
    dogAdapter adapter;
    private dog[] list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lview = findViewById(R.id.lview);
        initData();
        adapter = new dogAdapter(this,list);
        lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dog tmp = adapter.getItem(i);
                Toast.makeText(getApplicationContext(),"hello"+tmp.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        Integer[] img = {R.drawable.dog,R.drawable.ic_launcher_background};
        String[] name = {"Phoc soc","Ngao"};
        list = new dog[img.length];
        for(int i = 0 ; i < img.length ; i++){
            list[i] = new dog(img[i],name[i]);
        }
    }
}