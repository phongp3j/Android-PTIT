package com.example.btl.model;

public class Book {
    private String uid,tensach,mota,url;
    private long id,danhmucID,timestamp;

    public Book() {
    }

    public Book(String uid, String tensach, String mota, String url, long id, long danhmucID, long timestamp) {
        this.uid = uid;
        this.tensach = tensach;
        this.mota = mota;
        this.url = url;
        this.id = id;
        this.danhmucID = danhmucID;
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDanhmucID() {
        return danhmucID;
    }

    public void setDanhmucID(long danhmucID) {
        this.danhmucID = danhmucID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
