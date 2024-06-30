package com.example.appbansach.admin;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Category;

import java.util.List;

public class ListBookActivity extends AppCompatActivity implements BookRecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    private Button btnUpdateCategory, btnDeleteCategory, btnBack;

    BookRecycleViewAdapter adapter;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        initView();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        Category category = (Category)intent.getSerializableExtra("category");
        String nameCate = category.getName();
        int id = category.getId();
        btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao xoa!");
                builder.setMessage("Ban co chac muon xoa "+nameCate+" khong?");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                        db.deleteCategory(id,nameCate);
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
        });
        btnUpdateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ListBookActivity.this,UpdateCategoryActivity.class);
                intent1.putExtra("category",category);
                startActivity(intent1);
            }
        });
        db = new SQLiteHelper(ListBookActivity.this);
        List<Book> list = db.getBookByCate(nameCate);
        adapter = new BookRecycleViewAdapter();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(ListBookActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }
    public void initView(){
        recyclerView = findViewById(R.id.recyclView);
        btnBack = findViewById(R.id.btnBack);
        btnUpdateCategory = findViewById(R.id.updateCate);
        btnDeleteCategory = findViewById(R.id.deleteCategory);
    }

    @Override
    public void onItemClick(View view, int position) {
        Book item = adapter.getItem(position);
        Intent intent = new Intent(ListBookActivity.this, UpdateDeleteBookActivity.class);
        intent.putExtra("book",item);
        startActivity(intent);
    }
}