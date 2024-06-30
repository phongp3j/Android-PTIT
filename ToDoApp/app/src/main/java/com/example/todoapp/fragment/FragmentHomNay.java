package com.example.todoapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.user.UpdateDeleteActivity;
import com.example.todoapp.adapter.RecycleViewAdapter;
import com.example.todoapp.data.DataManager;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentHomNay extends Fragment implements RecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;
    private TextView tvdalam;
    String receivedData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homnay,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclView);
        tvdalam = view.findViewById(R.id.dalam);
        receivedData= DataManager.getInstance().getData();
        adapter = new RecycleViewAdapter();
        db=  new SQLiteHelper(getContext());
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<Task> list = db.getByDate(f.format(d),receivedData);
        adapter.setList(list);
        tvdalam.setText("Số việc đã làm: "+tong(list));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

    }

    public String tong(List<Task> list){
        String res = "";
        int x= 0;
        for(Task i : list){
            if(i.getTinhTrang().equals("Đã Hoàn Thành")){
                x++;
            }
        }
        res = x+"/"+list.size();
        return  res;
    }
    @Override
    public void onItemClick(View view, int position) {
        Task item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<Task> list = db.getByDate(f.format(d),receivedData);
        adapter.setList(list);
        tvdalam.setText("Số việc đã làm: "+tong(list));
    }
}
