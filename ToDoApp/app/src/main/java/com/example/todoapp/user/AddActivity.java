package com.example.todoapp.user;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.R;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.model.Task;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp;
    private EditText eTen,eGio,eNgay;
    private Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eGio.setOnClickListener(this);
        eNgay.setOnClickListener(this);
    }
    public void initView(){
        sp = findViewById(R.id.spinnerTinhtrang);
        eTen = findViewById(R.id.tvTen);
        eGio = findViewById(R.id.tvGio);
        eNgay = findViewById(R.id.tvNgay);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.tinhtrang)));
    }

    @Override
    public void onClick(View v) {
        if(v == eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date;
                    month += 1; // Vì Java sử dụng chỉ số bắt đầu từ 0 cho tháng

                    // Định dạng ngày tháng nếu tháng lớn hơn 9
                    if (month > 9) {
                        if (dayOfMonth < 10) {
                            date = "0" + dayOfMonth + "/" + month + "/" + year;
                        } else {
                            date = dayOfMonth + "/" + month + "/" + year;
                        }
                    }
                    // Định dạng ngày tháng nếu tháng nhỏ hơn hoặc bằng 9
                    else {
                        if (dayOfMonth < 10) {
                            date = "0" + dayOfMonth + "/0" + month + "/" + year;
                        } else {
                            date = dayOfMonth + "/0" + month + "/" + year;
                        }
                    }

                    eNgay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if( v == eGio){
            Calendar c = Calendar.getInstance();
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    String formattedTime = String.format("%02d:%02d", i, i1);
                    eGio.setText(formattedTime);
                }
            },hh,mm,true);
            timePickerDialog.show();
        }
        if(v == btCancel){
            finish();
        }
        if(v == btUpdate){
            String t = eTen.getText().toString();
            String g = eGio.getText().toString();
            String tt = sp.getSelectedItem().toString();
            String n = eNgay.getText().toString();
            if(!t.isEmpty() ){
                Intent intent = getIntent();
                String nguoilam = intent.getStringExtra("username");
                Task i = new Task(t,g,n,tt,nguoilam);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
    }
}