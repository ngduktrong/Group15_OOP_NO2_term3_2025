package com.group15.Review;

import java.util.Scanner;

import com.group15.models.KhachHang;

public class KhachHangReview {
    public void nhapKhachHang() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Nhap vao ma khach hang:");
            int maKhachHang = input.nextInt();
            input.nextLine();
            System.out.println("Nhap vao ten khach hang:");
            String ten = input.nextLine();
            System.out.println("Nhap vao so dien thoai khach hang:");
            String soDienThoai = input.nextLine();
            System.out.println("Nhap vao email khach hang:");
            String email = input.nextLine();
            KhachHang kh = new KhachHang(maKhachHang, ten, soDienThoai, email);
            System.out.println("Thong tin khach hang:");
            kh.hienthithongtinkhachhang();
        }
    }
}