package com.example.servingwebcontent.models;
public class Ghe {
    private String SoGhe;
    private String MaPhong;
    public String getSoGhe() {
        return SoGhe;
    }
    public void setSoGhe(String soGhe) {
        this.SoGhe = soGhe;
    }
    public String getMaPhong() {
        return MaPhong;
    }
    public void setMaPhong(String maPhong) {
        this.MaPhong = maPhong;
    }
    public Ghe(String soGhe, String maPhong) {
        this.SoGhe = soGhe;
        this.MaPhong = maPhong;
    }

   
    public Ghe() {
      
    }
}
