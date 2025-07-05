package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.KhachHangDao;
import com.example.servingwebcontent.models.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangDao khachHangDao;

    // ✅ Lấy tất cả khách hàng
    public List<KhachHang> getAllKhachHang() {
        return khachHangDao.getAll();
    }

    // ✅ Lấy theo ID
    public KhachHang getKhachHangById(int id) {
        return khachHangDao.getByID(id);
    }

    // ✅ Tạo mới khách hàng
    public boolean createKhachHang(KhachHang kh) {
        try {
            khachHangDao.create(kh);
            System.out.println("✅ Tạo khách hàng thành công.");
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Không thể thêm khách hàng: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("❌ Lỗi tạo khách hàng: " + e.getMessage());
            return false;
        }
    }

    // ✅ Cập nhật
    public boolean updateKhachHang(KhachHang kh) {
        try {
            khachHangDao.update(kh);
            System.out.println("✅ Cập nhật khách hàng thành công.");
            return true;
        } catch (Exception e) {
            System.err.println("❌ Lỗi cập nhật khách hàng: " + e.getMessage());
            return false;
        }
    }

    // ✅ Xóa
    public boolean deleteKhachHang(int id) {
        try {
            khachHangDao.delete(id);
            System.out.println("✅ Xóa khách hàng thành công.");
            return true;
        } catch (Exception e) {
            System.err.println("❌ Lỗi xóa khách hàng: " + e.getMessage());
            return false;
        }
    }
}
