package com.group15.models;
public class Ghe {
    private int soGhe;
    private String maPhong;
    public int getSoGhe() {
        return soGhe;
    }
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
    public String getMaPhong() {
        return maPhong;
    }
    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }
    public Ghe(int soGhe, String maPhong) {
        this.soGhe = soGhe;
        this.maPhong = maPhong;
    }

   
    public Ghe() {
      
    }
}
