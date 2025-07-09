package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.GheDao;
import com.example.servingwebcontent.dao.PhongChieuDao;
import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.models.PhongChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        List<Ghe> danhSach = gheDao.getAll();
        danhSach.sort(this::compareGhe);
        return danhSach;
    }

    public List<Ghe> getByMaPhong(int maPhong) {
        List<Ghe> danhSach = gheDao.getByMaPhong(maPhong);
        danhSach.sort(this::compareGhe);
        return danhSach;
    }

    public Ghe getGheById(String soGhe, int maPhong) {
        return gheDao.getById(soGhe, maPhong);
    }

    public boolean createGhe(Ghe ghe) {
        PhongChieu phong = phongChieuDao.getById(ghe.getMaPhong());
        if (phong == null) {
            System.err.println(" Mã phòng không hợp lệ!");
            return false;
        }

        List<Ghe> gheTrongPhong = gheDao.getByMaPhong(ghe.getMaPhong());
        if (gheTrongPhong.size() >= phong.getSoLuongGhe()) {
            System.err.println(" Không thể thêm ghế: số lượng ghế đã đạt tối đa!");
            return false;
        }

        Ghe gheTonTai = gheDao.getById(ghe.getSoGhe(), ghe.getMaPhong());
        if (gheTonTai != null) {
            System.err.println(" Ghế đã tồn tại trong phòng này!");
            return false;
        }

        gheDao.create(ghe);
        System.out.println(" Thêm ghế thành công: " + ghe.getSoGhe() + " (Phòng " + ghe.getMaPhong() + ")");
        return true;
    }

    public boolean updateGhe(Ghe ghe) {
        Ghe gheCu = gheDao.getById(ghe.getSoGhe(), ghe.getMaPhong());
        if (gheCu == null) {
            System.err.println(" Không tìm thấy ghế để cập nhật!");
            return false;
        }

        gheDao.update(ghe);
        System.out.println(" Cập nhật ghế thành công: " + ghe.getSoGhe());
        return true;
    }

    public boolean deleteGhe(String soGhe, int maPhong) {
        Ghe ghe = gheDao.getById(soGhe, maPhong);
        if (ghe == null) {
            System.err.println("Không tìm thấy ghế để xoá!");
            return false;
        }

        gheDao.delete(soGhe, maPhong);
        System.out.println(" Xoá ghế thành công: " + soGhe + " (Phòng " + maPhong + ")");
        return true;
    }

    public void createAuto(int maPhong, int soHang, int soGheMoiHang) {
        PhongChieu phong = phongChieuDao.getById(maPhong);
        if (phong == null) {
            System.err.println(" Mã phòng không hợp lệ khi tạo ghế hàng loạt!");
            return;
        }

        int gheToiDa = phong.getSoLuongGhe();
        List<Ghe> gheHienTai = gheDao.getByMaPhong(maPhong);
        int demThem = 0;

        for (char row = 'A'; row < 'A' + soHang; row++) {
            for (int num = 1; num <= soGheMoiHang; num++) {
                if (gheHienTai.size() + demThem >= gheToiDa) {
                    System.err.println(" Vượt quá số lượng ghế tối đa của phòng!");
                    return;
                }

                String soGhe = row + String.valueOf(num);
                Ghe gheMoi = new Ghe(soGhe, maPhong);

                if (gheDao.getById(soGhe, maPhong) == null) {
                    gheDao.create(gheMoi);
                    demThem++;
                } else {
                    System.out.println(" Bỏ qua ghế đã tồn tại: " + soGhe);
                }
            }
        }

        System.out.println(" Đã thêm " + demThem + " ghế cho phòng " + maPhong);
    }

    public void delete(String soGhe) {
        List<Ghe> gheList = gheDao.getAll();
        for (Ghe g : gheList) {
            if (g.getSoGhe().equals(soGhe)) {
                gheDao.delete(soGhe, g.getMaPhong());
                System.out.println(" Xoá ghế thành công: " + soGhe);
                return;
            }
        }
        System.err.println(" Không tìm thấy ghế để xoá: " + soGhe);
    }

    // So sánh ghế theo maPhong -> soGhe (tự nhiên)
    private int compareGhe(Ghe g1, Ghe g2) {
        int cmpPhong = Integer.compare(g1.getMaPhong(), g2.getMaPhong());
        if (cmpPhong != 0) return cmpPhong;

        // So sánh mã ghế (A1 < A2 < A10)
        String p1 = g1.getSoGhe().replaceAll("\\d", "");
        String p2 = g2.getSoGhe().replaceAll("\\d", "");
        int num1 = Integer.parseInt(g1.getSoGhe().replaceAll("\\D", ""));
        int num2 = Integer.parseInt(g2.getSoGhe().replaceAll("\\D", ""));

        int cmpRow = p1.compareTo(p2);
        return (cmpRow != 0) ? cmpRow : Integer.compare(num1, num2);
    }
}
