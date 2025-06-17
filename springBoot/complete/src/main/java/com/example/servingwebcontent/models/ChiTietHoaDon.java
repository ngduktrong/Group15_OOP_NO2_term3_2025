package com.example.servingwebcontent.models;

public class ChiTietHoaDon {
    private int MaHoaDon ;
    private int MaVe ;

    public ChiTietHoaDon(int MaHoaDon, int MaVe) {
        this.MaHoaDon = MaHoaDon;
        this.MaVe = MaVe;
    }
    public int getMaHoaDon() {
        return MaHoaDon;
    }
    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }
    public int getMaVe() {
        return MaVe;
    }
    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }
}
