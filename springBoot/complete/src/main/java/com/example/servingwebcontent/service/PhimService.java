package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.PhimDao;
import com.example.servingwebcontent.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhimService {

    @Autowired
    private PhimDao phimDao;

    // Lấy tất cả phim
    public List<Phim> getAllPhim() {
        return phimDao.getAll();
    }

    // Lấy phim theo ID
    public Phim getPhimById(int id) {
        return phimDao.getById(id);
    }

    // Tạo mới phim
    public void createPhim(Phim phim) {
        phimDao.create(phim);
    }

    // Cập nhật phim
    public void updatePhim(Phim phim) {
        phimDao.update(phim);
    }

    // Xoá phim
    public void deletePhim(int id) {
        phimDao.delete(id);
    }
}
