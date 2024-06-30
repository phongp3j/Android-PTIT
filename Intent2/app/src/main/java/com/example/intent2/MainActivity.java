package com.example.intent2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intent2.model.Account;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        Intent intent = getIntent();
        if(intent.getSerializableExtra("account")!=null && intent.getSerializableExtra("user")!=null){
            Account log = (Account) intent.getSerializableExtra("account");
            Account res = (Account) intent.getSerializableExtra("user");
            if(log.getUsername().equals(res.getUsername()) && log.getPassword().equals(res.getPassword())){
                txt.setText("Dang nhap thanh cong");
            }
            else{
                txt.setText("Dang nhap that bai");
            }
        }
    }
}