package com.example.appbansach.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Order;
import com.squareup.picasso.Picasso;

public class OrderUpdateActivity extends AppCompatActivity {
    private ImageView img;
    private TextView tensach,danhmuc,gia,daban,soluong,diachi,sdt,nguoinhan,tongtien;
    private EditText trangthai;

    private Button btnBack,btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update);
        initview();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("order");
        SQLiteHelper db = new SQLiteHelper(OrderUpdateActivity.this);
        Book book = db.getBookById(order.getIdSach());
        Picasso.get().load(book.getUrl()).into(img);
        tensach.setText(book.getTen());
        danhmuc.setText(book.getDanhmuc());
        gia.setText("Giá: "+book.getGia()+"");
        daban.setText("Đã bán: "+book.getDaban()+"");
        soluong.setText(order.getSoluong()+"");
        diachi.setText(order.getDiachi());
        sdt.setText(order.getSdt());
        nguoinhan.setText(order.getNguoinhan());
        trangthai.setText(order.getTrangthai());
        tongtien.setText(book.getGia()*order.getSoluong()+" VND");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setTrangthai(trangthai.getText().toString());
                db.updateOrder(order);
                Toast.makeText(OrderUpdateActivity.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public void initview(){
        img = findViewById(R.id.img);
        tensach = findViewById(R.id.tvTensach);
        danhmuc = findViewById(R.id.tvDanhmuc);
        gia = findViewById(R.id.tvGia);
        daban = findViewById(R.id.tvDaban);
        soluong = findViewById(R.id.edNumber);
        diachi = findViewById(R.id.edDiachi);
        sdt = findViewById(R.id.edSdt);
        nguoinhan = findViewById(R.id.edNguoinhan);
        trangthai = findViewById(R.id.edTrangthai);
        tongtien = findViewById(R.id.tvTongtien);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}