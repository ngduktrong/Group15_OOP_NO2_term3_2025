package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.SuatChieuDao;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuatChieuService {

    @Autowired
    private SuatChieuDao suatChieuDao;

    // Lấy tất cả suất chiếu
    public List<SuatChieu> getAllSuatChieu() {
        return suatChieuDao.getAll();
    }

    // Lấy suất chiếu theo ID
    public SuatChieu getSuatChieuById(int id) {
        return suatChieuDao.getById(id);
    }

    // Tạo mới suất chiếu
    public void createSuatChieu(SuatChieu s) {
        suatChieuDao.create(s);
    }

    // Cập nhật suất chiếu
    public void updateSuatChieu(SuatChieu s) {
        suatChieuDao.update(s);
    }

    // Xóa suất chiếu
    public void deleteSuatChieu(int id) {
        suatChieuDao.delete(id);
    }
}
