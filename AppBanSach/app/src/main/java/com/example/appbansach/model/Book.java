package com.example.appbansach.model;

import java.io.Serializable;

public class Book implements Serializable
{
    private int id;
    private String danhmuc,ten,mota,url;

    private int daban;

    private long gia;

    public Book() {
    }

    public Book(int id, String danhmuc, String ten, String mota, String url, int daban, long gia) {
        this.id = id;
        this.danhmuc = danhmuc;
        this.ten = ten;
        this.mota = mota;
        this.url = url;
        this.daban = daban;
        this.gia = gia;
    }

    public Book(String danhmuc, String ten, String mota, String url, int daban, long gia) {
        this.danhmuc = danhmuc;
        this.ten = ten;
        this.mota = mota;
        this.url = url;
        this.daban = daban;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDaban() {
        return daban;
    }

    public void setDaban(int daban) {
        this.daban = daban;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }
}
