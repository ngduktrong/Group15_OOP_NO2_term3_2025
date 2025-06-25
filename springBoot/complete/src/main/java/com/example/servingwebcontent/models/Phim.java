package com.example.servingwebcontent.models;
import java.time.LocalDate;

public class Phim {
    private int MaPhim;
    private String TenPhim;
    private int ThoiLuong;
    private LocalDate NgayKhoiChieu;
    private String NuocSanXuat;
    private String DinhDang;
    private String MoTa;
    private String DaoDien;
    private String DuongDanPoster;
    public Phim() {
        
    }

    public Phim(int MaPhim,String TenPhim, int ThoiLuong, LocalDate NgayKhoiChieu, String NuocSanXuat, String DinhDang, String MoTa, String DaoDien, String DuongDanPoster) {
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
    public int getMaPhim() {
        return MaPhim;
    }
    public void setMaPhim(int MaPhim) {
        this.MaPhim = MaPhim;
    }
    public String getTenPhim() {
        return TenPhim;
    }
    public int getThoiLuong() {
        return ThoiLuong;
    }
    public LocalDate getNgayKhoiChieu() {
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
    public void setNgayKhoiChieu(LocalDate NgayKhoiChieu) {
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