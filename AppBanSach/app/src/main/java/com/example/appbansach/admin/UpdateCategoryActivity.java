package com.example.appbansach.admin;

import android.content.Intent;
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

public class UpdateCategoryActivity extends AppCompatActivity {
    private EditText edCategory;
    private Button btnUpdate, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);
        initview();
        Intent intent = getIntent();
        Category category = (Category)intent.getSerializableExtra("category");
        edCategory.setText(category.getName());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edCategory.getText().toString();
                SQLiteHelper db= new SQLiteHelper(UpdateCategoryActivity.this);
                db.updateCategory(new Category(category.getId(),name));
                db.updateBookByCategory(category.getName(),name);
                Toast.makeText(UpdateCategoryActivity.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void initview(){
        edCategory = findViewById(R.id.edCategory);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
    }
}