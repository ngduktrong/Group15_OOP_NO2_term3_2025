package com.example.servingwebcontent.models;
public class HoaDon {
    private int MaHoaDon ;
    private int MaKhachHang;
    private String NgayLap ;
    private double TongTien;
    

public HoaDon(int MaHoaDon, int MaKhachHang, String NgayLap, double TongTien) {
        this.MaHoaDon = MaHoaDon;
        this.MaKhachHang = MaKhachHang;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }

public int getMaHoaDon() {
        return MaHoaDon;
    }
public void setMaHoaDon(int MaHoaDon){
        this.MaHoaDon = MaHoaDon;
    }
public int getMaKhachHang(){
        return MaKhachHang ;
    }
public void setMaKhachHang(int MaKhachHang){
        this.MaKhachHang = MaKhachHang;
    }
public String getNgayLap() {
        return NgayLap;
    }
public void setNgayLap(String NgayLap) {
        this.NgayLap = NgayLap;
    }
public double getTongTien() {
        return TongTien;
    }
public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
@Override
public String toString() {
        return "HoaDon{" +
                "MaHoaDon=" + MaHoaDon +
                ", MaKhachHang=" + MaKhachHang +
                ", NgayLap='" + NgayLap + '\'' +
                ", TongTien=" + TongTien +
                '}';
    }

}