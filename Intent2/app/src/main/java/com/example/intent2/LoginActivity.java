package com.example.intent2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intent2.model.Account;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText tvusername,tvpassword;
    private Button btlogin,btregister;
    private  final static int REQUEST_CODE = 10000;
    private Account user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btlogin.setOnClickListener(this);
        btregister.setOnClickListener(this);
    }

    public void initView(){
        tvusername = findViewById(R.id.txtUsername);
        tvpassword = findViewById(R.id.txtPassword);
        btlogin = findViewById(R.id.btLogin);
        btregister = findViewById(R.id.btRegister);
    }

    @Override
    public void onClick(View v) {
        if(v == btlogin){
            Intent logIntent = new Intent(LoginActivity.this,MainActivity.class);
            Account account = new Account(tvusername.getText().toString(),tvpassword.getText().toString());
            logIntent.putExtra("account",account);
            logIntent.putExtra("user",user);
            startActivity(logIntent);
        }
        if(v == btregister){
            Intent reIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(reIntent,REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode==RESULT_OK){
            if(data == null){
                Toast.makeText(this,"Nguoi dung huy dang ky",Toast.LENGTH_LONG).show();
            }
            else {
                user = (Account) data.getSerializableExtra("data");
                tvusername.setText(user.getUsername());
                tvpassword.setText(user.getPassword());
            }
        }
        else {
            user = null;
        }
    }
}