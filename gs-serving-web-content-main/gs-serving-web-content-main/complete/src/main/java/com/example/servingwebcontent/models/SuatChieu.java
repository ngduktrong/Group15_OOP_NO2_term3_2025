package com.example.servingwebcontent.models;
public class SuatChieu {
    private int MaSuatChieu;
    private int MaPhim;
    private int MaPhong;
    private String NgayGioChieu;

    public SuatChieu(int MaSuatChieu, int MaPhim, int MaPhong, String NgayGioChieu) {
        this.MaSuatChieu = MaSuatChieu;
        this.MaPhim = MaPhim;
        this.MaPhong = MaPhong;
        this.NgayGioChieu = NgayGioChieu;
    }

    public SuatChieu() {
        super(); 
    }

    public int getMaSuatChieu() {
        return MaSuatChieu;
    }

    public void setMaSuatChieu(int MaSuatChieu) {
        this.MaSuatChieu = MaSuatChieu;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int MaPhim) {
        this.MaPhim = MaPhim;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getNgayGioChieu() {
        return NgayGioChieu;
    }

    public void setNgayGioChieu(String NgayGioChieu) {
        this.NgayGioChieu = NgayGioChieu;
    }

}
