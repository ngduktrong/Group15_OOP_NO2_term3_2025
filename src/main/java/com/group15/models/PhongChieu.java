package com.group15.models;

public class PhongChieu {
    public int MaPhong;
    public String tenPhong;
    public RapPhim rapPhim;
    public int SoLuongGhe;
    public String loaiPhong;

    public PhongChieu(int maPhong, String tenPhong, RapPhim rapPhim, int soLuongGhe, String loaiPhong) {
        this.MaPhong = maPhong;
        this.tenPhong = tenPhong;
        this.rapPhim = rapPhim;
        this.SoLuongGhe = soLuongGhe;
        this.loaiPhong = loaiPhong;
    }

    public void hienThiPhongChieu() {
        System.out.println("Ma phong: " + MaPhong);
        System.out.println("Ten phong: " + tenPhong);
        System.out.println("Rap phim: " + rapPhim.TenRap);
        System.out.println("Ma Rap: " + rapPhim.MaRap);
        System.out.println("Dia chi: " + rapPhim.DiaChi);
        System.out.println("So luong ghe: " + SoLuongGhe);
        System.out.println("Loai phong: " + loaiPhong);
    }
}