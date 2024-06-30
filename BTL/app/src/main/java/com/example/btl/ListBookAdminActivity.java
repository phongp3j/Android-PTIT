package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapter.BookListAdminAdapter;
import com.example.btl.model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListBookAdminActivity extends AppCompatActivity {
    private TextView back,category;

    private EditText search;

    private RecyclerView recyclerView;

    private List<Book> list;

    private BookListAdminAdapter adapter;
    private String categoryID;
    private String categoryTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book_admin);
        initview();
        Intent intent = getIntent();
        categoryID = intent.getStringExtra("categoryID");
        System.out.println(categoryID);
        categoryTitle = intent.getStringExtra("categoryTitle");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadPdfList();
    }
    public void initview(){
        back= findViewById(R.id.tvBack);
        category = findViewById(R.id.categorytv);
        search = findViewById(R.id.edSearch);
        recyclerView = findViewById(R.id.bookRv);
    }
    public void loadPdfList(){
        list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild("danhmucID").equalTo(categoryID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            Book b = ds.getValue(Book.class);
                            list.add(b);
                        }
                        adapter = new BookListAdminAdapter(ListBookAdminActivity.this,list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}