package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.ListBookAdminActivity;
import com.example.btl.R;
import com.example.btl.model.Book;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class BookListAdminAdapter extends RecyclerView.Adapter<BookListAdminAdapter.BookListAdminViewHolder> {
    private Context context;

    private List<Book> listBook;

    public BookListAdminAdapter(Context context, List<Book> list) {
        this.context=context;
        this.listBook=list;
    }

    @NonNull
    @Override
    public BookListAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book_admin,parent,false);
        return new BookListAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdminViewHolder holder, int position) {
        Book book = listBook.get(position);
        String ten = book.getTensach();
        String mota = book.getMota();
        long danhmuc = book.getDanhmucID();
        holder.tvTen.setText(ten);
        holder.tvMota.setText(mota);
        loadBookFromUrl(book,holder);
        loadCategory(book,holder);
    }
    public void loadBookFromUrl(Book book,BookListAdminViewHolder holder){
        String url = book.getUrl();
        StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(url);
        ref.getBytes(50000000)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        holder.pdfView.fromBytes(bytes)
                                .pages(0)
                                .spacing(0)
                                .swipeHorizontal(false)
                                .enableSwipe(false)
                                .onError(new OnErrorListener() {
                                    @Override
                                    public void onError(Throwable t) {

                                    }
                                })
                                .onPageError(new OnPageErrorListener() {
                                    @Override
                                    public void onPageError(int page, Throwable t) {

                                    }
                                })
                                .load();
                    }
                });
    }
    public void loadCategory(Book book,BookListAdminViewHolder holder){
        long cateid = book.getId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.child(""+cateid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String catetitle = ""+snapshot.child("category").getValue();

                        holder.tvCate.setText(catetitle);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    @Override
    public int getItemCount() {
        return listBook.size();
    }

    class BookListAdminViewHolder extends RecyclerView.ViewHolder{
        PDFView pdfView;
        TextView tvTen,tvMota,tvCate;
        public BookListAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfView = itemView.findViewById(R.id.pdfView);
            tvTen = itemView.findViewById(R.id.tvTensachrow);
            tvMota = itemView.findViewById(R.id.tvMotarow);
            tvCate = itemView.findViewById(R.id.tvCategoryrow);
        }
    }
}
