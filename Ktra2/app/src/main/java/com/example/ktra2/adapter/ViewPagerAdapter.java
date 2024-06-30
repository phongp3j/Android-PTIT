package com.example.ktra2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ktra2.fragment.FragmentDanhsach;
import com.example.ktra2.fragment.FragmentTimkiem;
import com.example.ktra2.fragment.FragmentYeuthich;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDanhsach();
            case 1: return new FragmentYeuthich();
            case 2: return new FragmentTimkiem();
            default:return new FragmentDanhsach();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}