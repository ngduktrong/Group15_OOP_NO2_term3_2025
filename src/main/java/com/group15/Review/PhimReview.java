package com.group15.Review;
import java.util.ArrayList;

import com.group15.models.Phim;

public class PhimReview {
    private ArrayList<Phim> st = new ArrayList<>();
    // Thêm phim
    public ArrayList<Phim> addPhim(Phim phim) {
        st.add(phim);
        return st;
    }
    public Phim getPhimByMa(int maPhim) {
        for (Phim p : st) {
            if (p.getMaPhim() == maPhim) {
                return p;
            }
        }
        return null;
    }
    // Tìm phim theo tên và mã
    public Phim getPhimByTenVaMa(String tenPhim, int maPhim) {
        for (Phim p : st) {
            if (p.getTenPhim().equalsIgnoreCase(tenPhim) && p.getMaPhim() == maPhim) {
                return p;
            }
        }
        return null;
    }
    // Sửa phim theo mã phim
    public boolean editPhimById(int maPhim, Phim newPhimData) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaPhim() == maPhim) {
                // Cập nhật dữ liệu
                st.set(i, newPhimData);
                return true;
            }
        }
        return false; // Không tìm thấy phim để sửa
    }
    // Xóa phim theo mã
    public boolean deletePhimById(int maPhim) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaPhim() == maPhim) {
                st.remove(i);
                return true;
            }
        }
        return false;
    }
    // In danh sách phim
    public void printPhimList() {
        if (st.isEmpty()) {
            System.out.println("Danh sach phim trong.");
            return;
        }
        for (Phim phim : st) {
            System.out.println("Ma Phim: " + phim.getMaPhim());
            System.out.println("Ten Phim: " + phim.getTenPhim());
            System.out.println("Thoi Luong: " + phim.getThoiLuong());
            System.out.println("Ngay Khoi Chieu: " + phim.getNgayKhoiChieu());
            System.out.println("Nuoc San Xuat: " + phim.getNuocSanXuat());
            System.out.println("Dinh Dang: " + phim.getDinhDang());
            System.out.println("Mo Ta: " + phim.getMoTa());
            System.out.println("Dao Dien: " + phim.getDaoDien());
            System.out.println("Duong Dan Poster: " + phim.getDuongDanPoster());
            System.out.println("-----------------------------");
        }
    }
}
