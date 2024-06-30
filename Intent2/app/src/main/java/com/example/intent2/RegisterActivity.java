package com.example.intent2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intent2.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText tvUsername, tvPassword;
    private Button btRegister,btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btCancel.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }
    public void initView(){
        tvUsername = findViewById(R.id.txtUsername);
        tvPassword = findViewById(R.id.txtPassword);
        btRegister = findViewById(R.id.btRegister);
        btCancel = findViewById(R.id.btCancel);
    }

    @Override
    public void onClick(View v) {
        if(v == btCancel){
            setResult(RESULT_CANCELED,null);
            finish();
        }
        if(v == btRegister){
            Account account = new Account(tvUsername.getText().toString(),tvPassword.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("data",account);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}