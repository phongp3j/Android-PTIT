package com.example.appbansach.admin;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    private EditText edBook,edDes,edUrl,edPrice;
    private Spinner spCate;
    private ImageView img;
    private Button btnAdd,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        initview();
        SQLiteHelper db = new SQLiteHelper(this);
        List<Category> listCate = db.getAllCategory();
        List<String> listCateString = new ArrayList<>();
        for(Category i : listCate){
            listCateString.add(i.getName());
        }
        spCate.setAdapter(new ArrayAdapter<String>(AddBookActivity.this,R.layout.item_spinner,listCateString));
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edBook.getText().toString();
                String mota = edDes.getText().toString();
                String url = edUrl.getText().toString();
                Integer gia = Integer.parseInt(edPrice.getText().toString());
                String danhmuc = spCate.getSelectedItem().toString();
                Book book = new Book(danhmuc,ten,mota,url,0,gia);
                SQLiteHelper db = new SQLiteHelper(AddBookActivity.this);
                db.addBook(book);
                Toast.makeText(AddBookActivity.this, "Them sach thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        edUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edUrl.getText().toString().isEmpty()){
                    Picasso.get().load(edUrl.getText().toString()).into(img);
                }
            }
        });
    }

    public void initview(){
        edBook = findViewById(R.id.edBook);
        edDes = findViewById(R.id.edMota);
        edUrl = findViewById(R.id.edUrl);
        edPrice = findViewById(R.id.edPrice);
        spCate = findViewById(R.id.spCate);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        img = findViewById(R.id.img);
    }
}