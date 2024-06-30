package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeconActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt;
    //khi muon gui 1 object thi trong class object phai implements serializables va khi nhan thi dung getSerializable(ep kieu)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon);
        Intent t = getIntent();
        tv = findViewById(R.id.tvName);
        bt = findViewById(R.id.bt);
        String name = t.getStringExtra("name");
        tv.setText(name);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}