package com.example.ktra2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ktra2.dal.SQLiteHelper;
import com.example.ktra2.model.Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spPhongthi;
    private EditText eTen,eGio,eNgay;
    private CheckBox cbThiviet,cbKhongthiviet;
    private Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        cbThiviet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbKhongthiviet.setChecked(false);
            }
        });
        cbKhongthiviet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbThiviet.setChecked(false);
            }
        });
        eNgay.setOnClickListener(this);
        eGio.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
    }
    public void initView(){
        spPhongthi = findViewById(R.id.spinnerPhongthi);
        eTen = findViewById(R.id.tvTen);
        eGio = findViewById(R.id.edGio);
        eNgay = findViewById(R.id.edNgay);
        cbThiviet = findViewById(R.id.cbThiviet);
        cbKhongthiviet = findViewById(R.id.cbKhongthiviet);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        spPhongthi.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.phongthi)));
    }

    @Override
    public void onClick(View v) {
        if(v == btCancel){
            finish();
        }
        if(v == eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);

                    // Định dạng ngày tháng thành chuỗi
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(selectedDate.getTime());

                    // Gán ngày tháng vào EditText
                    eNgay.setText(formattedDate);
                }
            },year,month,day);
            dialog.show();
        }
        if(v == eGio){
            Calendar c = Calendar.getInstance();
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    eGio.setText(i+":"+i1);
                }
            },hh,mm,true);
            timePickerDialog.show();
        }
        if(v == btUpdate){
            String ten = eTen.getText().toString();
            String phongthi = spPhongthi.getSelectedItem().toString();
            String ngay = eNgay.getText().toString();
            String gio = eGio.getText().toString();
            int thiviet ;
            if(cbThiviet.isChecked()){
                thiviet = 1;
            }
            else{
                thiviet = 0;
            }
            if(!ten.isEmpty() && !ngay.isEmpty() && !gio.isEmpty()){
                Item i = new Item(ten,ngay,gio,phongthi,thiviet);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
            else {
                Toast.makeText(this, "Vui long nhap thong tin", Toast.LENGTH_SHORT).show();
            }
        }
}
}
