package com.example.servingwebcontent.service;


import com.example.servingwebcontent.Review.VeList;
import com.example.servingwebcontent.Review.SuatChieuList;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VeService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<Ve> getVeListByKhachHang(int maKhachHang) {
        List<Ve> danhSach = VeList.layDanhSachVe(maKhachHang);
        for (Ve ve : danhSach) {
            SuatChieu sc = SuatChieuList.getById(ve.getMaSuatChieu());
            if (sc != null && sc.getNgayGioChieu() != null) {
                ve.setNgayGioChieu(sc.getNgayGioChieu().format(formatter));
            } else {
                ve.setNgayGioChieu("N/A");
            }
        }
        return danhSach;
    }

    public List<Ve> kiemTraVeSapChieu(List<Ve> danhSachVe) {
        List<Ve> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = now.plusMinutes(30);
        for (Ve ve : danhSachVe) {
            SuatChieu sc = SuatChieuList.getById(ve.getMaSuatChieu());
            if (sc == null || sc.getNgayGioChieu() == null) continue;
            LocalDateTime ngayGio = sc.getNgayGioChieu();
            if (!ngayGio.isBefore(now) && !ngayGio.isAfter(limit)) {
                // Gán ngayGioChieu nếu cần (đã gán ở trên)
                result.add(ve);
            }
        }
        return result;
    }
}
