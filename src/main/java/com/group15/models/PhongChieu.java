package com.group15.models;
public class PhongChieu {
    private int MaPhong ;
    private String TenPhong;
    private int SoLuongGhe;
    private String LoaiPhong;

    public PhongChieu(int MaPhong , String TenPhong , int SoLuongGhe , String LoaiPhong){
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.SoLuongGhe = SoLuongGhe;
        this.LoaiPhong = LoaiPhong;
    }
    public int getMaPhong(){
        return MaPhong;
    }
    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }
    public String getTenPhong(){
        return TenPhong;
    }
    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }
    public int getSoLuongGhe(){
        return SoLuongGhe;
    }
    public void setSoLuongGhe(int SoLuongGhe) {
        this.SoLuongGhe = SoLuongGhe;
    }
    public String getLoaiPhong(){
        return LoaiPhong;
    }
    public void setLoaiPhong(String LoaiPhong) {
        this.LoaiPhong = LoaiPhong ;
    }
    @Override
    public String toString() {
        return "   PhongChieu{" +
                "  MaPhong=" + MaPhong +
                ", TenPhong='" + TenPhong + '\'' +
                ", SoLuongGhe=" + SoLuongGhe +
                ", LoaiPhong='" + LoaiPhong + '\'' +
                '}';
    }
}