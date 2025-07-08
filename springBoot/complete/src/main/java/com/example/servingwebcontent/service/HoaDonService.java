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

    public List<HoaDon> getAllHoaDon() {
        return hoaDonDao.getAll();
    }

    public HoaDon getHoaDonById(int id) {
        return hoaDonDao.getById(id);
    }

    //  Tạo hóa đơn mới → trả về mã hóa đơn
    public int createHoaDon(HoaDon hoaDon) {
        try {
            if (hoaDon.getTongTien() < 0) {
                System.out.println(" Tổng tiền không được âm!");
                return -1;
            }

            if (!isValidKhachHang(hoaDon.getMaKhachHang())) {
                System.out.println(" Mã khách hàng không tồn tại!");
                return -1;
            }

            if (!isValidNhanVien(hoaDon.getMaNhanVien())) {
                System.out.println(" Mã nhân viên không tồn tại!");
                return -1;
            }

            int maHoaDon = hoaDonDao.createHoaDon(hoaDon);
            if (maHoaDon != -1) {
                System.out.println("Tạo hóa đơn thành công!");
                return maHoaDon;
            } else {
                System.out.println(" Không tạo được hóa đơn!");
                return -1;
            }

        } catch (Exception e) {
            System.out.println(" Tạo hóa đơn thất bại: " + e.getMessage());
            return -1;
        }
    }

    public boolean updateHoaDon(HoaDon hoaDon) {
        if (hoaDonDao.getById(hoaDon.getMaHoaDon()) == null) {
            System.out.println(" Không tìm thấy hóa đơn để cập nhật!");
            return false;
        }

        if (hoaDon.getTongTien() < 0) {
            System.out.println(" Tổng tiền không được âm!");
            return false;
        }

        if (!isValidKhachHang(hoaDon.getMaKhachHang())) {
            System.out.println(" Mã khách hàng không tồn tại!");
            return false;
        }

        if (!isValidNhanVien(hoaDon.getMaNhanVien())) {
            System.out.println(" Mã nhân viên không tồn tại!");
            return false;
        }

        hoaDonDao.update(hoaDon);
        capNhatNgayLapTuVe(hoaDon.getMaHoaDon());

        System.out.println(" Cập nhật hóa đơn thành công!");
        return true;
    }

    public boolean deleteHoaDon(int id) {
        if (hoaDonDao.getById(id) != null) {
            hoaDonDao.delete(id);
            System.out.println(" Xóa hóa đơn thành công!");
            return true;
        } else {
            System.out.println(" Không tìm thấy hóa đơn để xóa!");
            return false;
        }
    }

    public List<HoaDon> getHoaDonByMaKhachHang(int maKH) {
        return hoaDonDao.getByMaKhachHang(maKH);
    }

    public List<HoaDon> getHoaDonByNgayLap(String ngayLap) {
        return hoaDonDao.getByNgayLap(ngayLap);
    }

    public List<HoaDon> getHoaDonByKhoangNgay(String tuNgay, String denNgay) {
        return hoaDonDao.getByKhoangNgay(tuNgay, denNgay);
    }

    public double getTongDoanhThuTheoNgay(String ngayLap) {
        return hoaDonDao.getTongDoanhThuTheoNgay(ngayLap);
    }

    public double getTongDoanhThuTheoKhoangNgay(String tuNgay, String denNgay) {
        return hoaDonDao.getTongDoanhThuTheoKhoangNgay(tuNgay, denNgay);
    }

    //  Gọi từ controller khi vé đã thanh toán
    public void capNhatNgayLapTuVe(int maHoaDon) {
        hoaDonDao.capNhatNgayLapTuVe(maHoaDon);
    }

    private boolean isValidKhachHang(Integer maKH) {
        return maKH == null || khachHangDao.getByID(maKH) != null;
    }

    private boolean isValidNhanVien(Integer maNV) {
        return maNV == null || nhanVienDao.getById(maNV) != null;
    }
}
