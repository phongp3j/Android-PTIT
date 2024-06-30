package com.example.appbansach.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Order;
import com.example.appbansach.model.User;

import java.util.List;

public class UpdateDeleteUserActivity extends AppCompatActivity {
    private Button btUpdate,btBack,btRemove;
    private EditText eTen,eUser,ePass,eRole,eSdt;

    private TextView tvTong;
    User item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_user);
        initView();
        Intent intent = getIntent();
        item = (User) intent.getSerializableExtra("item");
        eTen.setText(item.getFullname());
        eUser.setText(item.getEmail());
        eRole.setText(item.getRole());
        ePass.setText(item.getPass());
        eSdt.setText(item.getPhone());
        SQLiteHelper db = new SQLiteHelper(this);
        List<Order> tmp = db.getOrderByIdUser(item.getId());
        tvTong.setText(""+tmp.size());
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.getId();
                String ten = eTen.getText().toString();
                String email = eUser.getText().toString();
                String sdt = eSdt.getText().toString();
                String pass = ePass.getText().toString();
                String role = eRole.getText().toString();
                if(!ten.isEmpty()){
                    User i = new User(id,email,pass,sdt,ten,role);
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
        eSdt = findViewById(R.id.tvSdt);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btDelete);
        tvTong = findViewById(R.id.tvTong);
    }
}