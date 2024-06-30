package com.example.todoapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.adapter.ViewPagerAdapter;
import com.example.todoapp.data.DataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserMainActivity extends AppCompatActivity {
    private String userLoginned;
    private TextView tvUsername;
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Intent intent = getIntent();
        userLoginned = intent.getStringExtra("username");
        DataManager.getInstance().setData(userLoginned);
        initview();
        tvUsername.setText("Xin Chào "+userLoginned);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginned = "";
                startActivity(new Intent(UserMainActivity.this, MainActivity.class));
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainActivity.this, AddActivity.class);
                intent.putExtra("username",userLoginned);
                startActivity(intent);
            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:navigationView.getMenu().findItem(R.id.mHomnay).setChecked(true);
                        break;
                    case 1:navigationView.getMenu().findItem(R.id.mTatca).setChecked(true);
                        break;
                    case 2:navigationView.getMenu().findItem(R.id.mTimkiem).setChecked(true);
                        break;
                    case 3:navigationView.getMenu().findItem(R.id.mHoso).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.mHomnay){
                    viewPager.setCurrentItem(0);
                }
                if(menuItem.getItemId() == R.id.mTatca){
                    viewPager.setCurrentItem(1);
                }
                if(menuItem.getItemId() == R.id.mTimkiem){
                    viewPager.setCurrentItem(2);
                }
                if(menuItem.getItemId() == R.id.mHoso){
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }

    public void initview(){
        tvUsername = findViewById(R.id.tvUsername);
        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.viewPager);
        fab = findViewById(R.id.fab);
        imageView = findViewById(R.id.logout);
    }
}