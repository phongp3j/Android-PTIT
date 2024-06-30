package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Date;
import com.example.myapplication.model.DateAdapter;
import com.example.myapplication.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DateAdapter.DateItemListener , SearchView.OnQueryTextListener{
    private Spinner sp;
    private RecyclerView recyclerView;
    private DateAdapter dateAdapter;
    private EditText edname,eddate;
    private Button btadd,btupdate;
    private int pcur;

    private SearchView searchView;
    private int[] imgs = {R.drawable.car,R.drawable.airplan,R.drawable.motor};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dateAdapter = new DateAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(dateAdapter);
        dateAdapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                String i = sp.getSelectedItem().toString();
                String name = edname.getText().toString();
                String date1 = eddate.getText().toString();
                int img = R.drawable.airplan;
                try {
                    img = imgs[Integer.parseInt(i)];
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai",Toast.LENGTH_LONG).show();
                }
                date.setImg(img);
                date.setName(name);
                date.setDate(date1);
                dateAdapter.add(date);
            }
        });
        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                String i = sp.getSelectedItem().toString();
                String name = edname.getText().toString();
                String date1 = eddate.getText().toString();
                int img = R.drawable.airplan;
                try {
                    img = imgs[Integer.parseInt(i)];
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai",Toast.LENGTH_LONG).show();
                }
                date.setImg(img);
                date.setName(name);
                date.setDate(date1);
                dateAdapter.update(pcur,date);
                btadd.setEnabled(true);
                btupdate.setEnabled(false);
            }
        });
    }

    private void initView() {
        sp = findViewById(R.id.sp);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView = findViewById(R.id.recyclerView);
        edname = findViewById(R.id.name);
        eddate = findViewById(R.id.date);
        btadd = findViewById(R.id.btAdd);
        btupdate = findViewById(R.id.btupdate);
        btupdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        btadd.setEnabled(false);
        btupdate.setEnabled(true);
        pcur = position;
        Date date = dateAdapter.getItem(position);
        int img = date.getImg();
        int p = 0;
        for(int i = 0 ; i < imgs.length ; i++){
            if(img == imgs[i]){
                p = i ;
                break;
            }
        }
        sp.setSelection(p);
        edname.setText(date.getName());
        eddate.setText(date.getDate());

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
         return false;
    }
    public void filter(String s){
        List<Date> filterList = new ArrayList<>();
        for(Date i : dateAdapter.getBackup()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this,"No data ",Toast.LENGTH_LONG).show();
        }
        else{
            dateAdapter.filterList(filterList);
        }
    }
}