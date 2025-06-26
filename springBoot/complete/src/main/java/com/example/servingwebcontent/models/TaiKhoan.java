package com.example.servingwebcontent.models;

public class TaiKhoan {

    public enum LoaiTaiKhoan {
        admin  , user ;

        public String toDatabaseValue() {
            return this.name().toLowerCase();
        }

        public static LoaiTaiKhoan fromDatabaseValue(String value) {
            return value != null ? LoaiTaiKhoan.valueOf(value.toUpperCase()) : user ; // default fallback
        }
    }

    private String tenDangNhap;  // PRIMARY KEY
    private String matKhau;
    private LoaiTaiKhoan loaiTaiKhoan;
    private int maNguoiDung;

    // Constructors
    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String loaiTaiKhoanStr, int maNguoiDung) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = LoaiTaiKhoan.fromDatabaseValue(loaiTaiKhoanStr);
        this.maNguoiDung = maNguoiDung;
    }

    // Getters and setters
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public LoaiTaiKhoan getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(LoaiTaiKhoan loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoanStr) {
        this.loaiTaiKhoan = LoaiTaiKhoan.fromDatabaseValue(loaiTaiKhoanStr);
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    // Helper để lưu xuống DB
    public String getLoaiTaiKhoanAsString() {
        return loaiTaiKhoan != null ? loaiTaiKhoan.toDatabaseValue() : null;
    }
}
