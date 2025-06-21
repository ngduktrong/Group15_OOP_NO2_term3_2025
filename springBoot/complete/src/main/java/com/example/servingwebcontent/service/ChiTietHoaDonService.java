// ChiTietHoaDonService.java
package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.ChiTietHoaDonDao;
import com.example.servingwebcontent.models.ChiTietHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietHoaDonService {

    @Autowired
    private ChiTietHoaDonDao chiTietHoaDonDao;

    // Lấy tất cả chi tiết hóa đơn
    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        return chiTietHoaDonDao.getAll();
    }

    // Lấy chi tiết hóa đơn theo composite key
    public ChiTietHoaDon getChiTietHoaDon(int maHoaDon, int maVe) {
        return chiTietHoaDonDao.getById(maHoaDon, maVe);
    }

    // Tạo mới chi tiết hóa đơn
    public void createChiTietHoaDon(ChiTietHoaDon ct) {
        chiTietHoaDonDao.create(ct);
    }

    // Cập nhật chi tiết hóa đơn
    public void updateChiTietHoaDon(int oldMaHoaDon, int oldMaVe, ChiTietHoaDon ctNew) {
        chiTietHoaDonDao.update(oldMaHoaDon, oldMaVe, ctNew);
    }

    // Xóa chi tiết hóa đơn
    public void deleteChiTietHoaDon(int maHoaDon, int maVe) {
        chiTietHoaDonDao.delete(maHoaDon, maVe);
    }
}
