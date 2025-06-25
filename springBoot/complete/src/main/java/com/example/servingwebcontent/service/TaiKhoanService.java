package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.TaiKhoanDao;
import com.example.servingwebcontent.models.TaiKhoan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {

    private final TaiKhoanDao taiKhoanDao;

    public TaiKhoanService(TaiKhoanDao taiKhoanDao) {
        this.taiKhoanDao = taiKhoanDao;
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanDao.getAll();
    }

    public TaiKhoan getTaiKhoanByUsername(String tenDangNhap) {
        return taiKhoanDao.getByUsername(tenDangNhap);
    }

    public boolean createTaiKhoan(TaiKhoan taiKhoan) {
        return taiKhoanDao.create(taiKhoan);
    }

    public void updateTaiKhoan(TaiKhoan taiKhoan) {
        taiKhoanDao.update(taiKhoan);
    }

    public void deleteTaiKhoan(String tenDangNhap) {
        taiKhoanDao.delete(tenDangNhap);
    }

    public boolean isUsernameExists(String tenDangNhap) {
        return taiKhoanDao.existsByUsername(tenDangNhap);
    }
}
