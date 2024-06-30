package com.example.appbansach.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.data.DataManager;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Order;
import com.example.appbansach.model.User;
import com.squareup.picasso.Picasso;

public class BuyActivity extends AppCompatActivity {
    private ImageView img;
    private TextView tvTen,tvMota,tvDanhmuc,tvGia,tvDaban,tvTong;

    private EditText edSoluong,edDiachi,edSdt,edTen;

    private Button btnBack,btnMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initView();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        Picasso.get().load(book.getUrl()).into(img);
        tvTen.setText(book.getTen());
        tvMota.setText(book.getMota());
        tvDanhmuc.setText("Danh mục: "+book.getDanhmuc());
        tvGia.setText("Giá bán: "+book.getGia());
        tvDaban.setText("Đã bán: "+book.getDaban());
        String email =  DataManager.getInstance().getData();
        SQLiteHelper db = new SQLiteHelper(BuyActivity.this);
        User user = db.getUserByEmail(email);
        edTen.setText(user.getFullname());
        edSdt.setText(user.getPhone());
        edSoluong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!edSoluong.getText().toString().isEmpty()){
                    tvTong.setText("Total: "+Integer.parseInt(edSoluong.getText().toString())*book.getGia() + " VND");
                }
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order(book.getId(), user.getId(),edDiachi.getText().toString(),edSdt.getText().toString(),"Đang Chuẩn Bị",edTen.getText().toString(),Integer.parseInt(edSoluong.getText().toString())*Integer.parseInt(book.getGia()+""),Integer.parseInt(edSoluong.getText().toString()));
                SQLiteHelper db = new SQLiteHelper(BuyActivity.this);
                db.addOrder(order);
                Book tmp = new Book(book.getId(),book.getDanhmuc(),book.getTen(),book.getMota(),book.getUrl(), book.getDaban()+Integer.parseInt(edSoluong.getText().toString()), book.getGia() );
                db.updateBook(tmp);
                finish();
            }
        });
    }

    public void initView(){
        img = findViewById(R.id.img);
        tvTen = findViewById(R.id.tvTensach);
        tvMota = findViewById(R.id.tvMota);
        tvDanhmuc = findViewById(R.id.tvDanhmuc);
        tvGia = findViewById(R.id.tvGia);
        tvDaban = findViewById(R.id.tvDaban);
        edSoluong = findViewById(R.id.edNumber);
        edDiachi = findViewById(R.id.edDiachi);
        edSdt = findViewById(R.id.edSdt);
        edTen = findViewById(R.id.edNguoinhan);
        tvTong = findViewById(R.id.tvTongtien);
        btnBack = findViewById(R.id.btnBack);
        btnMua = findViewById(R.id.btnMuangay);
    }
}