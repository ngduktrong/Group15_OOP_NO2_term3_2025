package com.example.servingwebcontent.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

public class SuatChieu {
    private int maSuatChieu;
    private int maPhim;
    private int maPhong;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngayGioChieu;

    public int getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(int maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public LocalDateTime getNgayGioChieu() {
        return ngayGioChieu;
    }

    public void setNgayGioChieu(LocalDateTime ngayGioChieu) {
        this.ngayGioChieu = ngayGioChieu;
    }

    // ✅ Định dạng để hiển thị trong Thymeleaf
    public String getNgayGioChieuFormatted() {
        if (ngayGioChieu == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ngayGioChieu.format(formatter);
    }
}