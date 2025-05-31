package com.group15.models;
public class Ghe {
    private int SoGhe ;
    private String MaPhong ;

    public int getSoghe(){
        return SoGhe ;
    }
    public void setSoGhe(int MaGhe){
        this.SoGhe = SoGhe ;
    }
    public String getMaPhong(){
        return MaPhong ;
    }
    public void setMaPhong(String MaPhong){
        this.MaPhong = MaPhong ;
    }
    public Ghe(int SoGhe, String MaPhong) {
        this.SoGhe = SoGhe;
        this.MaPhong = MaPhong;
    }
    public Ghe(){
        super(); // Super lớp con muốn gọi construc lớp cha 
    }
}
