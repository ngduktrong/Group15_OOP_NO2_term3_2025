package com.group15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.group15.Review.SuatChieuReview;
import com.group15.models.SuatChieu;

public class TestSuatChieu {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quanlyrcp?useSSL=false&serverTimezone=UTC", "root", "15102005");
            SuatChieuReview service = new SuatChieuReview(conn);
            String ngay = "2025-05-21";
            List<SuatChieu> danhSach = service.laySuatChieuTheoNgay(ngay);

            System.out.println("Danh sach suat chieu ngay " + ngay + ":");
            for (SuatChieu sc : danhSach) {
                System.out.printf("Ma suat: %d | Ma phim: %d | Ma phong: %d | Thoi gian: %s\n",
                        sc.getMaSuatChieu(), sc.getMaPhim(), sc.getMaPhong(), sc.getNgayGioChieu());
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}