package com.example.todoapp.fragment;

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

import com.example.todoapp.R;
import com.example.todoapp.adapter.RecycleViewAdapter;
import com.example.todoapp.data.DataManager;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.model.Task;

import java.util.Calendar;
import java.util.List;

public class FragmentTimKiem extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView tvTong;
    private Button btSearch;
    private SearchView searchView;
    private EditText efrom,eto;
    private Spinner spCategory;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;
    String receivedData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timkiem,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db= new SQLiteHelper(getContext());
        receivedData= DataManager.getInstance().getData();
        List<Task> list = db.getAll(receivedData);
        adapter.setList(list);
        tvTong.setText("Tong tien: "+tong(list));
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
                List<Task> list = db.searchByTitle(newText,receivedData);
                tvTong.setText("Tong : "+tong(list));
                adapter.setList(list);
                return true;
            }
        });
        efrom.setOnClickListener(this);
        eto.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cate = spCategory.getItemAtPosition(position).toString();
                List<Task> list;
                if(!cate.equals("All")){
                    list = db.searchByCategory(cate,receivedData);
                }
                else{
                    list = db.getAll(receivedData);
                }
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private int tong(List<Task> list){
        int t = 0 ;
        for(Task i : list){
            t++;
        }
        return t;
    }
    public void initView(View v){
        recyclerView = v.findViewById(R.id.recycleView);
        tvTong  = v.findViewById(R.id.tvTong);
        btSearch = v.findViewById(R.id.btSearch);
        searchView = v.findViewById(R.id.search);
        efrom = v.findViewById(R.id.eFrom);
        eto = v.findViewById(R.id.eTo);
        spCategory = v.findViewById(R.id.spCategory);
        String[] arr  = getResources().getStringArray(R.array.tinhtrang);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0 ; i < arr.length ; i++){
            arr1[1+i] = arr[i];
        }
        spCategory.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));
    }
    @Override
    public void onClick(View v) {
        if(v == efrom){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date;
                    month += 1; // Vì Java sử dụng chỉ số bắt đầu từ 0 cho tháng

                    // Định dạng ngày tháng nếu tháng lớn hơn 9
                    if (month > 9) {
                        if (dayOfMonth < 10) {
                            date = "0" + dayOfMonth + "/" + month + "/" + year;
                        } else {
                            date = dayOfMonth + "/" + month + "/" + year;
                        }
                    }
                    // Định dạng ngày tháng nếu tháng nhỏ hơn hoặc bằng 9
                    else {
                        if (dayOfMonth < 10) {
                            date = "0" + dayOfMonth + "/0" + month + "/" + year;
                        } else {
                            date = dayOfMonth + "/0" + month + "/" + year;
                        }
                    }

                    efrom.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v == eto){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date;
                    month += 1; // Vì Java sử dụng chỉ số bắt đầu từ 0 cho tháng

                    // Định dạng ngày tháng nếu tháng lớn hơn 9
                    if (month > 9) {
                        if (dayOfMonth < 10) {
                            date = "0" + dayOfMonth + "/" + month + "/" + year;
                        } else {
                            date = dayOfMonth + "/" + month + "/" + year;
                        }
                    }
                    // Định dạng ngày tháng nếu tháng nhỏ hơn hoặc bằng 9
                    else {
                        if (dayOfMonth < 10) {
                            date = "0" + dayOfMonth + "/0" + month + "/" + year;
                        } else {
                            date = dayOfMonth + "/0" + month + "/" + year;
                        }
                    }

                    eto.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v == btSearch){
            String from = efrom.getText().toString();
            String to = eto.getText().toString();
            if(!from.isEmpty() && !to.isEmpty()){
                List<Task> list = db.searchByDate(from,to,receivedData);
                adapter.setList(list);
                tvTong.setText("Tong : "+tong(list));
            }
        }
    }
}