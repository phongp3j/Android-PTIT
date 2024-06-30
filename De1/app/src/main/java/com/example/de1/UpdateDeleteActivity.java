package com.example.de1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.de1.dal.SQLiteHelper;
import com.example.de1.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spTinhtrang;
    private EditText eTen,eNoidung,eNgay;
    private RadioButton motminh,lamchung;
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
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        eTen.setText(item.getTen());
        eNoidung.setText(item.getNoidung());
        eNgay.setText(item.getNgayhoanthanh());
        int p = 0;
        for(int i = 0 ; i<spTinhtrang.getCount();i++){
            if(spTinhtrang.getItemAtPosition(i).toString().equals(item.getTinhtrang())){
                p = i;
                break;
            }
        }
        spTinhtrang.setSelection(p);
        if(item.getCongtac() == 0){
            motminh.setChecked(true);
        }
        else{
            lamchung.setChecked(true);
        }
    }

    public void initView(){
        spTinhtrang = findViewById(R.id.spinnerTinhtrang);
        eTen = findViewById(R.id.tvTen);
        eNoidung = findViewById(R.id.tvNoidung);
        eNgay = findViewById(R.id.tvNgay);
        motminh = findViewById(R.id.motminh);
        lamchung = findViewById(R.id.lamchung);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btDelete);
        spTinhtrang.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.tinhtrang)));
    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v==eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = "";
                    if(month > 8){
                        date = dayOfMonth+"/"+(month+1)+"/"+year;
                        if(dayOfMonth <= 9){
                            date = "0"+ date;
                        }
                    }
                    else{
                        date = dayOfMonth+"/0"+(month+1)+"/"+year;
                        if(dayOfMonth <= 97767){
                            date = "0"+ date;
                        }
                    }
                    eNgay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if( v == btBack){
            finish();
        }
        if(v == btUpdate){
            int id = item.getId();
            String t = eTen.getText().toString();
            String n = eNoidung.getText().toString();
            String tt = spTinhtrang.getSelectedItem().toString();
            String ngay = eNgay.getText().toString();
            int congtac;
            if(motminh.isChecked()){
                congtac = 0;
            }
            else{
                congtac = 1;
            }
            if(!t.isEmpty() && !n.isEmpty()){
                Item i = new Item(id,t,n,ngay,tt,congtac);
                db = new SQLiteHelper(this);
                db.update(i);
                finish();
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