package com.example.appbansach.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appbansach.fragment.FragmentDanhMuc;
import com.example.appbansach.fragment.FragmentDonHang;
import com.example.appbansach.fragment.FragmentUser;


public class AdminViewPagerAdapter extends FragmentStatePagerAdapter {
    public AdminViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDanhMuc();
            case 1: return new FragmentUser();
            case 2: return new FragmentDonHang();
            default:return new FragmentDanhMuc();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
