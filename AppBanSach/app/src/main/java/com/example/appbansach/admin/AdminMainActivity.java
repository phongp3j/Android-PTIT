package com.example.appbansach.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appbansach.MainActivity;
import com.example.appbansach.R;
import com.example.appbansach.data.DataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminMainActivity extends AppCompatActivity {
    private String userLoginned;
    private TextView tvUsername;
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private ImageView imageView;
    private Button addCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Intent intent = getIntent();
        userLoginned = intent.getStringExtra("username");
        DataManager.getInstance().setData(userLoginned);
        initview();
        tvUsername.setText("Xin Chào :" + userLoginned);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginned = "";
                startActivity(new Intent(AdminMainActivity.this, MainActivity.class));
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });
        AdminViewPagerAdapter adapter = new AdminViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:navigationView.getMenu().findItem(R.id.mDanhmuc).setChecked(true);
                        break;
                    case 1:navigationView.getMenu().findItem(R.id.mUser).setChecked(true);
                        break;
                    case 2:navigationView.getMenu().findItem(R.id.mDonhang).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.mDanhmuc){
                    viewPager.setCurrentItem(0);
                }
                if(menuItem.getItemId() == R.id.mUser){
                    viewPager.setCurrentItem(1);
                }
                if(menuItem.getItemId() == R.id.mDonhang){
                    viewPager.setCurrentItem(2);
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
        addCategory = findViewById(R.id.addcategory);
    }
}