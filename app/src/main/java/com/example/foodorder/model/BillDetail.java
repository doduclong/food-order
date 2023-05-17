package com.example.foodorder.model;

public class BillDetail {
    private String billId;
    private String name;
    private String img;
    private String status;
    private int price;
    private int numProduct;

    public BillDetail(){
    }

    public BillDetail(String name, String img, String status, int price) {
        this.name = name;
        this.img = img;
        this.status = status;
        this.price = price;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }
}
