package com.example.pos;

public class Product {
    private int id;
    private String name;
    private String desc;
    private double price;
    private int size;


    public Product(String name, int size, double price, String desc) {

        this.name = name;
        this.desc = desc;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
