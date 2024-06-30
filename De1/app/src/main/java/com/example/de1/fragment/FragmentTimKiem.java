package com.example.de1.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.de1.R;
import com.example.de1.adapter.RecycleViewAdapter;
import com.example.de1.dal.SQLiteHelper;
import com.example.de1.model.Item;

import java.util.Calendar;
import java.util.List;

public class FragmentTimKiem extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button btSearch;
    private SearchView searchView;
    private Spinner spTinhtrang;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db= new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Item> list = db.searchByTitle(newText);
                adapter.setList(list);
                return true;
            }
        });
        btSearch.setOnClickListener(this);
        spTinhtrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cate = spTinhtrang.getItemAtPosition(position).toString();
                List<Item> list;
                if(!cate.equals("All")){
                    list = db.searchByCategory(cate);
                }
                else{
                    list = db.getAll();
                }
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void initView(View v){
        recyclerView = v.findViewById(R.id.recycleView);
        btSearch = v.findViewById(R.id.btSearch);
        searchView = v.findViewById(R.id.search);
        spTinhtrang = v.findViewById(R.id.spTinhtrang);
        String[] arr  = getResources().getStringArray(R.array.tinhtrang);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0 ; i < arr.length ; i++){
            arr1[1+i] = arr[i];
        }
        spTinhtrang.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));
    }
    @Override
    public void onClick(View v) {
    }
}
