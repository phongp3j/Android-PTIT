package com.example.todoapp.user;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTen,egio,engay;
    private Button btUpdate,btBack,btRemove;
    Task item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        engay.setOnClickListener(this);
        egio.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Task) intent.getSerializableExtra("item");
        eTen.setText(item.getTenCongViec());
        egio.setText(item.getGioBatDau());
        engay.setText(item.getNgay());
        int p = 0;
        for(int i = 0 ; i<sp.getCount();i++){
            if(sp.getItemAtPosition(i).toString().equals(item.getTinhTrang())){
                p = i;
                break;
            }
        }
        sp.setSelection(p);
    }

    public void initView(){
        sp = findViewById(R.id.spinnerTinhtrang);
        eTen = findViewById(R.id.tvTen);
        egio = findViewById(R.id.tvGio);
        engay = findViewById(R.id.tvNgay);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btDelete);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.tinhtrang)));
    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v==engay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                    engay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if( v == egio){
            Calendar c = Calendar.getInstance();
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    String formattedTime = String.format("%02d:%02d", i, i1);
                    egio.setText(formattedTime);
                }
            },hh,mm,true);
            timePickerDialog.show();
        }
        if( v == btBack){
            finish();
        }
        if(v == btUpdate){
            int id = item.getId();
            String ten = eTen.getText().toString();
            String gio = egio.getText().toString();
            String tinhtrang = sp.getSelectedItem().toString();
            String ngay = engay.getText().toString();
            String nguoilam = item.getNguoiLam();
            if(!ten.isEmpty()){
                Task i = new Task(id,ten,gio,ngay,tinhtrang,nguoilam);
                db = new SQLiteHelper(this);
                db.update(i);
                finish();
            }
        }
        if(v == btRemove){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setMessage("Ban co chac muon xoa "+item.getTenCongViec()+" khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                    db.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}