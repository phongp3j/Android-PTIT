package com.example.btl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText edName,edEmail,edPass,edConfirmpass;

    private Button register;
    private TextView tvLogin;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }
    public void initView(){
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);
        edConfirmpass = findViewById(R.id.edCPass);
        register = findViewById(R.id.register);
        tvLogin = findViewById(R.id.tvLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Vui Long Doi...");
        progressDialog.setCanceledOnTouchOutside(false);
    }
    private String name = "", email = "", pass = "";
    public void validateData(){
        name = edName.getText().toString().trim();
        email = edEmail.getText().toString().trim();
        pass = edPass.getText().toString().trim();
        String cpass = edConfirmpass.getText().toString().trim();
        if(name.isEmpty()){
            Toast.makeText(this, "Hay Nhap Ten", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email Khong Dung", Toast.LENGTH_SHORT).show();
        }
        else if(pass.isEmpty()){
            Toast.makeText(this, "Hay Nhap Mat Khau", Toast.LENGTH_SHORT).show();
        }
        else if(cpass.isEmpty()){
            Toast.makeText(this, "Hay Xac Nhan Lai Mat Khau", Toast.LENGTH_SHORT).show();
        }
        else if(!cpass.equals(pass)){
            Toast.makeText(this, "Mat Khau Xac Nhan Khong Dung", Toast.LENGTH_SHORT).show();
        }
        else {
            createUserAccount();
        }
    }
    public void createUserAccount(){
        progressDialog.setMessage("Dang Tao Tai Khoan...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                updateUserInfor();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {

            }
        });
    }
    public void updateUserInfor(){
        progressDialog.setMessage("Dang Luu Thong Tin Tai Khoan...");

        long timestamp = System.currentTimeMillis();

        String uid = firebaseAuth.getUid();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("email",email);
        hashMap.put("name",name);
        hashMap.put("profileImg","");
        hashMap.put("userType","user");
        hashMap.put("timestamp",timestamp);

        //set data vao db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Dang Ky Tai Khoan Thanh Cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,DashboardUserActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}