package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.admin.AdminMainActivity;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.user.UserMainActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername, edPassword;

    private Button loginBtn,backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteHelper db = new SQLiteHelper(LoginActivity.this);
                    if(db.login(username,password) == -1){
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    } else if (db.login(username,password) == -2) {
                        Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void initview(){
        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        backBtn = findViewById(R.id.back);
    }
}