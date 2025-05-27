package com.group15.models;

public class NguoiDung {
   

    public NguoiDung(int maNguoiDung, String hoTen, String soDienThoai, String email, String loaiNguoiDung) {
        this.MaNguoiDung = maNguoiDung;
        this.HoTen = hoTen;
        this.SoDienThoai = soDienThoai;
        this.Email = email;
        this.LoaiNguoiDung = loaiNguoiDung;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.MaNguoiDung = maNguoiDung;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        this.HoTen = hoTen;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getLoaiNguoiDung() {
        return LoaiNguoiDung;
    }

    public void setLoaiNguoiDung(String loaiNguoiDung) {
        this.LoaiNguoiDung = loaiNguoiDung;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "maNguoiDung=" + MaNguoiDung +
                ", hoTen='" + HoTen + '\'' +
                ", soDienThoai='" + SoDienThoai + '\'' +
                ", email='" + Email + '\'' +
                ", loaiNguoiDung='" + LoaiNguoiDung + '\'' +
                '}';
    }
}
