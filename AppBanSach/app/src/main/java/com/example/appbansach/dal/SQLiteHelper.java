package com.example.appbansach.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appbansach.model.Book;
import com.example.appbansach.model.Category;
import com.example.appbansach.model.Comment;
import com.example.appbansach.model.Order;
import com.example.appbansach.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Bookapp.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "email TEXT,password TEXT,phone TEXT,fullname TEXT, role TEXT)";
        db.execSQL(sql);

        String sql1 = "CREATE TABLE categories("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT)";
        db.execSQL(sql1);
        String sql2 = "CREATE TABLE books("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,mota TEXT,danhmuc TEXT, daban INTEGER, anh TEXT, gia INTEGER)";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE comments("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idSach INTEGER,email TEXT,danhgia TEXT)";
        db.execSQL(sql3);
        String sql4 = "CREATE TABLE orders("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idSach INTEGER,idUser INTEGER,diachi TEXT,sdt TEXT,trangthai TEXT, nguoinhan TEXT, gia INTEGER, soluong INTEGER)";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //USER
    //Register
    public long register(User i){
        ContentValues values = new ContentValues();
        values.put("email",i.getEmail());
        values.put("password",i.getPass());
        values.put("phone",i.getPhone());
        values.put("fullname",i.getFullname());
        values.put("role",i.getRole());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("users",null,values);
    }
    // get all user
    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "email DESC";
        Cursor rs = st.query("users",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String email = rs.getString(1);
            String password = rs.getString(2);
            String phone = rs.getString(3);
            String fullname = rs.getString(4);
            String role = rs.getString(5);
            list.add(new User(id,email,password,phone,fullname,role));
        }
        return list;
    }
    //Update user
    public int updateUser(User i){
        ContentValues values = new ContentValues();
        values.put("email",i.getEmail());
        values.put("password",i.getPass());
        values.put("phone",i.getPhone());
        values.put("fullname",i.getFullname());
        values.put("role",i.getRole());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("users",values,whereClase,whereArgs);
    }
    //get user by email
    public User getUserByEmail(String email){
        SQLiteDatabase st = getReadableDatabase();
        String order = "email DESC";
        Cursor rs = st.query("users",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String mail = rs.getString(1);
            String password = rs.getString(2);
            String phone = rs.getString(3);
            String fullname = rs.getString(4);
            String role = rs.getString(5);
            if(mail.equals(email)){
                return new User(id,mail,password,phone,fullname,role);
            }
        }
        return null;
    }
    //get user by id
    public User getUserById(int id) {
        SQLiteDatabase st = getReadableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor rs = st.query("users", null, selection, selectionArgs, null, null, null);
        User user = null;
        if (rs != null && rs.moveToFirst()) {
            String mail = rs.getString(rs.getColumnIndexOrThrow("email"));
            String password = rs.getString(rs.getColumnIndexOrThrow("password"));
            String phone = rs.getString(rs.getColumnIndexOrThrow("phone"));
            String fullname = rs.getString(rs.getColumnIndexOrThrow("fullname"));
            String role = rs.getString(rs.getColumnIndexOrThrow("role"));
            user = new User(id, mail, password, phone, fullname, role);
        }
        if (rs != null) {
            rs.close();
        }
        return user;
    }

    //Delete user
    public int deleteUser(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("users",whereClase,whereArgs);
    }
    //Login
    public long login(String username,String password){
        String whereClause = "email like ?";
        String[] whereArgs = {username};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("users",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String email = rs.getString(1);
            String pass = rs.getString(2);
            String phone = rs.getString(3);
            String fullname = rs.getString(4);
            String role = rs.getString(5);
            if(email.equals(username) && password.equals(pass)){
                if(role.equals("user")){
                    return 0;
                }
                else {
                    return 1;
                }
            }
        }
        return 2;
    }
    //get order by id user
    public List<Order> getOrderByIdUser(int idUser) {
        List<Order> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "id DESC";
        Cursor rs = st.query("orders",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            int idsach = rs.getInt(1);
            int iduser = rs.getInt(2);
            String diachi = rs.getString(3);
            String sdt = rs.getString(4);
            String trangthai = rs.getString(5);
            String nguoinhan = rs.getString(6);
            int gia = rs.getInt(7);
            int soluong = rs.getInt(8);
            if(iduser == idUser){
                res.add(new Order(id,idsach,iduser,diachi,sdt,trangthai,nguoinhan,gia,soluong));
            }
        }
        return res;
    }
    //get all order
    public List<Order> getAllOrder() {
        List<Order> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "id DESC";
        Cursor rs = st.query("orders",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            int idsach = rs.getInt(1);
            int iduser = rs.getInt(2);
            String diachi = rs.getString(3);
            String sdt = rs.getString(4);
            String trangthai = rs.getString(5);
            String nguoinhan = rs.getString(6);
            int gia = rs.getInt(7);
            int soluong = rs.getInt(8);
            res.add(new Order(id,idsach,iduser,diachi,sdt,trangthai,nguoinhan,gia,soluong));
        }
        return res;
    }

    // add category
    public long addCategory(Category i){
        ContentValues values = new ContentValues();
        values.put("name",i.getName());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("categories",null,values);
    }
    //delete category
    public int deleteCategory(int id,String cate){
        String whereClase = "id= ?";
        deleteBookByCate(cate);
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("categories",whereClase,whereArgs);
    }
    //update category
    public int updateCategory(Category i){
        ContentValues values = new ContentValues();
        values.put("name",i.getName());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("categories",values,whereClase,whereArgs);
    }
    //update book by category
    public int updateBookByCategory(String cateOld,String cateNew){
        List<Book> list = getBookByCate(cateOld);
        int res = 0;
        for(Book i : list){
            ContentValues values = new ContentValues();
            values.put("danhmuc", cateNew);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(i.getId())};
            int rowsAffected = sqLiteDatabase.update("books", values, whereClause, whereArgs);
            res++;
        }

        return  res;
    }
    //get all category
    public List<Category> getAllCategory(){
        List<Category> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "name DESC";
        Cursor rs = st.query("categories",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            res.add(new Category(id,name));
        }
        return res;
    }
    //delete book by id
    public int deleteBook(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("books",whereClase,whereArgs);
    }
    // delete book by cate
    public int deleteBookByCate(String cate){
        String whereClase = "danhmuc = ?";
        String[] whereArgs = {cate};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("books",whereClase,whereArgs);
    }
    //add book
    public long addBook(Book i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("mota",i.getMota());
        values.put("danhmuc",i.getDanhmuc());
        values.put("daban",i.getDaban());
        values.put("anh",i.getUrl());
        values.put("gia",i.getGia());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("books",null,values);
    }
    //update book
    public int updateBook(Book i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("mota",i.getMota());
        values.put("danhmuc",i.getDanhmuc());
        values.put("daban",i.getDaban());
        values.put("anh",i.getUrl());
        values.put("gia",i.getGia());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("books",values,whereClase,whereArgs);
    }
    //get book by category
    public List<Book> getBookByCate(String cate){
        List<Book> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ten DESC";
        Cursor rs = st.query("books",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String mota = rs.getString(2);
            String danhmuc = rs.getString(3);
            int daban = rs.getInt(4);
            String anh = rs.getString(5);
            int gia = rs.getInt(6);
            if(danhmuc.equals(cate)){
                res.add(new Book(id,danhmuc,ten,mota,anh,daban,gia));
            }
        }
        return res;
    }

    //get book by id
    public Book getBookById(int id) {
        SQLiteDatabase st = getReadableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor rs = st.query("books", null, selection, selectionArgs, null, null, null);
        Book book = null;
        if (rs != null && rs.moveToFirst()) {
            String ten = rs.getString(rs.getColumnIndexOrThrow("ten"));
            String mota = rs.getString(rs.getColumnIndexOrThrow("mota"));
            String danhmuc = rs.getString(rs.getColumnIndexOrThrow("danhmuc"));
            int daban = rs.getInt(rs.getColumnIndexOrThrow("daban"));
            String anh = rs.getString(rs.getColumnIndexOrThrow("anh"));
            int gia = rs.getInt(rs.getColumnIndexOrThrow("gia"));
            book = new Book(id, danhmuc, ten, mota, anh, daban, gia);
        }
        if (rs != null) {
            rs.close();
        }
        return book;
    }
    // get top 5 book
    public List<Book> getTop5Book(){
        List<Book> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "daban DESC"; // Sắp xếp theo số lượng đã bán giảm dần
        Cursor rs = st.query("books", null, null, null, null, null, order, "5"); // Giới hạn kết quả trả về là 5
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String mota = rs.getString(2);
            String danhmuc = rs.getString(3);
            int daban = rs.getInt(4);
            String anh = rs.getString(5);
            int gia = rs.getInt(6);
            res.add(new Book(id, danhmuc, ten, mota, anh, daban, gia));
        }
        return res;
    }

    //get all book
    public List<Book> getAllBook(){
        List<Book> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "daban DESC"; // Sắp xếp theo số lượng đã bán giảm dần
        Cursor rs = st.query("books", null, null, null, null, null, order);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String mota = rs.getString(2);
            String danhmuc = rs.getString(3);
            int daban = rs.getInt(4);
            String anh = rs.getString(5);
            int gia = rs.getInt(6);
            res.add(new Book(id, danhmuc, ten, mota, anh, daban, gia));
        }
        return res;
    }

    //search book by title
    public List<Book> searchBookByTitle(String title){
        List<Book> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "daban DESC"; // Sắp xếp theo số lượng đã bán giảm dần
        // Điều kiện WHERE để lọc dữ liệu dựa trên tiêu đề
        String selection = "ten LIKE ?";
        String[] selectionArgs = {"%" + title + "%"};
        Cursor rs = st.query("books", null, selection, selectionArgs, null, null, order);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String mota = rs.getString(2);
            String danhmuc = rs.getString(3);
            int daban = rs.getInt(4);
            String anh = rs.getString(5);
            int gia = rs.getInt(6);
            res.add(new Book(id, danhmuc, ten, mota, anh, daban, gia));
        }
        return res;
    }

    //add comment
    public long addComment(Comment i){
        ContentValues values = new ContentValues();
        values.put("idSach",i.getIdSach());
        values.put("email",i.getEmail());
        values.put("danhgia",i.getDanhgia());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("comments",null,values);
    }

    //get comment by book
    public List<Comment> getComment(int idsach){
        List<Comment> res = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "idSach DESC";
        String selection = "idSach = ?";
        String[] selectionArgs = {String.valueOf(idsach)};
        Cursor rs = st.query("comments", null, selection, selectionArgs, null, null, order);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            int idSach = rs.getInt(1);
            String email = rs.getString(2);
            String danhgia = rs.getString(3);
            res.add(new Comment(id, idSach, email, danhgia));
        }
        return res;
    }

    //add order
    public long addOrder(Order i){
        ContentValues values = new ContentValues();
        values.put("idSach",i.getIdSach());
        values.put("idUser",i.getIdUser());
        values.put("diachi",i.getDiachi());
        values.put("sdt",i.getSdt());
        values.put("trangthai",i.getTrangthai());
        values.put("nguoinhan",i.getNguoinhan());
        values.put("gia",i.getGia());
        values.put("soluong",i.getSoluong());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("orders",null,values);
    }

    //update order
    public int updateOrder(Order i){
        ContentValues values = new ContentValues();
        values.put("idSach",i.getIdSach());
        values.put("idUser",i.getIdUser());
        values.put("diachi",i.getDiachi());
        values.put("sdt",i.getSdt());
        values.put("trangthai",i.getTrangthai());
        values.put("nguoinhan",i.getNguoinhan());
        values.put("gia",i.getGia());
        values.put("soluong",i.getSoluong());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("orders",values,whereClase,whereArgs);
    }
}
