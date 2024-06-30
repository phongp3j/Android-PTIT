package com.example.timedatepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button time,date;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        time.setOnClickListener(this);
        date.setOnClickListener(this);
        textView = findViewById(R.id.textv);
    }

    @Override
    public void onClick(View view) {
        if(view == time){
            Calendar c = Calendar.getInstance();
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    textView.setText(i+":"+i1);
                }
            },hh,mm,true);
            timePickerDialog.show();
        }
        if(view == date ){
            Calendar c = Calendar.getInstance();
            int dd = c.get(Calendar.DAY_OF_MONTH);
            int mm = c.get(Calendar.MONTH);
            int yy = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    textView.setText(i+"/"+i1+1+"/"+i2);
                }
            },yy,mm,dd);
            datePickerDialog.show();
        }
    }
}