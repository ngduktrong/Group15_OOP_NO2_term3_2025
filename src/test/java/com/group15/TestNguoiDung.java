package com.group15;

import com.group15.models.NguoiDung;

public class TestNguoiDung {
    public static void main(String[] args) {
        // Tạo đối tượng bằng constructor đầy đủ tham số
        NguoiDung nguoiDung2 = new NguoiDung(
                2,
                "Tran Thi B",
                "0987654321",
                "b@example.com",
                "NhanVien"
        );

        System.out.println("\nThong tin Nguoi Dung   2:");
        System.out.println(nguoiDung2);
    }
}