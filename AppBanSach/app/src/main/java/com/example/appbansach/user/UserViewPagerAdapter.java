package com.example.appbansach.user;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appbansach.fragment.FragmentDanhMuc;
import com.example.appbansach.fragment.FragmentDonHang;
import com.example.appbansach.fragment.FragmentGioHang;
import com.example.appbansach.fragment.FragmentHoSo;
import com.example.appbansach.fragment.FragmentHome;
import com.example.appbansach.fragment.FragmentTimKiem;
import com.example.appbansach.fragment.FragmentUser;


public class UserViewPagerAdapter extends FragmentStatePagerAdapter {
    public UserViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentTimKiem();
            case 2: return new FragmentGioHang();
            case 3: return new FragmentHoSo();
            default:return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
