package com.example.foodorder.model;

public class BillDetail {
    private String billId;
    private String name;
    private int price;
    private String img;
    private int quantity;

    public BillDetail(){
    }

    public BillDetail(String name,  int price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
