package com.group15.models;

public class NguoiDung {
    private int maNguoiDung;
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String loaiNguoiDung; // KhachHang hoặc NhanVien

    public NguoiDung() {
    }

    public NguoiDung(int maNguoiDung, String hoTen, String soDienThoai, String email, String loaiNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(String loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "maNguoiDung=" + maNguoiDung +
                ", hoTen='" + hoTen + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", loaiNguoiDung='" + loaiNguoiDung + '\'' +
                '}';
    }
}
