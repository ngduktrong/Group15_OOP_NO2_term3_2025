package Javaproject.src.models;
public class Customer {
    private int makhachhang;
    private String ten;
    private String sodienthoai;
    private String email;

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