package com.example.servingwebcontent.Review;

import com.example.servingwebcontent.models.SuatChieu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuatChieuList {
    public static List<SuatChieu> layDanhSachSuatChieu() {
        List<SuatChieu> list = new ArrayList<>();
        // Thêm dữ liệu giả lập: maSuatChieu và LocalDateTime
        // Ví dụ ngày giờ chiếu thử
        list.add(new SuatChieu(1001, LocalDateTime.of(2025,6,18,14,0)));
        list.add(new SuatChieu(1002, LocalDateTime.of(2025,6,18,16,30)));
        list.add(new SuatChieu(1003, LocalDateTime.of(2025,6,18,20,0)));
        list.add(new SuatChieu(1004, LocalDateTime.of(2025,6,17,18,0)));
        list.add(new SuatChieu(1005, LocalDateTime.of(2025,6,18,9,0)));
        return list;
    }

    public static SuatChieu getById(int maSuatChieu) {
        for (SuatChieu sc : layDanhSachSuatChieu()) {
            if (sc.getMaSuatChieu() == maSuatChieu) {
                return sc;
            }
        }
        return null;
    }
}
