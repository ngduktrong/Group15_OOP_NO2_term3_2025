package com.group15.Review;
import java.util.ArrayList;

import com.group15.models.PhongChieu;
public class PhongChieuReview {
    private ArrayList<PhongChieu> phongChieuList = new ArrayList<>();

    // Thêm Phòng Chiếu
    public ArrayList<PhongChieu> addPhongChieu(PhongChieu phongChieu) {
        phongChieuList.add(phongChieu);
        return phongChieuList;
    }
    // Sửa Phòng chiếu theo mã
    public ArrayList<PhongChieu> editPhongChieuById(int maPhong, PhongChieu newPhongChieuData) {
        for (int i = 0; i < phongChieuList.size(); i++) {
            if (phongChieuList.get(i).getMaPhong() == maPhong) {
                phongChieuList.set(i, newPhongChieuData);
                return phongChieuList;
            }
        }
        return phongChieuList; // Trả về danh sách, dù không tìm thấy để sửa
    }
    // Xóa Phòng chiếu theo mã
    public ArrayList<PhongChieu> deletePhongChieuById(int maPhong) {
        for (int i = 0; i < phongChieuList.size(); i++) {
            if (phongChieuList.get(i).getMaPhong() == maPhong) {
                phongChieuList.remove(i);
                break;
            }
        }
        return phongChieuList; // Trả về danh sách dù có xóa hay không
    }
    // In danh sách Phòng Chiếu
    public void printPhongChieuList() {
        if (phongChieuList.isEmpty()) {
            System.out.println("Danh sách phòng chiếu trống.");
            return;
        }
        for (PhongChieu phongChieu : phongChieuList) {
            System.out.println("Ma Phong: " + phongChieu.getMaPhong());
            System.out.println("Ten Phong: " + phongChieu.getTenPhong());
            System.out.println("So Luong Ghe: " + phongChieu.getSoLuongGhe());
            System.out.println("Loai Phong: " + phongChieu.getLoaiPhong());
            System.out.println("-------------------------");
        }
    }
}
