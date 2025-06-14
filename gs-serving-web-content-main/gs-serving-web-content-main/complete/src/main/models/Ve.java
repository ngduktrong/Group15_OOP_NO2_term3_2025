package com.example.servingwebcontent.models;

public class Ve {
    public enum TrangThaiVe{
        available , booked , paid , cancelled, pending 
    }
    private int MaVe;
    private int MaSuatChieu;
    private int MaPhong;
    private String SoGhe;
    private int MaHoaDon;
    private double GiaVe;
    private String TrangThai;
    private String NgayDat;
    public Ve(int MaVe, int MaSuatChieu, int MaPhong, String SoGhe, int MaHoaDon, double GiaVe, String TrangThai, String NgayDat) {
        this.MaVe = MaVe;
        this.MaSuatChieu = MaSuatChieu;
        this.MaPhong = MaPhong;
        this.SoGhe = SoGhe;
        this.MaHoaDon = MaHoaDon;
        this.GiaVe = GiaVe;
        this.TrangThai = TrangThai;
        this.NgayDat = NgayDat;
    }
    public int getMaVe() {
        return MaVe;
    }
    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }
    public int getMaSuatChieu() {
        return MaSuatChieu;
    }
    public void setMaSuatChieu(int MaSuatChieu) {
        this.MaSuatChieu = MaSuatChieu;
    }
    public int getMaPhong() {
        return MaPhong;
    }
    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }
    public String getSoGhe() {
        return SoGhe;
    }
    public void setSoGhe(String SoGhe) {
        this.SoGhe = SoGhe;
    }
    public int getMaHoaDon() {
        return MaHoaDon;
    }
    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }
    public double getGiaVe() {
        return GiaVe;
    }
    public void setGiaVe(double GiaVe) {
        this.GiaVe = GiaVe;
    }
    public String getTrangThai() {
        return TrangThai;
    }
    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    public String getNgayDat() {
        return NgayDat;
    }
    public void setNgayDat(String NgayDat) {
        this.NgayDat = NgayDat;
    }
    @Override
    public String toString() {
        return "Ve{" +
                "MaVe=" + MaVe +
                ", MaSuatChieu=" + MaSuatChieu +
                ", MaPhong=" + MaPhong +
                ", SoGhe='" + SoGhe + '\'' +
                ", MaHoaDon=" + MaHoaDon +
                ", GiaVe=" + GiaVe +
                ", TrangThai='" + TrangThai + '\'' +
                ", NgayDat='" + NgayDat + '\'' +
                '}';
    }
}
