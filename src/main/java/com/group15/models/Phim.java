package com.group15.models;

public class Phim {
    private int MaPhim;
    private String TenPhim;
    private int ThoiLuong;
    private String NgayKhoiChieu;
    private String NuocSanXuat;
    private String DinhDang;
    private String MoTa;
    private String DaoDien;
    private String DuongDanPoster;
    public Phim() {
        
    }

    public Phim(String TenPhim,int MaPhim, int ThoiLuong, String NgayKhoiChieu, String NuocSanXuat, String DinhDang, String MoTa, String DaoDien, String DuongDanPoster) {
        this.MaPhim = MaPhim;
        this.TenPhim = TenPhim;
        this.ThoiLuong = ThoiLuong;
        this.NgayKhoiChieu = NgayKhoiChieu;
        this.NuocSanXuat = NuocSanXuat;
        this.DinhDang = DinhDang;
        this.MoTa = MoTa;
        this.DaoDien = DaoDien;
        this.DuongDanPoster = DuongDanPoster;
    }

    public String getTenPhim() {
        return TenPhim;
    }
    public int getThoiLuong() {
        return ThoiLuong;
    }
    public String getNgayKhoiChieu() {
        return NgayKhoiChieu;
    }
    public String getNuocSanXuat() {
        return NuocSanXuat;
    }
    public String getDinhDang() {
        return DinhDang;
    }
    public String getMoTa() {
        return MoTa;
    }
    public String getDaoDien() {
        return DaoDien;
    }
    public String getDuongDanPoster() {
        return DuongDanPoster;
    }

    public void setTenPhim(String TenPhim) {
        this.TenPhim = TenPhim;
    }
    public void setThoiLuong(int ThoiLuong) {
        this.ThoiLuong = ThoiLuong;
    }
    public void setNgayKhoiChieu(String NgayKhoiChieu) {
        this.NgayKhoiChieu = NgayKhoiChieu;
    }
    public void setNuocSanXuat(String NuocSanXuat) {
        this.NuocSanXuat = NuocSanXuat;
    }
    public void setDinhDang(String DinhDang) {
        this.DinhDang = DinhDang;
    }
    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    public void setDaoDien(String DaoDien) {
        this.DaoDien = DaoDien;
    }
    public void setDuongDanPoster(String DuongDanPoster) {
        this.DuongDanPoster = DuongDanPoster;
    }
    public int getMaPhim() {
        return MaPhim;
    }
public void setMaPhim(int MaPhim) {
        this.MaPhim = MaPhim;
    }
    


    @Override
    public String toString() {
        return "Phim{" +
                "TenPhim='" + TenPhim + '\'' +
                ", ThoiLuong=" + ThoiLuong +
                ", NgayKhoiChieu='" + NgayKhoiChieu + '\'' +
                ", NuocSanXuat='" + NuocSanXuat + '\'' +
                ", DinhDang='" + DinhDang + '\'' +
                ", MoTa='" + MoTa + '\'' +
                ", DaoDien='" + DaoDien + '\'' +
                ", DuongDanPoster='" + DuongDanPoster + '\'' +
                '}';
    }
}