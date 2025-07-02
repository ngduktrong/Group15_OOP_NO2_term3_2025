package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.GheDao;
import com.example.servingwebcontent.dao.PhongChieuDao;
import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.models.PhongChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GheService {

    private final GheDao gheDao;
    private final PhongChieuDao phongChieuDao;

    @Autowired
    public GheService(GheDao gheDao, PhongChieuDao phongChieuDao) {
        this.gheDao = gheDao;
        this.phongChieuDao = phongChieuDao;
    }

    public List<Ghe> getAllGhe() {
        return gheDao.getAll();
    }

    public Ghe getGheById(String soGhe, int maPhong) {
        return gheDao.getById(soGhe, maPhong);
    }

    public boolean createGhe(Ghe ghe) {
        PhongChieu phong = phongChieuDao.getById(ghe.getMaPhong());

        // Kiểm tra mã phòng có tồn tại không
        if (phong == null) {
            System.err.println("❌ Mã phòng không hợp lệ!");
            return false;
        }

        // Kiểm tra số ghế hiện tại
        List<Ghe> gheTrongPhong = gheDao.getByMaPhong(ghe.getMaPhong());
        if (gheTrongPhong.size() >= phong.getSoLuongGhe()) {
            System.err.println("❌ Không thể thêm ghế: số lượng ghế đã đạt tối đa!");
            return false;
        }

        // Kiểm tra trùng số ghế
        Ghe gheTonTai = gheDao.getById(ghe.getSoGhe(), ghe.getMaPhong());
        if (gheTonTai != null) {
            System.err.println("❌ Ghế đã tồn tại trong phòng này!");
            return false;
        }

        gheDao.create(ghe);
        System.out.println("✅ Thêm ghế thành công: " + ghe.getSoGhe() + " (Phòng " + ghe.getMaPhong() + ")");
        return true;
    }

    public boolean updateGhe(Ghe ghe) {
        Ghe gheCu = gheDao.getById(ghe.getSoGhe(), ghe.getMaPhong());
        if (gheCu == null) {
            System.err.println("❌ Không tìm thấy ghế để cập nhật!");
            return false;
        }

        gheDao.update(ghe);
        System.out.println("✅ Cập nhật ghế thành công: " + ghe.getSoGhe());
        return true;
    }

    public boolean deleteGhe(String soGhe, int maPhong) {
        Ghe ghe = gheDao.getById(soGhe, maPhong);
        if (ghe == null) {
            System.err.println("❌ Không tìm thấy ghế để xoá!");
            return false;
        }

        gheDao.delete(soGhe, maPhong);
        System.out.println("✅ Xoá ghế thành công: " + soGhe + " (Phòng " + maPhong + ")");
        return true;
    }

    public List<Ghe> getByMaPhong(int maPhong) {
        return gheDao.getByMaPhong(maPhong);
    }
}
