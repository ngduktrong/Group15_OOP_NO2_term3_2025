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
        // ✅ Kiểm tra mã suất chiếu
        if (suatChieuDao.getById(ve.getMaSuatChieu()) == null) {
            System.out.println("❌ Mã suất chiếu không tồn tại!");
            return false;
        }

        // ✅ Kiểm tra mã phòng
        if (phongChieuDao.getById(ve.getMaPhong()) == null) {
            System.out.println("❌ Mã phòng chiếu không tồn tại!");
            return false;
        }

        // ✅ Kiểm tra mã hóa đơn
        if (hoaDonDao.getById(ve.getMaHoaDon()) == null) {
            System.out.println("❌ Mã hóa đơn không tồn tại!");
            return false;
        }

        // ✅ Kiểm tra ghế có bị trùng không trong suất chiếu/phòng
        List<Ve> existing = veDao.getAll();
        for (Ve v : existing) {
            if (v.getMaSuatChieu() == ve.getMaSuatChieu()
                && v.getMaPhong() == ve.getMaPhong()
                && v.getSoGhe().equalsIgnoreCase(ve.getSoGhe())) {
                System.out.println("❌ Ghế này đã được đặt trong suất chiếu/phòng tương ứng.");
                return false;
            }
        }

        // ✅ Thêm vé
        veDao.create(ve);
        System.out.println("✅ Thêm vé thành công!");
        return true;
    }

    public boolean updateVe(Ve veMoi) {
        Ve veCu = veDao.getById(veMoi.getMaVe());

        if (veCu == null) {
            System.out.println("❌ Không tìm thấy vé để cập nhật!");
            return false;
        }

        // ❌ Không cho sửa mã suất chiếu
        if (veMoi.getMaSuatChieu() != veCu.getMaSuatChieu()) {
            System.out.println("❌ Không được phép sửa mã suất chiếu. Vui lòng hủy và tạo vé mới.");
            return false;
        }

        // ❌ Không được đổi ghế nếu đã thanh toán
        if (!veCu.getSoGhe().equalsIgnoreCase(veMoi.getSoGhe())
            && !"Chưa thanh toán".equalsIgnoreCase(veCu.getTrangThai())) {
            System.out.println("❌ Không thể đổi ghế vì vé đã thanh toán.");
            return false;
        }

        // ✅ Cho phép đổi trạng thái, có log
        if (!veCu.getTrangThai().equalsIgnoreCase(veMoi.getTrangThai())) {
            System.out.printf("📘 Trạng thái vé [%d] thay đổi: '%s' → '%s'%n",
                    veMoi.getMaVe(), veCu.getTrangThai(), veMoi.getTrangThai());
        }

        veDao.update(veMoi);
        System.out.println("✅ Cập nhật vé thành công!");
        return true;
    }

    public boolean deleteVe(int id) {
        if (veDao.getById(id) != null) {
            veDao.delete(id);
            System.out.println("✅ Xoá vé thành công!");
            return true;
        } else {
            System.out.println("❌ Không tìm thấy vé để xoá!");
            return false;
        }
    }
}
