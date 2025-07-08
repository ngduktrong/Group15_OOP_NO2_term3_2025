package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.PhimDao;
import com.example.servingwebcontent.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhimService {

    private final PhimDao phimDao;

    @Autowired
    public PhimService(PhimDao phimDao) {
        this.phimDao = phimDao;
    }

    public List<Phim> getAllPhim() {
        return phimDao.getAll();
    }

    public Phim getPhimById(int id) {
        return phimDao.getById(id);
    }

    public boolean createPhim(Phim phim) {
        try {
            phimDao.create(phim);
            return true;
        } catch (Exception e) {
            System.out.println(" Lỗi tạo phim:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePhim(Phim phim) {
        try {
            phimDao.update(phim);
            return true;
        } catch (Exception e) {
            System.out.println(" Lỗi cập nhật phim:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePhim(int id) {
        try {
            phimDao.delete(id);
            return true;
        } catch (Exception e) {
            System.out.println(" Lỗi xóa phim:");
            e.printStackTrace();
            return false;
        }
    }
}
