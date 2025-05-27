package com.group15.models;

public class NguoiDung {
    
   
    private int MaNguoiDung;
    
    
    private String HoTen;

   
    private String SoDienThoai;
    
    private String Email;
   
    private String LoaiNguoiDung; // KhachHang hoặc NhanVien

   

    public NguoiDung(int maNguoiDung, String hoTen, String soDienThoai, String email, String loaiNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
        this.HoTen = HoTen;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.LoaiNguoiDung = LoaiNguoiDung;
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
