package com.example.servingwebcontent.models;

public class RapPhim {
    public int MaRap;
    public String TenRap;
    public String DiaChi;
    public String SoDienThoai;

    public RapPhim(int maRap, String tenRap, String diaChi, String soDienThoai) {
        MaRap = maRap;
        TenRap = tenRap;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
    }

    public void HienThiRapPhim() {
        System.out.println("Ma Rap: " + MaRap);
        System.out.println("Tan Rap: " + TenRap);
        System.out.println("Đia Chi: " + DiaChi);
        System.out.println("So Đien Thoai: " + SoDienThoai);
    }
}
