package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.HoaDonDao;
import com.example.servingwebcontent.models.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonDao hoaDonDao;

    // Lấy tất cả hóa đơn
    public List<HoaDon> getAllHoaDon() {
        return hoaDonDao.getAll();
    }

    // Lấy hóa đơn theo ID
    public HoaDon getHoaDonById(int id) {
        return hoaDonDao.getById(id);
    }

    // Tạo mới hóa đơn
    public void createHoaDon(HoaDon hd) {
        hoaDonDao.create(hd);
    }

    // Cập nhật hóa đơn
    public void updateHoaDon(HoaDon hd) {
        hoaDonDao.update(hd);
    }

    // Xóa hóa đơn
    public void deleteHoaDon(int id) {
        hoaDonDao.delete(id);
    }
}
