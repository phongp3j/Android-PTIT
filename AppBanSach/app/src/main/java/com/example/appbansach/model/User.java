package com.example.appbansach.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email,pass,phone,fullname,role;

    public User() {
    }

    public User(int id, String email, String pass, String phone, String fullname, String role) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.fullname = fullname;
        this.role = role;
    }

    public User(String email, String pass, String phone, String fullname, String role) {
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.fullname = fullname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
