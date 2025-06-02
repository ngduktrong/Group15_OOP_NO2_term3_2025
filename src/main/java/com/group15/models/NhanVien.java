package com.group15.models;

public class NhanVien {
    public enum ChucVu {
        Admin , QuanLy, ThuNgan, BanVe
    }
    private int MaNhanVien;
    private String Ten;
    private String ChucVu;
    private String CaLam;
    private String SoDienThoai;
    private String Email;

    public NhanVien(int MaNhanVien , String Ten , String ChucVu, String CaLam,String SoDienThoai,String Email){
        this.MaNhanVien=MaNhanVien;
        this.Ten=Ten;
        this.ChucVu=ChucVu;
        this.CaLam=CaLam;
        this.SoDienThoai=SoDienThoai;
        this.Email=Email;
    }
    public int getMaNhanVien() {
        return MaNhanVien;
    }
    public String getTen(){
        return Ten;
    }
    public String getChucVu() {
        return ChucVu;
    }
    public String getCaLam() {
        return CaLam;
    }
    public String getSoDienThoai() {
        return SoDienThoai;
    }
    public String getEmail() {
        return Email;
    }
    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }
    public void setTen(String Ten) {
        this.Ten = Ten;
    }
    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }
    public void setCaLam(String CaLam) {
        this.CaLam = CaLam;
    }
    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
