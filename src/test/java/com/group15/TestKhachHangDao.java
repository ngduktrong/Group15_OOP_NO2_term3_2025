package com.group15;
import com.group15.dao.KhachHangDao;
import com.group15.models.KhachHang;

public class TestKhachHangDao {
    public static void main(String[] args) {
        KhachHangDao khachHangDao = new KhachHangDao();

        // Thêm khách hàng vào danh sách
        KhachHang kh1 = new KhachHang(3, 100);
        KhachHang kh2 = new KhachHang(4, 200);
        khachHangDao.add(kh1);
        khachHangDao.add(kh2);

        // In danh sách khách hàng
        khachHangDao.getAll().forEach(System.out::println);
        khachHangDao.printAll();
    }
}