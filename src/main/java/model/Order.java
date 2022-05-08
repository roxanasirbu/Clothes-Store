package com.example.pos;

import java.util.Date;

public class Order {
    private String Cname;
    private int quantity;
    private double price;
    private String address;
    private Date date;
    private String status;
    private String productname;


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Order(String cname, int quantity, double price, String address, Date date, String status, String prodname) {
        this.Cname = cname;
        this.quantity = quantity;
        this.price = price;
        this.address = address;
        this.date = date;
        this.status = status;
        this.productname = prodname;
    }

    public Order() {

    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
