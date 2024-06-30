package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText ed1,ed2;
    private Spinner sp1;
    private Button bt1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.num1);
        ed2 = findViewById(R.id.num2);
        sp1 = findViewById(R.id.sp1);
        bt1 = findViewById(R.id.add);
        tv1 = findViewById(R.id.res);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {

                   String num1 = ed1.getText().toString();
                   String num2 = ed2.getText().toString();
                       double tmp = Double.parseDouble(num1) + Double.parseDouble(num2);
                       tv1.setText("Ket qua la: " + Double.toString(tmp));
               }
               catch (NumberFormatException e){
                       Toast.makeText(getApplicationContext(),"Vui long nhap so", Toast.LENGTH_LONG).show();
               }
            }
        });
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tmp1 = sp1.getSelectedItem().toString();
                String num1 = ed1.getText().toString();
                String num2 = ed2.getText().toString();
                if(tmp1.equals("Add")){
                    try {
                        double tmp = Double.parseDouble(num1) + Double.parseDouble(num2);
                        tv1.setText("Ket qua la: " + Double.toString(tmp));
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Vui long nhap so", Toast.LENGTH_LONG).show();
                    }
                }
                if(tmp1.equals("Sub")){
                    try {
                        double tmp = Double.parseDouble(num1) - Double.parseDouble(num2);
                        tv1.setText("Ket qua la: " + Double.toString(tmp));
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Vui long nhap so", Toast.LENGTH_LONG).show();
                    }
                }
                if(tmp1.equals("Mul")){
                    try {
                        double tmp = Double.parseDouble(num1) * Double.parseDouble(num2);
                        tv1.setText("Ket qua la: " + Double.toString(tmp));
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Vui long nhap so", Toast.LENGTH_LONG).show();
                    }
                }
                if(tmp1.equals("Div")){
                    try {
                        if(Double.parseDouble(num2) == 0){
                            Toast.makeText(getApplicationContext(),"Khong chia cho 0", Toast.LENGTH_LONG).show();
                        }
                        else{
                            double tmp = Double.parseDouble(num1) / Double.parseDouble(num2);
                            tv1.setText("Ket qua la: " + Double.toString(tmp));
                        }
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Vui long nhap so", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}