package com.example.ktra2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;

    private String ten,ngay,gio,phongthi;
    private int thiviet;

    public Item() {
    }

    public Item(int id, String ten, String ngay, String gio, String phongthi, int thiviet) {
        this.id = id;
        this.ten = ten;
        this.ngay = ngay;
        this.gio = gio;
        this.phongthi = phongthi;
        this.thiviet = thiviet;
    }

    public Item(String ten, String ngay, String gio, String phongthi, int thiviet) {
        this.ten = ten;
        this.ngay = ngay;
        this.gio = gio;
        this.phongthi = phongthi;
        this.thiviet = thiviet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getPhongthi() {
        return phongthi;
    }

    public void setPhongthi(String phongthi) {
        this.phongthi = phongthi;
    }

    public int getThiviet() {
        return thiviet;
    }

    public void setThiviet(int thiviet) {
        this.thiviet = thiviet;
    }
}
