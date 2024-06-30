package com.example.todoapp.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoapp.R;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;

import java.util.List;

public class UpdateDeleteUserActivity extends AppCompatActivity {
    private Button btUpdate,btBack,btRemove;
    private EditText eTen,eUser,ePass,eRole;

    private TextView tvTong,tvHoanthanh,tvChualam,tvTrehan;
    User item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_user);
        initView();
        Intent intent = getIntent();
        item = (User) intent.getSerializableExtra("item");
        eTen.setText(item.getFullname());
        eUser.setText(item.getUsername());
        eRole.setText(item.getType());
        ePass.setText(item.getPassword());
        SQLiteHelper db = new SQLiteHelper(this);
        List<Task> listTask = db.getTaskUser(item.getUsername());
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
                int id = item.getId();
                String ten = eTen.getText().toString();
                String username = eUser.getText().toString();
                String pass = ePass.getText().toString();
                String role = eRole.getText().toString();
                if(!ten.isEmpty()){
                    User i = new User(username,ten,pass,role,id);
                    db.updateUser(i);
                    finish();
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao xoa!");
                builder.setMessage("Ban co chac muon xoa "+item.getFullname()+" khong?");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                        db.deleteUser(id);
                        finish();
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void initView(){
        eTen = findViewById(R.id.tvTen);
        eUser = findViewById(R.id.tvUser);
        ePass = findViewById(R.id.tvPass);
        eRole = findViewById(R.id.tvRole);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btDelete);
        tvTong = findViewById(R.id.tvTong);
        tvChualam = findViewById(R.id.tvChualam);
        tvHoanthanh = findViewById(R.id.tvHoanthanh);
        tvTrehan = findViewById(R.id.tvTrehan);
    }
}