package com.example.appbansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbansach.admin.AdminMainActivity;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.user.UserMainActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername, edPassword;

    private Button btnLogin, btnCancel;

    private TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
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
                    if(db.login(username,password) == 2){
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    } else if (db.login(username,password) == 1) {
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
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
            }
        });
    }

    public void initView(){
        forgot = findViewById(R.id.forgot);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
    }
}