package com.example.todoapp.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username,fullname,password,type;
    private int id;

    public User() {
    }

    public User(String username, String fullname, String password, String type, int id) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public User(String username, String fullname, String password, String type) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
