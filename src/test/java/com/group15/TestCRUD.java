package com.group15;
import com.group15.dao.VeDao;
import com.group15.dao.HoaDonDao;
import com.group15.dao.ChiTietHoaDonDao;
import com.group15.models.Ve;
import com.group15.models.HoaDon;
import com.group15.models.ChiTietHoaDon;

import java.util.List;

public class TestCRUD {
    public static void main(String[] args) {
        VeDao veDao = new VeDao();
        HoaDonDao hdDao = new HoaDonDao();
        ChiTietHoaDonDao ctDao = new ChiTietHoaDonDao();

        // 1. CREATE
        HoaDon hd = new HoaDon(1, 101, "2025-06-07", 500000.0);
        hdDao.insert(hd);
        System.out.println("Inserted HoaDon: " + hd);

        Ve ve = new Ve(1, 5, 2, "A10", 1, 100000.0, "booked", "2025-06-07");
        veDao.insert(ve);
        System.out.println("Inserted Ve: " + ve);

        ChiTietHoaDon ct = new ChiTietHoaDon(1, 1);
        ctDao.insert(ct);
        System.out.println("Inserted ChiTietHoaDon: MaHD=1, MaVe=1");

        // 2. READ
        System.out.println("Ve id=1 => " + veDao.getById(1));
        System.out.println("HoaDon id=1 => " + hdDao.getById(1));
        System.out.println("ChiTietHoaDon list => " + ctDao.getAll());

        // 3. UPDATE
        ve.setTrangThai("paid");
        veDao.update(ve);
        System.out.println("Updated Ve => " + veDao.getById(1));

        hd.setTongTien(550000.0);
        hdDao.update(hd);
        System.out.println("Updated HoaDon => " + hdDao.getById(1));

        ChiTietHoaDon ctNew = new ChiTietHoaDon(1, 1); // với ví dụ đơn giản, không đổi khóa
        ctDao.update(ct, ctNew);
        System.out.println("Updated ChiTietHoaDon => " + ctDao.getById(1, 1));

        // 4. DELETE
        ctDao.delete(1, 1);
        System.out.println("After delete ChiTietHoaDon => " + ctDao.getAll());

        veDao.delete(1);
        System.out.println("After delete Ve => " + veDao.getAll());

        hdDao.delete(1);
        System.out.println("After delete HoaDon => " + hdDao.getAll());
    }
}
