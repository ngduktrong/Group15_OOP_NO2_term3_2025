package com.group15.models;

public class NguoiDung {
    public enum LoaiNguoiDungEnum {
        khachhang ,
        nhanvien
    }
    private int MaNguoiDung;
    private String HoTen;
    private String SoDienThoai;
    private String Email;
    private String LoaiNguoiDung; // KhachHang hoặc NhanVien

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

    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getLoaiNguoiDung() {
        return LoaiNguoiDung;
    }

    public void setLoaiNguoiDung(String LoaiNguoiDung) {
        this.LoaiNguoiDung = LoaiNguoiDung;
    }
    
}
