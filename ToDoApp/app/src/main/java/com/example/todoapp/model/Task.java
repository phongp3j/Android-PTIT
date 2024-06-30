package com.example.todoapp.model;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String tenCongViec,gioBatDau,ngay,tinhTrang,nguoiLam;

    public Task() {
    }

    public Task(int id, String tenCongViec, String gioBatDau, String ngay, String tinhTrang, String nguoiLam) {
        this.id = id;
        this.tenCongViec = tenCongViec;
        this.gioBatDau = gioBatDau;
        this.ngay = ngay;
        this.tinhTrang = tinhTrang;
        this.nguoiLam = nguoiLam;
    }

    public Task(String tenCongViec, String gioBatDau, String ngay, String tinhTrang, String nguoiLam) {
        this.tenCongViec = tenCongViec;
        this.gioBatDau = gioBatDau;
        this.ngay = ngay;
        this.tinhTrang = tinhTrang;
        this.nguoiLam = nguoiLam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNguoiLam() {
        return nguoiLam;
    }

    public void setNguoiLam(String nguoiLam) {
        this.nguoiLam = nguoiLam;
    }
}
