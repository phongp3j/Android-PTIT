package com.example.de1;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spTinhtrang;
    private EditText eTen,eNoidung,eNgay;
    private RadioButton motminh,lamchung;
    private Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eNgay.setOnClickListener(this);
    }
    public void initView(){
        spTinhtrang = findViewById(R.id.spinnerTinhtrang);
        eTen = findViewById(R.id.tvTen);
        eNoidung = findViewById(R.id.tvNoidung);
        eNgay = findViewById(R.id.tvNgay);
        motminh = findViewById(R.id.motminh);
        lamchung = findViewById(R.id.lamchung);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        spTinhtrang.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.tinhtrang)));
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
        if(v == btCancel){
            finish();
        }
        if(v == btUpdate){
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
                Item i = new Item(t,n,ngay,tt,congtac);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
    }
}