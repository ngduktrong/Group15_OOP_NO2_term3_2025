package com.example.servingwebcontent.models;

import java.time.LocalDateTime;

public class Ve {
    public enum TrangThaiVe {
        available, booked, paid, cancelled, pending
    }

    private int MaVe;
    private int MaSuatChieu;
    private int MaPhong;
    private String SoGhe;
    private int MaHoaDon;
    private double GiaVe;
    private String TrangThai;
    private String NgayDat;

    // ✅ Bổ sung: ngày giờ chiếu lấy từ bảng SuatChieu
    private LocalDateTime ngayGioChieu;

    // Constructor gốc
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

    // Getter/setter cho tất cả các thuộc tính
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

    // ✅ Getter và Setter cho ngày giờ chiếu
    public LocalDateTime getNgayGioChieu() {
        return ngayGioChieu;
    }

    public void setNgayGioChieu(LocalDateTime ngayGioChieu) {
        this.ngayGioChieu = ngayGioChieu;
    }
    public Ve() {
        // Constructor rỗng
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
                ", NgayGioChieu=" + ngayGioChieu +
                '}';
    }
}
