package com.example.fregment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fregment.model.Fragment_Fa;
import com.example.fregment.model.Fragment_Fb;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.fa);
        bt2 = findViewById(R.id.fb);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment f;
            switch (v.getId()){
                case R.id.fa:
                    f = new Fragment_Fa();
                    transaction.add(R.id.frame,f);
                    break;
                case R.id.fb:
                    f = new Fragment_Fb();
                    transaction.add(R.id.frame,f);
                    break;
            }
            transaction.commit();
    }
}