package com.example.appbansach.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbansach.R;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Category;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText edCategory;

    private Button btnAdd,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initview();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edCategory.getText().toString().trim();
                if(name.isEmpty()){
                    Toast.makeText(AddCategoryActivity.this, "Vui long dien thong tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteHelper db = new SQLiteHelper(AddCategoryActivity.this);
                    db.addCategory(new Category(name));
                    Toast.makeText(AddCategoryActivity.this, "Them danh muc thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public void initview(){
        edCategory = findViewById(R.id.edCategory);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel =findViewById(R.id.btnCancel);
    }
}