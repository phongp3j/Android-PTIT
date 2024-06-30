package com.example.todoapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.todoapp.fragment.FragmentDanhSach;
import com.example.todoapp.fragment.FragmentHomNay;
import com.example.todoapp.fragment.FragmentHoso;
import com.example.todoapp.fragment.FragmentTimKiem;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHomNay();
            case 1: return new FragmentDanhSach();
            case 2: return new FragmentTimKiem();
            case 3: return new FragmentHoso();
            default:return new FragmentHomNay();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
