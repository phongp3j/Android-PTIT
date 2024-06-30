package com.example.appbansach.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id,idSach;
    private String email,danhgia;

    public Comment() {
    }

    public Comment(int id, int idSach, String email, String danhgia) {
        this.id = id;
        this.idSach = idSach;
        this.email = email;
        this.danhgia = danhgia;
    }

    public Comment(int idSach, String email, String danhgia) {
        this.idSach = idSach;
        this.email = email;
        this.danhgia = danhgia;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }
}
