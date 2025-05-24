package com.group15.models;

public class KhachHang {
    private int MaKhachHang;
    private String Ten;
    private String SoDienThoai;
    private String Email;


    public KhachHang( int MaKhachHang,String Ten,String SoDienThoai, String Email ){
        this.MaKhachHang =MaKhachHang;
        this.Ten=Ten;
        this.SoDienThoai=SoDienThoai;
        this.Email=Email;
    }
    public int getMaKhachHang() {
        return MaKhachHang;
    }
    public String getTen(){
        return Ten;
    }
    public String getSoDienThoai() {
        return SoDienThoai;
    }
    public String getEmail() {
        return Email;
    }
    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }
    public void setTen(String Ten) {
        this.Ten = Ten;
    }
    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void hienthithongtinkhachhang(){
        System.out.println("Ma Khach Hang:" +MaKhachHang);
        System.out.println("Ten:"+Ten);
        System.out.println("So Dien Thoai:"+SoDienThoai);
        System.out.println("Email:"+Email);
    }
}
