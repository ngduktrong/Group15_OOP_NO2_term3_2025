package com.example.servingwebcontent.models;
public class Ghe {
    private String SoGhe;
    private int MaPhong;
    
    public String getSoGhe() {
        return SoGhe;
    }
    public void setSoGhe(String soGhe) {
        this.SoGhe = soGhe;
    }
    public int getMaPhong() {
        return MaPhong;
    }
    public void setMaPhong(int maPhong) {
        this.MaPhong = maPhong;
    }
    
    public Ghe(String soGhe, int maPhong) {
        this.SoGhe = soGhe;
        this.MaPhong = maPhong;
        
    }

   
    public Ghe() {
      
    }
}
