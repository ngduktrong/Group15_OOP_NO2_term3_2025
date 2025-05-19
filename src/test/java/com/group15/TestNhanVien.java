package com.group15;
import java.util.Scanner;

import com.group15.models.NhanVien;

public class TestNhanVien {
    public static void main(String[] args){
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Nhap vao ma nhan vien:");
            int MaNhanVien = input.nextInt();
            input.nextLine();
            System.out.println("Nhap vao ten nhan vien:");
            String Ten = input.nextLine();
            System.out.println("Nhap vao chuc vu cua nhan vien:");
            String ChucVu = input.nextLine();
            System.out.println("Nhap vao ca lam cua nhan vien:");
            String CaLam = input.nextLine();
            System.out.println("Nhap vao so dien thoai cua nhan vien:");
            String SoDienThoai = input.nextLine();
            System.out.println("Nhap vao email cua nhan vien:");
            String Email = input.nextLine();
            NhanVien NV = new NhanVien(MaNhanVien, Ten, ChucVu, CaLam, SoDienThoai, Email);
            System.out.println("Thong tin nhan vien:");
            NV.hienthithongtinnhanvien();
        }
    }
}