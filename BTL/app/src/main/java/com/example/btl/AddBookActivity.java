package com.example.btl;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl.model.Category;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    private EditText edTensach,edMota;
    private TextView tvDanhmuc;
    private Button selectBook,addBook,cancel;

    private FirebaseAuth firebaseAuth;

    private static final int PDF_PICK_CODE = 1000;

    private Uri uriPicked = null;

    private List<String> list;
    private List<Long> categoryID;
    private String tensach = "", mota = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initview();
        loadCateogryList();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        selectBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectIntent();
            }
        });
        tvDanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonDanhmuc();
            }
        });
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    public void initview(){
        edTensach = findViewById(R.id.edTensach);
        edMota = findViewById(R.id.edMota);
        tvDanhmuc = findViewById(R.id.tvDanhmuc);
        selectBook = findViewById(R.id.selectBook);
        addBook = findViewById(R.id.addBook);
        cancel = findViewById(R.id.cancelAddBook);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void selectIntent(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF"),PDF_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == PDF_PICK_CODE){
                uriPicked = data.getData();
                selectBook.setText(""+uriPicked);
            }
            else {
                Toast.makeText(this, "Huy Chon Sach", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadCateogryList(){
        list = new ArrayList<>();
        categoryID = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                categoryID.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    String title = ""+ds.child("category").getValue();
                    long id = (long)ds.child("id").getValue();
                    list.add(title);
                    categoryID.add(id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private String title="";
    private long id;
    public void chonDanhmuc(){
        String[] tmp = new String[list.size()];
        for(int  i = 0 ; i < list.size() ; i++){
            tmp[i] = list.get(i);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chon Danh Muc")
                .setItems(tmp, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        title = list.get(which);
                        id = categoryID.get(which);
                        tvDanhmuc.setText(title);
                    }
                }).show();
    }

    public void validateData(){
        tensach = edTensach.getText().toString().trim();
        mota = edMota.getText().toString().trim();

        if(tensach.isEmpty()){
            Toast.makeText(this, "Vui long nhap ten sach", Toast.LENGTH_SHORT).show();
        }
        else if(mota.isEmpty()){
            Toast.makeText(this, "Vui long nhap mo ta", Toast.LENGTH_SHORT).show();
        } else if (title.isEmpty()) {
            Toast.makeText(this, "Vui long chon danh muc", Toast.LENGTH_SHORT).show();
        } else if (uriPicked == null) {
            Toast.makeText(this, "Vui long tai len sach", Toast.LENGTH_SHORT).show();
        }
        else {
            uploadPdfToStorage();
        }
    }

    public void uploadPdfToStorage(){
        long timestamp = System.currentTimeMillis();
        String path = "Books/"+timestamp;
        StorageReference ref = FirebaseStorage.getInstance().getReference(path);
        ref.putFile(uriPicked)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                        String uploadPdfUrl = ""+uriTask.getResult();
                        uploadBookToDB(uploadPdfUrl,timestamp);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddBookActivity.this, "Upload khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void uploadBookToDB(String url,long timestamp){
        String uid = firebaseAuth.getUid();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("id",timestamp);
        hashMap.put("tensach",tensach);
        hashMap.put("mota",mota);
        hashMap.put("danhmucID",id);
        hashMap.put("url",url);
        hashMap.put("timestamp",timestamp);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(""+timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddBookActivity.this, "Upload Sach thanh cong", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddBookActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}