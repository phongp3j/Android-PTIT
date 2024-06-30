package com.example.appbansach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansach.R;
import com.example.appbansach.admin.BookRecycleViewAdapter;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.Book;
import com.example.appbansach.user.ViewBookActivity;

import java.util.List;

public class FragmentTimKiem  extends Fragment implements BookRecycleViewAdapter.ItemListener{
    private SearchView searchView;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    BookRecycleViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timkiem,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recyclView);
        adapter = new BookRecycleViewAdapter();
        db=  new SQLiteHelper(getContext());
        List<Book> list = db.getAllBook();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Book> list = db.searchBookByTitle(newText);
                adapter.setList(list);
                return true;
            }
        });
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
        List<Book> list = db.getAllBook();
        adapter.setList(list);
    }
}