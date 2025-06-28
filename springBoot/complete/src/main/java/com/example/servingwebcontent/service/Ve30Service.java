package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.VeDao;
import com.example.servingwebcontent.models.Ve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Ve30Service {

    @Autowired
    private VeDao veDAO;

    /**
     * Lấy danh sách vé của một khách hàng dựa vào mã khách hàng
     */
    public List<Ve> getVeListByKhachHang(int maKhachHang) {
        return veDAO.getVeByMaKhachHang(maKhachHang);
    }

    /**
     * Lọc ra các vé có suất chiếu bắt đầu trong vòng 30 phút tới
     */
    public List<Ve> kiemTraVeSapChieu(List<Ve> danhSachVe) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in30Min = now.plusMinutes(30);

        // Lọc các vé có ngày giờ chiếu nằm trong khoảng [now, now + 30 phút]
        return danhSachVe.stream()
                .filter(ve -> {
                    LocalDateTime gioChieu = ve.getNgayGioChieu();
                    return gioChieu != null &&
                           gioChieu.isAfter(now) &&
                           gioChieu.isBefore(in30Min);
                })
                .collect(Collectors.toList());
    }

}
