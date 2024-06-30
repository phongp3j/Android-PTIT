package com.example.todoapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.adapter.UserRecycleViewAdapter;
import com.example.todoapp.db.SQLiteHelper;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.user.UpdateDeleteActivity;
import com.example.todoapp.user.UserMainActivity;

import java.util.List;

public class AdminMainActivity extends AppCompatActivity implements UserRecycleViewAdapter.ItemUserListener {
    private RecyclerView recyclerView;
    private String userLoginned;
    private ImageView imageView;
    private TextView tvUsername;
    private SQLiteHelper db;
    private UserRecycleViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        initView();
        db = new SQLiteHelper(AdminMainActivity.this);
        List<User> list = db.getAllUser();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        Intent intent = getIntent();
        tvUsername.setText("Xin chào admin: "+intent.getStringExtra("username"));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginned = "";
                startActivity(new Intent(AdminMainActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public void initView(){
        recyclerView = findViewById(R.id.recycleView);
        imageView = findViewById(R.id.logout);
        tvUsername = findViewById(R.id.tvUsername);
        adapter = new UserRecycleViewAdapter();
    }

    @Override
    public void onItemClick(View view, int position) {
        User item = adapter.getUser(position);
        Intent intent = new Intent(this, UpdateDeleteUserActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        List<User> list = db.getAllUser();
        adapter.setList(list);
    }
}