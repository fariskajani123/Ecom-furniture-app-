package com.example.furnitureapp;

public class Product {
    private String name;
    private int imageResId;
    private double price;

    public Product(String name, int imageResId, double price) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public double getPrice() {
        return price;
    }
}
