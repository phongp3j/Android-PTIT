package com.example.ktra2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktra2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Khoahoc.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,ngay TEXT,gio TEXT, phong TEXT,thiviet INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    //get all order by date descending
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ngay DESC";
        Cursor rs = st.query("items",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String ngay = rs.getString(2);
            String gio = rs.getString(3);
            String phong = rs.getString(4);
            int thiviet = rs.getInt(5);
            list.add(new Item(id,ten,ngay,gio,phong,thiviet));
        }
        return list;
    }
    public List<Item> searchByTitle(String key){
        List<Item> list = new ArrayList<>();
        String whereClause = "ten like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String ngay = rs.getString(2);
            String gio = rs.getString(3);
            String phong = rs.getString(4);
            int thiviet = rs.getInt(5);
            list.add(new Item(id,ten,ngay,gio,phong,thiviet));
        }
        return list;
    }
    public List<Item> searchByCategory(String category){
        List<Item> list = new ArrayList<>();
        String whereClause = "phong like ?";
        String[] whereArgs = {category};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String ngay = rs.getString(2);
            String gio = rs.getString(3);
            String phong = rs.getString(4);
            int thiviet = rs.getInt(5);
            list.add(new Item(id,ten,ngay,gio,phong,thiviet));
        }
        return list;
    }
    //add
    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("ngay",i.getNgay());
        values.put("gio",i.getGio());
        values.put("phong",i.getPhongthi());
        values.put("thiviet",i.getThiviet());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("items",null,values);
    }

    //update
    public int update(Item i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("ngay",i.getNgay());
        values.put("gio",i.getGio());
        values.put("phong",i.getPhongthi());
        values.put("thiviet",i.getThiviet());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items",values,whereClase,whereArgs);
    }

    //delete
    public int delete(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items",whereClase,whereArgs);
    }

}

