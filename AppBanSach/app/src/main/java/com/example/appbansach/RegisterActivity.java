package com.example.appbansach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edEmail, edFullname,edPhone, edCpassword, edPassword;

    private Button btnRegister, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String fullname = edFullname.getText().toString().trim();
                String phone = edPhone.getText().toString().trim();
                String pass = edPassword.getText().toString().trim();
                String cpass = edCpassword.getText().toString().trim();
                if(email.isEmpty() || fullname.isEmpty() || phone.isEmpty() || pass.isEmpty() || cpass.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(cpass)) {
                    Toast.makeText(RegisterActivity.this, "Xac nhan lai mat khau", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User(email,pass,phone,fullname,"user");
                    SQLiteHelper db = new SQLiteHelper(RegisterActivity.this);
                    db.register(user);
                    Toast.makeText(RegisterActivity.this, "Dang ky thanh cong!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public void initview(){
        edEmail = findViewById(R.id.edEmail);
        edFullname = findViewById(R.id.edFullname);
        edPhone = findViewById(R.id.edPhone);
        edPassword = findViewById(R.id.edPassword);
        edCpassword = findViewById(R.id.edCpassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);
    }
}