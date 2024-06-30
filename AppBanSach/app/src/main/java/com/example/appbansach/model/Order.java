package com.example.appbansach.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id,idSach,idUser;

    private String diachi,sdt,trangthai,nguoinhan;

    private int gia,soluong;

    public Order() {
    }

    public Order(int id, int idSach, int idUser, String diachi, String sdt, String trangthai, String nguoinhan, int gia, int soluong) {
        this.id = id;
        this.idSach = idSach;
        this.idUser = idUser;
        this.diachi = diachi;
        this.sdt = sdt;
        this.trangthai = trangthai;
        this.nguoinhan = nguoinhan;
        this.gia = gia;
        this.soluong = soluong;
    }

    public Order(int idSach, int idUser, String diachi, String sdt, String trangthai, String nguoinhan, int gia, int soluong) {
        this.idSach = idSach;
        this.idUser = idUser;
        this.diachi = diachi;
        this.sdt = sdt;
        this.trangthai = trangthai;
        this.nguoinhan = nguoinhan;
        this.gia = gia;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNguoinhan() {
        return nguoinhan;
    }

    public void setNguoinhan(String nguoinhan) {
        this.nguoinhan = nguoinhan;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
