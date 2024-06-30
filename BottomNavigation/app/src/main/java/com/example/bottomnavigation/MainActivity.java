package com.example.bottomnavigation;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigation.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),4);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.mHome).setCheckable(true);
                            break;
                    case 1: navigationView.getMenu().findItem(R.id.mNoti).setCheckable(true);
                            break;
                    case 2: navigationView.getMenu().findItem(R.id.mSearch).setCheckable(true);
                            break;
                    case 3: navigationView.getMenu().findItem(R.id.mCafe).setCheckable(true);
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
                int a = menuItem.getItemId();
//                switch (a){
//                    case R.id.mHome:
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case R.id.mSearch:
//                        viewPager.setCurrentItem(1);
//                        break;
//                    case 2131230964:
//                        viewPager.setCurrentItem(2);
//                        break;
//                    case 2131230961:
//                        viewPager.setCurrentItem(3);
//                        break;
//                }
                if(a == R.id.mHome){
                    viewPager.setCurrentItem(0);
                }
                if(a == R.id.mSearch){
                    viewPager.setCurrentItem(1);
                }
                if(a == R.id.mNoti){
                    viewPager.setCurrentItem(2);
                }
                if(a == R.id.mCafe){
                    viewPager.setCurrentItem(3);
                }
                return true;
            }


        });
    }
}