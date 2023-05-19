package com.example.foodorder.model;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private String billId;
    private String name;
    private String address;
    private String phoneNumber;
    private String time;
    private int total;
    private List<BillDetail> listBillDetail;

    public Bill(){
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Bill(String address, String name, String phoneNumber, String time,  int total, List<BillDetail> listBillDetail) {
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.time = time;
        this.total = total;
        this.listBillDetail = listBillDetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BillDetail> getListDetailOrder() {
        return listBillDetail;
    }

    public void setListDetailOrder(List<BillDetail> listBillDetail) {
        this.listBillDetail = listBillDetail;
    }

    public void addToListDetailOrder(BillDetail billDetail){
        if (this.listBillDetail == null){
            this.listBillDetail = new ArrayList<>();
        }
        this.listBillDetail.add(billDetail);
    }
}
