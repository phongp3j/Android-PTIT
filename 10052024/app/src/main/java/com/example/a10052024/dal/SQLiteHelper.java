package com.example.a10052024.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a10052024.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KT2.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE ves("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,noikhoihanh TEXT,dichvu TEXT, ngay TEXT,kigui INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addItem(Book i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("noikhoihanh",i.getNoikhoihanh());
        values.put("dichvu",i.getDichvu());
        values.put("ngay",i.getNgay());
        values.put("kigui",i.getKigui());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("ves",null,values);
    }
    public List<Book> getAll(){
        List<Book> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ten DESC";
        Cursor rs = st.query("ves",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noikhoihanh = rs.getString(2);
            String dichvu = rs.getString(3);
            String ngay = rs.getString(4);
            int kigui = rs.getInt(5);
            list.add(new Book(id,ten,noikhoihanh,dichvu,ngay,kigui));
        }
        return list;
    }
    public int update(Book i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("noikhoihanh",i.getNoikhoihanh());
        values.put("dichvu",i.getDichvu());
        values.put("ngay",i.getNgay());
        values.put("kigui",i.getKigui());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("ves",values,whereClase,whereArgs);
    }

    //delete
    public int delete(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("ves",whereClase,whereArgs);
    }

    public List<Book> getBookByTitlegetBookByTitle(String s){
        List<Book> list = new ArrayList<>();
//        SQLiteDatabase st = getReadableDatabase();
//        String order = "danhgia DESC";
//        Cursor rs = st.query("books",null,null,null,null,null,order);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String ten = rs.getString(1);
//            String tacgia = rs.getString(2);
//            String doituong = rs.getString(3);
//            int phamvi = rs.getInt(4);
//            int danhgia = rs.getInt(5);
//            if(ten.contains(s)){
//                list.add(new Book(id,ten,tacgia,doituong,phamvi,danhgia));
//            }
//        }
        return list;
    }
}
