package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.PhongChieuDao;
import com.example.servingwebcontent.models.PhongChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongChieuService {

    @Autowired
    private PhongChieuDao phongChieuDao;

    // Lấy tất cả phòng chiếu
    public List<PhongChieu> getAllPhongChieu() {
        return phongChieuDao.getAll();
    }

    // Lấy phòng chiếu theo ID
    public PhongChieu getPhongChieuById(int id) {
        return phongChieuDao.getById(id);
    }

    // Tạo mới phòng chiếu
    public void createPhongChieu(PhongChieu p) {
        phongChieuDao.create(p);
    }

    // Cập nhật phòng chiếu
    public void updatePhongChieu(PhongChieu p) {
        phongChieuDao.update(p);
    }

    // Xóa phòng chiếu
    public void deletePhongChieu(int id) {
        phongChieuDao.delete(id);
    }
}
