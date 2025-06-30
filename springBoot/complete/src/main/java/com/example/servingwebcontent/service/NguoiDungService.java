package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.KhachHangDao;
import com.example.servingwebcontent.dao.NguoiDungDao;
import com.example.servingwebcontent.dao.NhanVienDao;
import com.example.servingwebcontent.dao.TaiKhoanDao;
import com.example.servingwebcontent.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungDao nguoiDungDao;

    // Lấy tất cả người dùng
    public List<NguoiDung> getAllNguoiDungs() {
        return nguoiDungDao.getAll();
    }

    // Lấy người dùng theo ID
    public NguoiDung getNguoiDungById(int id) {
        return nguoiDungDao.getByID(id);
    }

    // Tạo mới người dùng
    public void createNguoiDung(NguoiDung nguoiDung) {
        nguoiDungDao.creat(nguoiDung);
    }

    // Cập nhật người dùng
    public void updateNguoiDung(NguoiDung nguoiDung) {
        nguoiDungDao.update(nguoiDung);
    }

    @Autowired
    private TaiKhoanDao taiKhoanDao;

    @Autowired
    private KhachHangDao khachHangDao;

    @Autowired
    private NhanVienDao nhanVienDao;

    public void deleteNguoiDung(int id) {
    taiKhoanDao.deleteByMaNguoiDung(id); // 🔧 cần thêm hàm này trong DAO
    khachHangDao.delete(id);
    nhanVienDao.delete(id);
    nguoiDungDao.delete(id);
    }
    
}
