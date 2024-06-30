package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Sach;
import com.example.myapplication.model.SachAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SachAdapter.SachItemListener,SearchView.OnQueryTextListener {
    private EditText edTen,edTacgia,edGio;
    private CheckBox cb1,cb2,cb3;
    private Button time,add,update;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private SachAdapter sachAdapter;
    private int pcur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sachAdapter = new SachAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(sachAdapter);
        sachAdapter.setClickListener(this);
        time.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = new Sach();
                String ten = edTen.getText().toString();
                String tacgia = edTacgia.getText().toString();
                String gio = edGio.getText().toString();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty() && !tacgia.isEmpty() && !gio.isEmpty() ){
                    sach.setTen(ten);
                    sach.setTacgia(tacgia);
                    sach.setGio(gio);
                    sach.setCb1(cb11);
                    sach.setCb2(cb21);
                    sach.setCb3(cb31);
                    sachAdapter.add(sach);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhap data",Toast.LENGTH_LONG).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = new Sach();
                String ten = edTen.getText().toString();
                String tacgia = edTacgia.getText().toString();
                String gio = edGio.getText().toString();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty() && !tacgia.isEmpty() && !gio.isEmpty() ){
                    sach.setTen(ten);
                    sach.setTacgia(tacgia);
                    sach.setGio(gio);
                    sach.setCb1(cb11);
                    sach.setCb2(cb21);
                    sach.setCb3(cb31);
                    sachAdapter.update(pcur,sach);
                    add.setEnabled(true);
                    update.setEnabled(false);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhap data",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void initView(){
        edTen = findViewById(R.id.edTen);
        edTacgia = findViewById(R.id.edTacgia);
        edGio = findViewById(R.id.edGio);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        time = findViewById(R.id.time);
        add = findViewById(R.id.btadd);
        update = findViewById(R.id.btupdate);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        update.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if(v==time){
            Calendar c = Calendar.getInstance();
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    edGio.setText(i+":"+i1);
                }
            },hh,mm,true);
            timePickerDialog.show();
        }

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
        List<Sach> filterList = new ArrayList<>();
        for(Sach i : sachAdapter.getBackup()){
            if(i.getTen().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this,"No data ",Toast.LENGTH_LONG).show();
        }
        else{
            sachAdapter.filterList(filterList);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        add.setEnabled(false);
        update.setEnabled(true);
        pcur = position;
        Sach sach = sachAdapter.getItem(position);
        if(sach.isCb1()){
            cb1.setChecked(true);
        }
        else {
            cb1.setChecked(false);
        }
        if(sach.isCb2()){
            cb2.setChecked(true);
        }
        else {
            cb2.setChecked(false);
        }
        if(sach.isCb3()){
            cb3.setChecked(true);
        }
        else {
            cb3.setChecked(false);
        }
        edTen.setText(sach.getTen());
        edTacgia.setText(sach.getTacgia());
        edGio.setText(sach.getGio());
    }
}