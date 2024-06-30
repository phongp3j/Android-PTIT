package com.example.de1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.de1.fragment.FragmentThongTin;
import com.example.de1.fragment.FragmentDanhSach;
import com.example.de1.fragment.FragmentTimKiem;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDanhSach();
            case 1: return new FragmentThongTin();
            case 2: return new FragmentTimKiem();
            default:return new FragmentDanhSach();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
