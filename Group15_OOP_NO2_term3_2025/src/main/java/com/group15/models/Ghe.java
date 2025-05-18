package com.group15.models;
import Javaproject.src.models.PhongChieu;

public class Ghe {
    public int MaGhe;
    public PhongChieu PhongChieu;
    public String SoGhe;
    public String LoaiGhe;

    public Ghe(int MaGhe, PhongChieu phongChieu, String SoGhe, String LoaiGhe) {
        this.MaGhe = MaGhe;
        this.PhongChieu = phongChieu;
        this.SoGhe = SoGhe;
        this.LoaiGhe = LoaiGhe;
    }
    public void HienThiGhe() {
        System.out.println("Ma ghe: " + MaGhe);
        System.out.println("Phong chieu: " + PhongChieu.MaPhong);
        System.out.println("So ghe: " + SoGhe);
        System.out.println("Loai ghe: " + LoaiGhe);
    }

}    