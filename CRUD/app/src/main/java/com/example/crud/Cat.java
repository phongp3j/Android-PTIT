package com.example.crud;

public class Cat {
    private int image;
    private String name, decribe;
    private double price;
    public Cat(){}
    public Cat(int image, String name, String decribe, double price) {
        this.image = image;
        this.name = name;
        this.decribe = decribe;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecribe() {
        return decribe;
    }

    public void setDecribe(String decribe) {
        this.decribe = decribe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
