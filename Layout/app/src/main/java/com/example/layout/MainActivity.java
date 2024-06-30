package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner sp1,sp2;
    private CheckBox cb1,cb2,cb3;
    private RadioButton rg1,rg2;
    private RatingBar rt1;
    private TextView tv1;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex3);
        initView();
        List<String> list = new ArrayList<>();
        list.add("Phong 4");
        list.add("Phong 5");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item_spinner,list);
        sp2.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = "";
                if(cb1.isChecked()){
                    res = res + cb1.getText().toString()+"\n";
                }
                if(cb2.isChecked()){
                    res = res + cb2.getText().toString()+"\n";
                }
                if(cb3.isChecked()){
                    res = res + cb3.getText().toString()+"\n";
                }
                if (rg1.isChecked()){
                    res = res + rg1.getText().toString()+"\n";
                }
                if (rg2.isChecked()){
                    res = res + rg2.getText().toString()+"\n";
                }
                res = res + rt1.getNumStars()+"\n";
                res = res + sp1.getSelectedItem().toString()+"\n";
                res = res + sp2.getSelectedItem().toString()+"\n";
                tv1.setText(res);
            }
        });
    }

    private void initView() {
        tv1 = findViewById(R.id.tv1);
        sp2 = findViewById(R.id.sp2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        sp1 = findViewById(R.id.sp1);
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rt1 = findViewById(R.id.rt1);
        submit = findViewById(R.id.submit);
    }
}