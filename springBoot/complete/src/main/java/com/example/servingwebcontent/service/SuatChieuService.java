package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.SuatChieuDao;
import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class SuatChieuService {

    private final SuatChieuDao suatChieuDao;

    public SuatChieuService(SuatChieuDao suatChieuDao) {
        this.suatChieuDao = suatChieuDao;
    }

    public List<SuatChieu> getAllSuatChieu() {
        return suatChieuDao.getAll();
    }

    public SuatChieu getSuatChieuById(int id) {
        return suatChieuDao.getById(id);
    }

    public boolean createSuatChieu(SuatChieu sc) {
        if (phimExists(sc.getMaPhim()) && phongExists(sc.getMaPhong())) {
            suatChieuDao.create(sc);
            System.out.println("✅ Thêm suất chiếu thành công: " + sc.getMaPhim() + " | Phòng: " + sc.getMaPhong());
            return true;
        } else {
            System.out.println("❌ Không thể thêm suất chiếu: MaPhim hoặc MaPhong không tồn tại.");
            return false;
        }
    }

    public void updateSuatChieu(SuatChieu sc) {
        suatChieuDao.update(sc);
        System.out.println("✏️ Cập nhật suất chiếu: " + sc.getMaSuatChieu());
    }

    public void deleteSuatChieu(int id) {
        suatChieuDao.delete(id);
        System.out.println("🗑️ Đã xoá suất chiếu: " + id);
    }

    // Kiểm tra MaPhim có tồn tại
    private boolean phimExists(int maPhim) {
        String sql = "SELECT 1 FROM Phim WHERE MaPhim = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maPhim);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra MaPhong có tồn tại
    private boolean phongExists(int maPhong) {
        String sql = "SELECT 1 FROM PhongChieu WHERE MaPhong = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maPhong);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<SuatChieu> getByMaPhim(int maPhim) {
        return suatChieuDao.getByMaPhim(maPhim);
    }
    public List<SuatChieu> getByMaPhongAndPhim(int maPhong, int maPhim) {
        return suatChieuDao.getByMaPhongAndPhim(maPhong, maPhim);
    }
}
