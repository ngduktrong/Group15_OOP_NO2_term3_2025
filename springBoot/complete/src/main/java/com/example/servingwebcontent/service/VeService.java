package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.*;
import com.example.servingwebcontent.models.Ve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeService {

    private final VeDao veDao;
    private final SuatChieuDao suatChieuDao;
    private final PhongChieuDao phongChieuDao;
    private final HoaDonDao hoaDonDao;

    @Autowired
    public VeService(VeDao veDao,
                     SuatChieuDao suatChieuDao,
                     PhongChieuDao phongChieuDao,
                     HoaDonDao hoaDonDao) {
        this.veDao = veDao;
        this.suatChieuDao = suatChieuDao;
        this.phongChieuDao = phongChieuDao;
        this.hoaDonDao = hoaDonDao;
    }

    public List<Ve> getAllVe() {
        return veDao.getAll();
    }

    public Ve getVeById(int id) {
        return veDao.getById(id);
    }

    public List<Ve> getVeByMaHoaDon(int maHoaDon) {
        return veDao.getVeByMaHoaDon(maHoaDon);
    }

    public boolean createVe(Ve ve) {
        // Kiểm tra khóa ngoại
        if (suatChieuDao.getById(ve.getMaSuatChieu()) == null) {
            System.out.println(" Mã suất chiếu không tồn tại!");
            return false;
        }

        if (phongChieuDao.getById(ve.getMaPhong()) == null) {
            System.out.println(" Mã phòng chiếu không tồn tại!");
            return false;
        }

        if (hoaDonDao.getById(ve.getMaHoaDon()) == null) {
            System.out.println(" Mã hóa đơn không tồn tại!");
            return false;
        }

        // Kiểm tra vé trùng
        List<Ve> existing = veDao.getAll();
        for (Ve v : existing) {
            if (v.getMaSuatChieu() == ve.getMaSuatChieu()
                    && v.getMaPhong() == ve.getMaPhong()
                    && v.getSoGhe().equalsIgnoreCase(ve.getSoGhe())
                    && !"Chưa thanh toán".equalsIgnoreCase(v.getTrangThai())) {
                System.out.println(" Ghế này đã được đặt.");
                return false;
            }
        }

        // Nếu hợp lệ → insert vé
        veDao.create(ve);
        System.out.println(" Thêm vé thành công!");
        return true;
    }

    public boolean updateVe(Ve veMoi) {
        Ve veCu = veDao.getById(veMoi.getMaVe());

        if (veCu == null) {
            System.out.println(" Không tìm thấy vé để cập nhật!");
            return false;
        }

        if (veMoi.getMaSuatChieu() != veCu.getMaSuatChieu()) {
            System.out.println(" Không được phép sửa mã suất chiếu. Vui lòng hủy và tạo vé mới.");
            return false;
        }

        if (!veCu.getSoGhe().equalsIgnoreCase(veMoi.getSoGhe())
                && !"Chưa thanh toán".equalsIgnoreCase(veCu.getTrangThai())) {
            System.out.println(" Không thể đổi ghế vì vé đã thanh toán.");
            return false;
        }

        if (!veCu.getTrangThai().equalsIgnoreCase(veMoi.getTrangThai())) {
            System.out.printf(" Trạng thái vé [%d] thay đổi: '%s' → '%s'%n",
                    veMoi.getMaVe(), veCu.getTrangThai(), veMoi.getTrangThai());
        }

        veDao.update(veMoi);
        System.out.println(" Cập nhật vé thành công!");
        return true;
    }

    public boolean deleteVe(int id) {
        if (veDao.getById(id) != null) {
            veDao.delete(id);
            System.out.println(" Xoá vé thành công!");
            return true;
        } else {
            System.out.println(" Không tìm thấy vé để xoá!");
            return false;
        }
    }

    public int getSoVeDaThanhToan() {
        return veDao.getSoVeDaThanhToan();
    }

    public boolean markVeAsPaid(int maVe) {
        boolean success = veDao.updateTrangThaiVeToPaid(maVe);
        if (success) {
            Ve ve = veDao.getById(maVe);
            if (ve != null && ve.getMaHoaDon() > 0) {
                hoaDonDao.capNhatNgayLapTuVe(ve.getMaHoaDon());
                System.out.println(" Đã cập nhật NgayLap cho hóa đơn: " + ve.getMaHoaDon());
            }
        } else {
            System.out.println(" Thanh toán thất bại!");
        }
        return success;
    }

    public List<String> getSoGheDaDat(int maSuatChieu) {
        return veDao.getSoGheDaDatBySuatChieu(maSuatChieu);
    }
}
