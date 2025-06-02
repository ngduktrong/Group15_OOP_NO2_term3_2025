package com.group15.models;

public class TaiKhoan {
    public enum LoaiTaiKhoan {
        admin, user
    }
    private String TenDangNhap; // PRIMARY KEY
    private String MatKhau;
    private String LoaiTaiKhoan; // "admin" hoặc "user"
    private int MaNguoiDung; // UNIQUE, FOREIGN KEY

    public TaiKhoan(String TenDangNhap, String MatKhau, String LoaiTaiKhoan, int MaNguoiDung) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.LoaiTaiKhoan = LoaiTaiKhoan;
        this.MaNguoiDung = MaNguoiDung;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getLoaiTaiKhoan() {
        return LoaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String LoaiTaiKhoan) {
        this.LoaiTaiKhoan = LoaiTaiKhoan;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }
}