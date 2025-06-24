package com.example.servingwebcontent.models;
public class Ghe {
    private int SoGhe;
    private String MaPhong;
    public int getSoGhe() {
        return SoGhe;
    }
    public void setSoGhe(int soGhe) {
        this.SoGhe = soGhe;
    }
    public String getMaPhong() {
        return MaPhong;
    }
    public void setMaPhong(String maPhong) {
        this.MaPhong = maPhong;
    }
    public Ghe(int soGhe, String maPhong) {
        this.SoGhe = soGhe;
        this.MaPhong = maPhong;
    }

   
    public Ghe() {
      
    }
}
