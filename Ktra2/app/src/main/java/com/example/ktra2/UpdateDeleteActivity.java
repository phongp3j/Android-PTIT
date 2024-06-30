package com.example.ktra2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spPhongthi;
    private EditText eTen,eGio,eNgay;
    private CheckBox cbThiviet,cbKhongthiviet;
    private Button btUpdate,btBack,btRemove;
    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        eNgay.setOnClickListener(this);
        eGio.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        eTen.setText(item.getTen());
        eNgay.setText(item.getNgay());
        eGio.setText(item.getGio());
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
        int p = 0;
        for(int i = 0 ; i<spPhongthi.getCount();i++){
            if(spPhongthi.getItemAtPosition(i).toString().equals(item.getPhongthi())){
                p = i;
                break;
            }
        }
        spPhongthi.setSelection(p);
        if(item.getThiviet() == 1){
            cbThiviet.setChecked(true);
        }
        else{
            cbKhongthiviet.setChecked(true);
        }
    }

    public void initView(){
        spPhongthi = findViewById(R.id.spinnerPhongthi);
        eTen = findViewById(R.id.tvTen);
        eGio = findViewById(R.id.edGio);
        eNgay = findViewById(R.id.edNgay);
        cbThiviet = findViewById(R.id.cbThiviet);
        cbKhongthiviet = findViewById(R.id.cbKhongthiviet);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btDelete);
        spPhongthi.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.phongthi)));

    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if( v == btBack){
            finish();
        }
        if(v==eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(v== eGio){
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
            int id = item.getId();
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
                Item i = new Item(id,ten,ngay,gio,phongthi,thiviet);
                db = new SQLiteHelper(this);
                db.update(i);
                finish();
            }
            else {
                Toast.makeText(this, "Vui long nhap thong tin", Toast.LENGTH_SHORT).show();
            }
        }
        if(v == btRemove){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setMessage("Ban co chac muon xoa "+item.getTen()+" khong?");
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