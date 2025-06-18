package com.example.servingwebcontent.Review;
import java.util.ArrayList;
import com.example.servingwebcontent.models.PhongChieu;
public class PhongChieuReview {
    private ArrayList<PhongChieu> phongChieuList = new ArrayList<>();

    
    public ArrayList<PhongChieu> addPhongChieu(PhongChieu phongChieu) {
        phongChieuList.add(phongChieu);
        return phongChieuList;
    }
    
    public ArrayList<PhongChieu> editPhongChieuById(int maPhong, PhongChieu newPhongChieuData) {
        for (int i = 0; i < phongChieuList.size(); i++) {
            if (phongChieuList.get(i).getMaPhong() == maPhong) {
                phongChieuList.set(i, newPhongChieuData);
                return phongChieuList;
            }
        }
        return phongChieuList; 
    }
    
    public ArrayList<PhongChieu> deletePhongChieuById(int maPhong) {
        for (int i = 0; i < phongChieuList.size(); i++) {
            if (phongChieuList.get(i).getMaPhong() == maPhong) {
                phongChieuList.remove(i);
                break;
            }
        }
        return phongChieuList; 
    }
    
    public void printPhongChieuList() {
        if (phongChieuList.isEmpty()) {
            System.out.println("Danh sach phong chieu trong.");
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
