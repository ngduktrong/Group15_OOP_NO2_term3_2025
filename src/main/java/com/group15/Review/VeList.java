package com.group15.Review;
import java.util.ArrayList;
import java.util.List;

import com.group15.models.HoaDon;
import com.group15.models.Ve;

public class VeList {

    public static List<Ve> layDanhSachVe(int maKhachHang) {
        // Danh sách giả lập hóa đơn
        List<HoaDon> hoaDons = new ArrayList<>();
        hoaDons.add(new HoaDon(1, 101, "2025-06-01", 150000));
        hoaDons.add(new HoaDon(2, 102, "2025-06-02", 75000));
        hoaDons.add(new HoaDon(3, 101, "2025-06-03", 85000));

        // Danh sách vé mẫu
        List<Ve> allVes = new ArrayList<>();
        allVes.add(new Ve(1, 1001, 1, "A1", 1, 75000, "paid", "2025-06-01"));
        allVes.add(new Ve(2, 1002, 1, "A2", 2, 75000, "booked", "2025-06-02"));
        allVes.add(new Ve(3, 1003, 2, "B1", 3, 85000, "paid", "2025-06-03"));
        allVes.add(new Ve(4, 1004, 2, "B2", 2, 85000, "cancelled", "2025-06-04"));

        // Tìm các mã hóa đơn thuộc về khách hàng cần tìm
        List<Integer> maHoaDons = new ArrayList<>();
        for (HoaDon hd : hoaDons) {
            if (hd.getMaKhachHang() == maKhachHang) {
                maHoaDons.add(hd.getMaHoaDon());
            }
        }

        // Lọc vé theo các mã hóa đơn
        List<Ve> danhSachVe = new ArrayList<>();
        for (Ve ve : allVes) {
            if (maHoaDons.contains(ve.getMaHoaDon())) {
                danhSachVe.add(ve);
            }
        }

        return danhSachVe;
    }
}
