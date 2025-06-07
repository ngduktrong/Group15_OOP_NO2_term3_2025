package com.group15.Review;

import java.util.ArrayList;
import java.util.List;

import com.group15.models.HoaDon;
import com.group15.models.Ve;

public class VeList {

    public static List<Ve> layDanhSachVe(int maKhachHang) {
       
        List<HoaDon> hoaDons = new ArrayList<>();
        hoaDons.add(new HoaDon(1, 101, "2025-06-12", 150000));
        hoaDons.add(new HoaDon(2, 102, "2025-06-02", 75000));
        hoaDons.add(new HoaDon(3, 101, "2025-06-03", 85000));
        
        hoaDons.add(new HoaDon(4, 103, "2025-06-08", 90000));

        /*
          nếu qua ngày trong vé thì chỉ cần sửa ngày tháng năm trong hóa đơn và test trong hàm test là đc
         */
        List<Ve> allVes = new ArrayList<>();
        allVes.add(new Ve(1, 1001, 1, "A1", 1, 75000, "paid", "2025-06-11"));
        allVes.add(new Ve(2, 1002, 1, "A2", 2, 75000, "booked", "2025-06-15"));
        allVes.add(new Ve(3, 1003, 2, "B1", 3, 85000, "paid", "2025-06-12"));
        allVes.add(new Ve(4, 1004, 2, "B2", 2, 85000, "cancelled", "2025-06-13"));
        
        allVes.add(new Ve(5, 1005, 3, "C1", 4, 90000, "booked", "2025-06-17"));

       
        List<Integer> maHoaDons = new ArrayList<>();
        for (HoaDon hd : hoaDons) {
            if (hd.getMaKhachHang() == maKhachHang) {
                maHoaDons.add(hd.getMaHoaDon());
            }
        }

       
        List<Ve> danhSachVe = new ArrayList<>();
        for (Ve ve : allVes) {
            if (maHoaDons.contains(ve.getMaHoaDon())) {
                danhSachVe.add(ve);
            }
        }

        return danhSachVe;
    }
}
