package com.example.appbansach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.data.DataManager;
import com.example.appbansach.model.Order;
import com.example.appbansach.model.User;
import com.example.appbansach.user.OrderRecycleViewAdapter;

import java.util.List;

public class FragmentHoSo  extends Fragment {
    private EditText edUser,edEmail,edSdt,edMk,edVaitro;
    private TextView tvTong;
    private Button btnUpdate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoso,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SQLiteHelper db=  new SQLiteHelper(getContext());
        User user = db.getUserByEmail(DataManager.getInstance().getData());
        List<Order> list = db.getOrderByIdUser(user.getId());
        edUser = view.findViewById(R.id.tvTen);
        edEmail = view.findViewById(R.id.tvUser);
        edSdt = view.findViewById(R.id.tvSdt);
        edMk = view.findViewById(R.id.tvPass);
        edVaitro = view.findViewById(R.id.tvRole);
        tvTong = view.findViewById(R.id.tvTong);
        btnUpdate=view.findViewById(R.id.btUpdate);
        edUser.setText(user.getFullname());
        edEmail.setText(user.getEmail());
        edSdt.setText(user.getPhone());
        edMk.setText(user.getPass());
        edVaitro.setText(user.getRole());
        tvTong.setText(list.size()+"");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateUser(new User(user.getId(),edEmail.getText().toString(),
                        edMk.getText().toString(),edSdt.getText().toString(),edUser.getText().toString(),user.getRole()));
                Toast.makeText(getContext(), "Cap nhat thong tin thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}