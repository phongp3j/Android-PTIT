package com.example.appbansach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appbansach.R;
import com.example.appbansach.admin.BookRecycleViewAdapter;
import com.example.appbansach.admin.UpdateDeleteUserActivity;
import com.example.appbansach.admin.UserRecycleViewAdapter;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Book;
import com.example.appbansach.model.User;
import com.example.appbansach.user.SlidePagerAdapter;
import com.example.appbansach.user.ViewBookActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends Fragment implements BookRecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;

    private ViewPager slide;
    private SQLiteHelper db;

    BookRecycleViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclView);
        slide = view.findViewById(R.id.viewPagerSlide);
        adapter = new BookRecycleViewAdapter();
        db=  new SQLiteHelper(getContext());
        List<Book> list = db.getTop5Book();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3};

        SlidePagerAdapter adapterslide = new SlidePagerAdapter(getContext() , images);
        slide.setAdapter(adapterslide);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                int nextPage = slide.getCurrentItem() + 1;
                if (nextPage >= adapterslide.getCount()) {
                    nextPage = 0;
                }
                slide.setCurrentItem(nextPage, true);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);
    }

    @Override
    public void onItemClick(View view, int position) {
        Book item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), ViewBookActivity.class);
        intent.putExtra("book",item);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        List<Book> list = db.getTop5Book();
        adapter.setList(list);
    }
}
