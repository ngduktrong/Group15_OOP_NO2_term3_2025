package com.group15.Review;

import java.util.Scanner;

import com.group15.models.NhanVien;

public class NhanVienReview {
    public void nhapNhanVien() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Nhap vao ma nhan vien:");
            int maNhanVien = input.nextInt();
            input.nextLine();
            System.out.println("Nhap vao ten nhan vien:");
            String ten = input.nextLine();
            System.out.println("Nhap vao chuc vu cua nhan vien:");
            String chucVu = input.nextLine();
            System.out.println("Nhap vao ca lam cua nhan vien:");
            String caLam = input.nextLine();
            System.out.println("Nhap vao so dien thoai cua nhan vien:");
            String soDienThoai = input.nextLine();
            System.out.println("Nhap vao email cua nhan vien:");
            String email = input.nextLine();
            NhanVien nv = new NhanVien(maNhanVien, ten, chucVu, caLam, soDienThoai, email);
            System.out.println("Thong tin nhan vien:");
            nv.hienthithongtinnhanvien();
        }
    }
}