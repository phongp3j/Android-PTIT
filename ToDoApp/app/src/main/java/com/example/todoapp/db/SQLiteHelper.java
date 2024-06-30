package com.example.todoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "username TEXT,fullname TEXT,password TEXT, role TEXT)";
        db.execSQL(sql);
        String sql1 = "CREATE TABLE items("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "tencongviec TEXT,gio TEXT,ngay TEXT, tinhtrang TEXT,nguoilam TEXT)";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
//        onUpgrade(db,0,1);
    }

    //Register
    public long register(User i){
        ContentValues values = new ContentValues();
        values.put("username",i.getUsername());
        values.put("fullname",i.getFullname());
        values.put("password",i.getPassword());
        values.put("role",i.getType());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("users",null,values);
    }

    //Login
    public long login(String username,String password){
        String whereClause = "username like ?";
        String[] whereArgs = {username};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("users",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String user = rs.getString(1);
            String fullname = rs.getString(2);
            String pass = rs.getString(3);
            String type = rs.getString(4);
            if(user.equals(username) && password.equals(pass)){
                if(type.equals("user")){
                    return id;
                }
                else {
                    return -2;
                }
            }
        }
        return -1;
    }

    //Get all user
    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "fullname DESC";
        Cursor rs = st.query("users",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String username = rs.getString(1);
            String fullname = rs.getString(2);
            String pass = rs.getString(3);
            String role = rs.getString(4);
            list.add(new User(username,fullname,pass,role,id));
        }
        return list;
    }

    public User getUserByUsername(String username){
        List<User> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "username like ?";
        String[] whereArgs = {username};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("users",null,order,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String username1 = rs.getString(1);
            String fullname = rs.getString(2);
            String pass = rs.getString(3);
            String role = rs.getString(4);
            if(username1.equals(username)){
                list.add(new User(username1,fullname,pass,role,id));
            }
        }
        return list.get(0);
    }
    //Update user
    public int updateUser(User i){
        ContentValues values = new ContentValues();
        values.put("username",i.getUsername());
        values.put("fullname",i.getFullname());
        values.put("password",i.getPassword());
        values.put("role",i.getType());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("users",values,whereClase,whereArgs);
    }
    //Delete user
    public int deleteUser(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("users",whereClase,whereArgs);
    }

    //Thống kê việc của user
    public List<Task> getTaskUser(String userLogined){
        List<Task> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "nguoilam like ?";
        String[] whereArgs = {userLogined};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,order,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String gio = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang = rs.getString(4);
            String nguoilam = rs.getString(5);
            if(nguoilam.equals(userLogined)){
                list.add(new Task(id,ten,gio,ngay,tinhtrang,nguoilam));
            }
        }
        return list;
    }
    //Add item
    public long addItem(Task i){
        ContentValues values = new ContentValues();
        values.put("tencongviec",i.getTenCongViec());
        values.put("gio",i.getGioBatDau());
        values.put("ngay",i.getNgay());
        values.put("tinhtrang",i.getTinhTrang());
        values.put("nguoilam",i.getNguoiLam());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("items",null,values);
    }

    //get items by date
    public List<Task> getByDate(String date,String userLogined){
        List<Task> list = new ArrayList<>();
        String whereClause = "ngay like ?";
        String[] whereArgs = {date};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String gio = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang = rs.getString(4);
            String nguoilam = rs.getString(5);
            if(nguoilam.equals(userLogined)){
                list.add(new Task(id,ten,gio,ngay,tinhtrang,nguoilam));
            }
        }
        return list;
    }

    //update
    public int update(Task i){
        ContentValues values = new ContentValues();
        values.put("tencongviec",i.getTenCongViec());
        values.put("gio",i.getGioBatDau());
        values.put("ngay",i.getNgay());
        values.put("tinhtrang",i.getTinhTrang());
        values.put("nguoilam",i.getNguoiLam());
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
    //get all
    public List<Task> getAll(String userLogined){
        List<Task> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ngay DESC";
        Cursor rs = st.query("items",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String gio = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang = rs.getString(4);
            String nguoilam = rs.getString(5);
            if(nguoilam.equals(userLogined)){
                list.add(new Task(id,ten,gio,ngay,tinhtrang,nguoilam));
            }
        }
        return list;
    }
    //Search
    public List<Task> searchByTitle(String key,String userLogined){
        List<Task> list = new ArrayList<>();
        String whereClause = "tencongviec like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String gio = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang = rs.getString(4);
            String nguoilam = rs.getString(5);
            if(nguoilam.equals(userLogined)){
                list.add(new Task(id,ten,gio,ngay,tinhtrang,nguoilam));
            }
        }
        return list;
    }
    public List<Task> searchByCategory(String category,String userLogined){
        List<Task> list = new ArrayList<>();
        String whereClause = "tinhtrang like ?";
        String[] whereArgs = {category};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String gio = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang = rs.getString(4);
            String nguoilam = rs.getString(5);
            if(nguoilam.equals(userLogined)){
                list.add(new Task(id,ten,gio,ngay,tinhtrang,nguoilam));
            }
        }
        return list;
    }

    public List<Task> searchByDate(String from,String to,String userLogined){
        List<Task> list = new ArrayList<>();
        String whereClause = "ngay BETWEEN ? AND ?";
        String[] whereArgs = {from.trim(),to.trim()};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String gio = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang = rs.getString(4);
            String nguoilam = rs.getString(5);
            if(nguoilam.equals(userLogined)){
                list.add(new Task(id,ten,gio,ngay,tinhtrang,nguoilam));
            }
        }
        return list;
    }


}
