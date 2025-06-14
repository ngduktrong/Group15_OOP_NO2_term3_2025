package com.group15.models;

public class NhanVien extends NguoiDung {
    public enum VaiTro {
        Admin, QuanLy, ThuNgan, BanVe
    }
    public NhanVien() {}

    private int MaNguoiDung;
    private VaiTro VaiTro;
    private String ChucVu;
    private double Luong;

    public NhanVien(int MaNguoiDung, VaiTro vaiTro, String ChucVu, double Luong) {
        this.MaNguoiDung = MaNguoiDung;
        this.VaiTro = vaiTro;
        this.ChucVu = ChucVu;
        this.Luong = Luong;
    }

    @Override
    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public VaiTro getVaiTro() {
        return VaiTro;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public double getLuong() {
        return Luong;
    }
    @Override
    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }

    public void setVaiTro(VaiTro VaiTro) {
        this.VaiTro = VaiTro;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public void setLuong(double Luong) {
        this.Luong = Luong;
    }
    @Override
    public String toString() {
        return "NhanVien{" +
                "MaNguoiDung=" + MaNguoiDung +
                ", vaiTro=" + VaiTro +
                ", ChucVu='" + ChucVu + '\'' +
                ", Luong=" + Luong +
                '}';
    }
}
