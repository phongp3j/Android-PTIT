package com.example.myapplication.model;

public class Sach {
    private String ten,tacgia,gio;
    private boolean cb1,cb2,cb3;

    public Sach() {
    }

    public Sach(String ten, String tacgia, String gio, boolean cb1, boolean cb2, boolean cb3) {
        this.ten = ten;
        this.tacgia = tacgia;
        this.gio = gio;
        this.cb1 = cb1;
        this.cb2 = cb2;
        this.cb3 = cb3;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public boolean isCb1() {
        return cb1;
    }

    public void setCb1(boolean cb1) {
        this.cb1 = cb1;
    }

    public boolean isCb2() {
        return cb2;
    }

    public void setCb2(boolean cb2) {
        this.cb2 = cb2;
    }

    public boolean isCb3() {
        return cb3;
    }

    public void setCb3(boolean cb3) {
        this.cb3 = cb3;
    }
}
