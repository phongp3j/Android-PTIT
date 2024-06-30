package com.example.ktra2.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.R;
import com.example.ktra2.adapter.RecycleViewAdapter;
import com.example.ktra2.dal.SQLiteHelper;
import com.example.ktra2.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentTimkiem extends Fragment  {
    private RecyclerView recyclerView;

    private TextView tvtong;
    private Spinner phongthi;
    private SearchView searchView;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timkiem,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        phongthi = view.findViewById(R.id.phongthi);
        String[] arr  = getResources().getStringArray(R.array.phongthi);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0 ; i < arr.length ; i++){
            arr1[1+i] = arr[i];
        }
        phongthi.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));
        searchView = view.findViewById(R.id.search);
        tvtong = view.findViewById(R.id.tvTong);
        adapter = new RecycleViewAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        db=  new SQLiteHelper(getContext());
        List<Item> list = new ArrayList<>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Item> list = db.searchByTitle(newText);
                tvtong.setText("Tong : "+list.size());
                adapter.setList(list);
                return true;
            }
        });
        phongthi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tmp = phongthi.getItemAtPosition(position).toString();
                List<Item> list1;
                if(!tmp.equals("All")){
                    list1 = db.searchByCategory(tmp);
                    tvtong.setText("Tong : "+list.size());
                }
                else{
                    list1 = db.getAll();
                }
                adapter.setList(list1);
                tvtong.setText("Tong : "+list.size());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
