package com.example.tablayoutcrud.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tablayoutcrud.fragment.FragmentAdd;
import com.example.tablayoutcrud.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private int numPage = 2;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentAdd();
            case 1: return new FragmentSearch();
            default: return new FragmentAdd();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
