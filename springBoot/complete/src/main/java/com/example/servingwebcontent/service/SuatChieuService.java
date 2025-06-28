package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.SuatChieuDao;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuatChieuService {

    @Autowired
    private SuatChieuDao suatChieuDao;

    public List<SuatChieu> getAllSuatChieu() {
        return suatChieuDao.getAll();
    }

    public SuatChieu getSuatChieuById(int id) {
        return suatChieuDao.getById(id);
    }

    public void createSuatChieu(SuatChieu s) {
        suatChieuDao.create(s);
    }

    public void updateSuatChieu(SuatChieu s) {
        suatChieuDao.update(s);
    }

    public void deleteSuatChieu(int id) {
        suatChieuDao.delete(id);
    }

    /** Lấy danh sách suất chiếu theo mã phòng */
    public List<SuatChieu> getByMaPhong(int maPhong) {
        return suatChieuDao.getByMaPhong(maPhong);
    }

    /** Lấy danh sách suất chiếu theo mã phim */
    public List<SuatChieu> getByMaPhim(int maPhim) {
        return suatChieuDao.getByMaPhim(maPhim);
    }

    /** Lấy danh sách suất chiếu theo cả mã phòng và mã phim */
    public List<SuatChieu> getByMaPhongAndPhim(int maPhong, int maPhim) {
        return suatChieuDao.getByMaPhongAndPhim(maPhong, maPhim);
    }
}
