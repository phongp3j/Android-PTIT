package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapter.CategoryRecycleViewAdapter;
import com.example.btl.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdminActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView emailLoginned;
    private ImageView logout;
    private FloatingActionButton fab;
    private Button addCategory;

    private RecyclerView recyclerView;

    private List<Category> list;
    private CategoryRecycleViewAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        initview();
        checkUsertype();
        loadCategories();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUsertype();
            }
        });
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardAdminActivity.this, AddCategoryActivity.class));
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardAdminActivity.this, AddBookActivity.class));
            }
        });
    }

    public void initview(){
        firebaseAuth = FirebaseAuth.getInstance();
        emailLoginned = findViewById(R.id.emailLogined);
        logout = findViewById(R.id.logout);
        fab = findViewById(R.id.fab);
        addCategory = findViewById(R.id.btAddCate);
        recyclerView = findViewById(R.id.recyclerViewCate);
    }
    private void checkUsertype() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(DashboardAdminActivity.this, MainActivity.class));
            finish();
        }
        else{
            String email = firebaseUser.getEmail();
            emailLoginned.setText(email);

        }
    }
    public void loadCategories(){
        list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Category c = ds.getValue(Category.class);
                    list.add(c);
                }
                categoryAdapter = new CategoryRecycleViewAdapter(DashboardAdminActivity.this,list);
                recyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}