package com.example.appbansach.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateDeleteBookActivity extends AppCompatActivity {
    private EditText edTen,edMota,edGia,edUrl,edDaban;
    private Spinner spCate;
    private ImageView img;
    private Button btUpdate,btDelete,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_book);
        initView();
        Intent intent = getIntent();
        Book book = (Book)intent.getSerializableExtra("book");
        edTen.setText(book.getTen());
        edMota.setText(book.getMota());
        edGia.setText(book.getGia()+"");
        edDaban.setText(book.getDaban()+"");
        edUrl.setText(book.getUrl());
        Picasso.get().load(edUrl.getText().toString()).into(img);
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
        SQLiteHelper db = new SQLiteHelper(UpdateDeleteBookActivity.this);
        List<Category> list = db.getAllCategory();
        List<String> listCategory = new ArrayList<>();
        for(Category i : list){
            listCategory.add(i.getName());
        }
        spCate.setAdapter(new ArrayAdapter<String>(UpdateDeleteBookActivity.this,R.layout.item_spinner,listCategory));
        int p = 0;
        for(int i = 0 ; i < listCategory.size(); i++){
            if(listCategory.get(i).equals(book.getDanhmuc())){
                p = i;
                break;
            }
        }
        spCate.setSelection(p);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao xoa!");
                builder.setMessage("Ban co chac muon xoa "+book.getTen()+" khong?");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                        db.deleteBook(book.getId());
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
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edTen.getText().toString();
                String mota = edMota.getText().toString();
                String url = edUrl.getText().toString();
                int daban = Integer.parseInt(edDaban.getText().toString());
                int gia = Integer.parseInt(edGia.getText().toString());
                String danhmuc = spCate.getSelectedItem().toString();
                db.updateBook(new Book(book.getId(),danhmuc,ten,mota,url,daban,gia));
                Toast.makeText(UpdateDeleteBookActivity.this, "Update sach thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public void initView(){
        edTen = findViewById(R.id.tvTen);
        edMota = findViewById(R.id.tvMota);
        edGia = findViewById(R.id.tvGia);
        edUrl = findViewById(R.id.tvUrl);
        edDaban = findViewById(R.id.tvDaban);
        spCate = findViewById(R.id.spCate);
        img = findViewById(R.id.img);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
        btCancel = findViewById(R.id.btBack);
    }
}