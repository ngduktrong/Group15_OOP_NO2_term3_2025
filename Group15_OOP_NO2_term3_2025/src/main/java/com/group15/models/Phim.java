package com.group15.models;

import java.time.LocalDate;

public class Phim {
    public int MaPhim;
    public String TenPhim;
    public String DaoDien;
    public LocalDate NgayCongChieu;
    public String TheLoai;
    public String ThoiLuong;

    public Phim(int MaPhim, String TenPhim, String DaoDien, LocalDate NgayCongChieu, String TheLoai, String ThoiLuong) {
        this.MaPhim=MaPhim;
        this.TenPhim=TenPhim;
        this.DaoDien=DaoDien;
        this.NgayCongChieu=NgayCongChieu;
        this.TheLoai=TheLoai;
        this.ThoiLuong=ThoiLuong;
    }

    public void hienthithongtinphim() {
        System.out.println("Ma Phim: "+MaPhim);
        System.out.println("Ten Phim: "+TenPhim);
        System.out.println("Dao Dien: "+DaoDien);
        System.out.println("Ngay Cong Chieu: "+NgayCongChieu);
        System.out.println("The Loai: "+TheLoai);
        System.out.println("Thoi Luong: "+ThoiLuong);
    }
}
