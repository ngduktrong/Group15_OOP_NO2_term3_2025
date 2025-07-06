package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.TaiKhoanDao;
import com.example.servingwebcontent.models.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {

    private final TaiKhoanDao taiKhoanDao;

    @Autowired
    public TaiKhoanService(TaiKhoanDao taiKhoanDao) {
        this.taiKhoanDao = taiKhoanDao;
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanDao.getAll();
    }

    public boolean createTaiKhoan(TaiKhoan tk) {
        return taiKhoanDao.insert(tk);
    }

    public boolean updateTaiKhoan(TaiKhoan tk) {
        return taiKhoanDao.update(tk);
    }

    public boolean deleteTaiKhoan(String tenDangNhap) {
        return taiKhoanDao.delete(tenDangNhap);
    }

    public TaiKhoan getByUsername(String tenDangNhap) {
        return taiKhoanDao.getByUsername(tenDangNhap);
    }

    // ✅ Hàm thêm người dùng mặc định (KhachHang) với số điện thoại
    public int createNguoiDungMacDinh(String hoTen, String soDienThoai) {
        return taiKhoanDao.insertNguoiDungMacDinh(hoTen, soDienThoai);
    }
}
