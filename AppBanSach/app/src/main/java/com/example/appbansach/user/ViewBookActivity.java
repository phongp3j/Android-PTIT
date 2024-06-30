package com.example.appbansach.user;

import android.content.Intent;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.data.DataManager;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Comment;
import com.example.appbansach.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewBookActivity extends AppCompatActivity {
    private ImageView img;

    private TextView tvTen,tvMota,tvDanhmuc,tvGia,tvDaban;
    private Button btnBack,btnMuangay,btnDanhgia;

    private EditText edDanhgia;
    private RecyclerView recyclerView;

    CommentRecycleViewAdapter adapter;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        initview();

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        Picasso.get().load(book.getUrl()).into(img);
        tvTen.setText(book.getTen());
        tvMota.setText(book.getMota());
        tvDanhmuc.setText("Danh mục: "+book.getDanhmuc());
        tvGia.setText("Giá bán: "+book.getGia());
        id = book.getId();
        tvDaban.setText("Đã bán: "+book.getDaban());
        String email =  DataManager.getInstance().getData();
        adapter = new CommentRecycleViewAdapter();
        SQLiteHelper db=  new SQLiteHelper(ViewBookActivity.this);
        List<Comment> listComment = db.getComment(book.getId());
        adapter.setList(listComment);
        LinearLayoutManager manager = new LinearLayoutManager(ViewBookActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnDanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String danhgia = edDanhgia.getText().toString();
                Comment comment = new Comment(book.getId(), email,danhgia);
                SQLiteHelper db = new SQLiteHelper(ViewBookActivity.this);
                db.addComment(comment);
                List<Comment> listComment = db.getComment(book.getId());
                adapter.setList(listComment);
                adapter.notifyDataSetChanged();
            }
        });
        btnMuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewBookActivity.this, BuyActivity.class);
                intent1.putExtra("book",book);
                startActivity(intent1);
            }
        });
    }

    public void initview(){
        img = findViewById(R.id.img);
        tvTen = findViewById(R.id.tvTensach);
        tvDanhmuc = findViewById(R.id.tvDanhmuc);
        tvGia = findViewById(R.id.tvGia);
        tvMota = findViewById(R.id.tvMota);
        tvDaban = findViewById(R.id.tvDaban);
        btnBack = findViewById(R.id.btnBack);
        btnMuangay = findViewById(R.id.btnMuangay);
        btnDanhgia = findViewById(R.id.btnDanhgia);
        edDanhgia = findViewById(R.id.edDanhgia);
        recyclerView = findViewById(R.id.rcDanhgia);
    }

    @Override
    public void onResume(){
        super.onResume();
        SQLiteHelper db = new SQLiteHelper(ViewBookActivity.this);
        List<Comment> list = db.getComment(id);
        adapter.setList(list);
    }
}