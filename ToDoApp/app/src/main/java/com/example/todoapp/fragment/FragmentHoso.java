package com.example.todoapp.fragment;

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

import com.example.todoapp.R;
import com.example.todoapp.data.DataManager;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;

import java.util.List;

public class FragmentHoso extends Fragment {
    private Button btUpdate;
    private EditText eTen,eUser,ePass,eRole;

    private TextView tvTong,tvHoanthanh,tvChualam,tvTrehan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoso, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eTen = view.findViewById(R.id.tvTen);
        eUser = view.findViewById(R.id.tvUser);
        ePass = view.findViewById(R.id.tvPass);
        eRole = view.findViewById(R.id.tvRole);
        btUpdate = view.findViewById(R.id.btUpdate);
        tvTong = view.findViewById(R.id.tvTong);
        tvHoanthanh = view.findViewById(R.id.tvHoanthanh);
        tvChualam = view.findViewById(R.id.tvChualam);
        tvTrehan = view.findViewById(R.id.tvTrehan);
        SQLiteHelper db = new SQLiteHelper(getContext());
        User user = db.getUserByUsername(DataManager.getInstance().getData());
        eTen.setText(user.getFullname());
        eUser.setText(user.getUsername());
        ePass.setText(user.getPassword());
        eRole.setText(user.getType());
        List<Task> listTask = db.getTaskUser(user.getUsername());
        int x1 = 0 , x2 = 0, x3 = 0;
        for(Task i : listTask){
            if(i.getTinhTrang().equals("Đã Hoàn Thành")){
                x1++;
            }
            else if(i.getTinhTrang().equals("Đang Làm")){
                x2++;
            }
            else{
                x3++;
            }
        }
        tvTong.setText(""+listTask.size());
        tvTrehan.setText(""+x3);
        tvHoanthanh.setText(""+x1);
        tvChualam.setText(""+x2);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = user.getId();
                String ten = eTen.getText().toString();
                String username = eUser.getText().toString();
                String pass = ePass.getText().toString();
                String role = eRole.getText().toString();
                if(!ten.isEmpty()){
                    User i = new User(username,ten,pass,role,id);
                    db.updateUser(i);
                    Toast.makeText(getContext(), "Update thanh cong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
