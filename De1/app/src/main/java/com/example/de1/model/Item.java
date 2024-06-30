package com.example.de1.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String ten,noidung,ngayhoanthanh,tinhtrang;
    private int congtac;

    public Item() {
    }

    public Item(int id, String ten, String noidung, String ngayhoanthanh, String tinhtrang, int congtac) {
        this.id = id;
        this.ten = ten;
        this.noidung = noidung;
        this.ngayhoanthanh = ngayhoanthanh;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public Item(String ten, String noidung, String ngayhoanthanh, String tinhtrang, int congtac) {
        this.ten = ten;
        this.noidung = noidung;
        this.ngayhoanthanh = ngayhoanthanh;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
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

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgayhoanthanh() {
        return ngayhoanthanh;
    }

    public void setNgayhoanthanh(String ngayhoanthanh) {
        this.ngayhoanthanh = ngayhoanthanh;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public int getCongtac() {
        return congtac;
    }

    public void setCongtac(int congtac) {
        this.congtac = congtac;
    }
}
