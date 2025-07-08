package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.TaiKhoanDao;
import com.example.servingwebcontent.models.TaiKhoan;
import com.example.servingwebcontent.models.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {

    private final TaiKhoanDao taiKhoanDao;
    private final KhachHangService khachHangService;

    @Autowired
    public TaiKhoanService(TaiKhoanDao taiKhoanDao, KhachHangService khachHangService) {
        this.taiKhoanDao = taiKhoanDao;
        this.khachHangService = khachHangService;
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanDao.getAll();
    }

    /**
     * Tạo tài khoản mới và tự động thêm bản ghi vào bảng KhachHang (nếu là user)
     */
    public boolean createTaiKhoan(TaiKhoan tk) {
        boolean inserted = taiKhoanDao.insert(tk);
        if (inserted && "user".equalsIgnoreCase(tk.getLoaiTaiKhoanAsString())) {
            // Thêm KhachHang từ MaNguoiDung
            KhachHang kh = new KhachHang();
            kh.setMaNguoiDung(tk.getMaNguoiDung());
            return khachHangService.createKhachHang(kh);
        }
        return inserted;
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

    //  Hàm thêm người dùng mặc định (vai trò: KhachHang)
    public int createNguoiDungMacDinh(String hoTen, String soDienThoai) {
        return taiKhoanDao.insertNguoiDungMacDinh(hoTen, soDienThoai);
    }
}
