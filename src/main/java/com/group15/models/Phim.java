package com.group15.models;

public class Phim {
    private int maPhim;
    private String tenPhim;
    private String daoDien;
    private String ngayCongChieu;
    private String theLoai;
    private String thoiLuongPhim;

    // Constructor
    public Phim(int maPhim, String tenPhim, String daoDien, String ngayCongChieu, String theLoai, String thoiLuongPhim) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.daoDien = daoDien;
        this.ngayCongChieu = ngayCongChieu;
        this.theLoai = theLoai;
        this.thoiLuongPhim = thoiLuongPhim;
    }

    // Phương thức hiển thị thông tin phim
    public void hienThiThongTinPhim() {
        System.out.println("Mã phim: " + maPhim);
        System.out.println("Tên phim: " + tenPhim);
        System.out.println("Đạo diễn: " + daoDien);
        System.out.println("Ngày công chiếu: " + ngayCongChieu);
        System.out.println("Thể loại: " + theLoai);
        System.out.println("Thời lượng phim: " + thoiLuongPhim);
    }
}
