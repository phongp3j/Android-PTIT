package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp2 = findViewById(R.id.sp2);
        String[] list = {"Phong1","Khanh1"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.item,list);
        sp2.setAdapter(adapter);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tmp = parent.getItemAtPosition(position).toString();
                Log.d("a",tmp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}