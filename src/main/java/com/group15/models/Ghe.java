package com.group15.models;
import com.group15.Review.PhongChieuReview;
import com.group15.models.PhongChieu;

public class Ghe{
    public int SoGhe;
    public PhongChieu PhongChieu;

    public Ghe(int soGhe, PhongChieu PhongChieu) {
        this.SoGhe = soGhe;
        this.PhongChieu = PhongChieu;

    }
    public void HienThiGhe(){
        System.out.println("Thong tin Ghe:");
        System.out.println("Ghe:"+ SoGhe);
        System.out.println("Phong Chieu:"+ PhongChieu.tenPhong);
    }

}