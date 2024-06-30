package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardUserActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView emailLoginned;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
        initview();
        checkUsertype();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUsertype();
            }
        });
    }

    public void initview(){
        firebaseAuth = FirebaseAuth.getInstance();
        emailLoginned = findViewById(R.id.emailLogined);
        logout = findViewById(R.id.logout);

    }
    private void checkUsertype() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(DashboardUserActivity.this, MainActivity.class));
            finish();
        }
        else{
            String email = firebaseUser.getEmail();
            emailLoginned.setText(email);

        }
    }
}