package com.example.intent3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button web,call,sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.web);
        call = findViewById(R.id.phone);
        sms = findViewById(R.id.sms);
        web.setOnClickListener(this);
        call.setOnClickListener(this);
        sms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v== web){
            Intent w = new Intent(Intent.ACTION_VIEW);
            w.setData(Uri.parse("https://www.youtube.com/watch?v=YSEDXvvlnbc"));
            startActivity(w);
        }
        if(v == call){
            Intent c = new Intent(Intent.ACTION_VIEW);
            c.setData(Uri.parse("tel:"+"0382567198"));
            startActivity(c);
        }
        if(v == sms){
            Intent s = new Intent(Intent.ACTION_VIEW);
            s.setData(Uri.parse("sms:"+"0382567198"));
            s.putExtra("sms_body","hello");
            startActivity(s);
        }
    }
}