package com.group15.models;
public class Customer {
    private final int makhachhang;
    private final String ten;
    private final String sodienthoai;
    private final String email;

    public Customer(int makhachhang, String ten, String sodienthoai, String email) {
        this.makhachhang = makhachhang;
        this.ten = ten;
        this.sodienthoai = sodienthoai;
        this.email = email;
    }
    public int getMakhachhang() {
        return makhachhang;
    }

    public String getTen() {
        return ten;
    }
    public String getSodienthoai() {
        return sodienthoai;
    }
    public String getEmail() {
        return email;
    }
    public void hienThiThongTin() {
        System.out.println("Ma KH: " + makhachhang);
        System.out.println("Ten: " + ten);
        System.out.println("SĐT: " + sodienthoai);
        System.out.println("Email: " + email);
    }

}