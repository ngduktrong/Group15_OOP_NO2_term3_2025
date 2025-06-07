package com.group15.Review;

import com.group15.models.Ve;
import com.group15.Review.VeList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;


public class KiemTraVeSapChieu {
    
    public static List<Ve> layVeSapChieu(int maKhachHang, int ngayTruoc) {
        List<Ve> danhSachVe = VeList.layDanhSachVe(maKhachHang);
        List<Ve> ketQua = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Ve ve : danhSachVe) {
            LocalDate ngayDat = LocalDate.parse(ve.getNgayDat(), formatter);
            long daysBetween = ChronoUnit.DAYS.between(today, ngayDat);
            if (daysBetween >= 0 && daysBetween <= ngayTruoc) {
                ketQua.add(ve);
            }
        }
        return ketQua;
    }
}