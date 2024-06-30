package com.example.de1.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.de1.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CongViec.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,noidung TEXT,ngayhoanthanh TEXT, tinhtrang TEXT, congtac INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add
    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("noidung",i.getNoidung());
        values.put("ngayhoanthanh",i.getNgayhoanthanh());
        values.put("tinhtrang",i.getTinhtrang());
        values.put("congtac",i.getCongtac());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("items",null,values);
    }

    //delete
    public int delete(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items",whereClase,whereArgs);
    }

    //update
    public int update(Item i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("noidung",i.getNoidung());
        values.put("ngayhoanthanh",i.getNgayhoanthanh());
        values.put("tinhtrang",i.getTinhtrang());
        values.put("congtac",i.getCongtac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items",values,whereClase,whereArgs);
    }

    //get all order by date descending
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ngayhoanthanh DESC";
        Cursor rs = st.query("items",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngayhoanthanh = rs.getString(3);
            String tinhtrang = rs.getString(4);
            int congtac = rs.getInt(5);
            list.add(new Item(id,ten,noidung,ngayhoanthanh,tinhtrang,congtac));
        }
        return list;
    }
    //get items by date
    public List<Item> getByDate(String date){
        List<Item> list = new ArrayList<>();
        String whereClause = "ngayhoanthanh like ?";
        String[] whereArgs = {date};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngayhoanthanh = rs.getString(3);
            String tinhtrang = rs.getString(4);
            int congtac = rs.getInt(5);
            list.add(new Item(id,ten,noidung,ngayhoanthanh,tinhtrang,congtac));
        }
        return list;
    }
    //Search
    public List<Item> searchByTitle(String key){
        List<Item> list = new ArrayList<>();
        String whereClause = "ten like ? or noidung like ?";
        String[] whereArgs = {"%"+key+"%", "%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngayhoanthanh = rs.getString(3);
            String tinhtrang = rs.getString(4);
            int congtac = rs.getInt(5);
            list.add(new Item(id,ten,noidung,ngayhoanthanh,tinhtrang,congtac));
        }
        return list;
    }
    public List<Item> searchByCategory(String category){
        List<Item> list = new ArrayList<>();
        Date today = new Date();
        String whereClause = "tinhtrang like ?";
        String[] whereArgs = {category};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngayhoanthanh = rs.getString(3);
            String tinhtrang = rs.getString(4);
            int congtac = rs.getInt(5);
            list.add(new Item(id,ten,noidung,ngayhoanthanh,tinhtrang,congtac));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                // Tính khoảng cách tới thời gian thực tế
                Date date1 = new Date();
                Date date2 = new Date();
                try {
                    date1 = formatter.parse(item1.getNgayhoanthanh());
                    date2 = formatter.parse(item2.getNgayhoanthanh());
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                long distance1 = Math.abs(date1.getTime() - today.getTime());
                long distance2 = Math.abs(date2.getTime() - today.getTime());
                // So sánh và đặt mục gần thời gian thực tế trước
                return Long.compare(distance1, distance2);
            }
        });
        return list;
    }
}
