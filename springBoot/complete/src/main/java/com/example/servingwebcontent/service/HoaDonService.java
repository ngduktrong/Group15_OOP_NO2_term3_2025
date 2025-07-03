package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.HoaDonDao;
import com.example.servingwebcontent.dao.KhachHangDao;
import com.example.servingwebcontent.dao.NhanVienDao;
import com.example.servingwebcontent.models.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {

    private final HoaDonDao hoaDonDao;
    private final KhachHangDao khachHangDao;
    private final NhanVienDao nhanVienDao;

    @Autowired
    public HoaDonService(HoaDonDao hoaDonDao, KhachHangDao khachHangDao, NhanVienDao nhanVienDao) {
        this.hoaDonDao = hoaDonDao;
        this.khachHangDao = khachHangDao;
        this.nhanVienDao = nhanVienDao;
    }

    // ✅ Lấy toàn bộ hóa đơn
    public List<HoaDon> getAllHoaDon() {
        return hoaDonDao.getAll();
    }

    // ✅ Lấy hóa đơn theo ID
    public HoaDon getHoaDonById(int id) {
        return hoaDonDao.getById(id);
    }

    // ✅ Tạo hóa đơn mới sau khi đã thanh toán vé
    public boolean createHoaDon(HoaDon hoaDon) {
        try {
            if (!isValidKhachHang(hoaDon.getMaKhachHang())) {
                System.out.println("❌ Mã khách hàng không tồn tại!");
                return false;
            }

            if (!isValidNhanVien(hoaDon.getMaNhanVien())) {
                System.out.println("❌ Mã nhân viên không tồn tại!");
                return false;
            }

            if (hoaDon.getTongTien() < 0) {
                System.out.println("❌ Tổng tiền không được âm!");
                return false;
            }

            hoaDonDao.create(hoaDon);
            System.out.println("✅ Tạo hóa đơn thành công!");
            return true;

        } catch (Exception e) {
            System.out.println("❌ Tạo hóa đơn thất bại: " + e.getMessage());
            return false;
        }
    }

    // ✅ Cập nhật hóa đơn
    public boolean updateHoaDon(HoaDon hoaDon) {
        if (hoaDonDao.getById(hoaDon.getMaHoaDon()) == null) {
            System.out.println("❌ Không tìm thấy hóa đơn để cập nhật!");
            return false;
        }

        if (!isValidKhachHang(hoaDon.getMaKhachHang())) {
            System.out.println("❌ Mã khách hàng không tồn tại!");
            return false;
        }

        if (!isValidNhanVien(hoaDon.getMaNhanVien())) {
            System.out.println("❌ Mã nhân viên không tồn tại!");
            return false;
        }

        if (hoaDon.getTongTien() < 0) {
            System.out.println("❌ Tổng tiền không được âm!");
            return false;
        }

        hoaDonDao.update(hoaDon);
        System.out.println("✅ Cập nhật hóa đơn thành công!");
        return true;
    }

    // ✅ Xóa hóa đơn
    public boolean deleteHoaDon(int id) {
        if (hoaDonDao.getById(id) != null) {
            hoaDonDao.delete(id);
            System.out.println("✅ Xóa hóa đơn thành công!");
            return true;
        } else {
            System.out.println("❌ Không tìm thấy hóa đơn để xóa!");
            return false;
        }
    }

    // ✅ Tìm hóa đơn theo mã khách hàng
    public List<HoaDon> getHoaDonByMaKhachHang(int maKH) {
        return hoaDonDao.getByMaKhachHang(maKH);
    }

    // ✅ Tìm hóa đơn theo ngày lập
    public List<HoaDon> getHoaDonByNgayLap(String ngayLap) {
        return hoaDonDao.getByNgayLap(ngayLap);
    }

    // ✅ Tìm hóa đơn trong khoảng ngày lập
    public List<HoaDon> getHoaDonByKhoangNgay(String tuNgay, String denNgay) {
        return hoaDonDao.getByKhoangNgay(tuNgay, denNgay);
    }

    // ✅ Tính tổng doanh thu theo ngày
    public double getTongDoanhThuTheoNgay(String ngayLap) {
        return hoaDonDao.getTongDoanhThuTheoNgay(ngayLap);
    }

    // ✅ Tính tổng doanh thu theo khoảng ngày
    public double getTongDoanhThuTheoKhoangNgay(String tuNgay, String denNgay) {
        return hoaDonDao.getTongDoanhThuTheoKhoangNgay(tuNgay, denNgay);
    }

    // 🔍 Kiểm tra khách hàng có tồn tại
    private boolean isValidKhachHang(Integer maKH) {
        return maKH == null || khachHangDao.getByID(maKH) != null;
    }

    // 🔍 Kiểm tra nhân viên có tồn tại
    private boolean isValidNhanVien(Integer maNV) {
        return maNV == null || nhanVienDao.getById(maNV) != null;
    }
}
