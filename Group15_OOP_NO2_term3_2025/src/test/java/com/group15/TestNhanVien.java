package com.group15;
import java.util.Scanner;

import Javaproject.src.models.NhanVien;

public class TestNhanVien {
    public static void main(String[] args){
        Scanner input =new Scanner(System.in);
        System.out.println("nhap vao ma nhan vien:");
        int MaNhanVien=input.nextInt();
        input.nextLine();
        System.out.println("nhap vao ten nhan vien:");
        String Ten =input.nextLine();
        System.out.println("nhap vao chuc vu cua nhan vien:");
        String ChucVu=input.nextLine();
        System.out.println("nhap vao ca lam cua nhan vien:");
        String CaLam= input.nextLine();
        System.out.println("nhap vao so dien thoai cua nhan vien:");
        String SoDienThoai=input.nextLine();
        System.out.println("nhap vao email cua nhan vien:");
        String Email=input.nextLine();
        NhanVien NV = new NhanVien(MaNhanVien,Ten,ChucVu,CaLam,SoDienThoai,Email);
        System.out.println("thong tin nhan vien:");
        NV.hienthithongtinnhanvien();
        input.close();
    }
}
