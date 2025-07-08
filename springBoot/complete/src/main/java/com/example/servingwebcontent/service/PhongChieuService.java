package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.PhongChieuDao;
import com.example.servingwebcontent.models.PhongChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongChieuService {

    private final PhongChieuDao phongChieuDao;

    @Autowired
    public PhongChieuService(PhongChieuDao phongChieuDao) {
        this.phongChieuDao = phongChieuDao;
    }

    public List<PhongChieu> getAllPhongChieu() {
        return phongChieuDao.getAll();
    }

    public PhongChieu getPhongChieuById(int id) {
        return phongChieuDao.getById(id);
    }

    public void createPhongChieu(PhongChieu phongChieu) {
        phongChieuDao.create(phongChieu);
    }

    public void updatePhongChieu(PhongChieu phongChieu) {
        phongChieuDao.update(phongChieu);
    }

    public void deletePhongChieu(int id) {
        phongChieuDao.delete(id);
    }

    //  Thêm kiểm tra tồn tại
    public boolean existsById(int id) {
        return phongChieuDao.getById(id) != null;
    }
}
