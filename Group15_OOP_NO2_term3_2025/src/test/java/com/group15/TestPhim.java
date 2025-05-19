package com.group15;

import java.util.Scanner;

import com.group15.models.Phim;

public class TestPhim {
    public static void main(String[] args){
        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Nhap vao ma phim:");
            int maPhim = input.nextInt();
            input.nextLine();

            System.out.println("Nhap vao ten phim:");
            String tenPhim = input.nextLine();

            System.out.println("Nhap vao ten dao dien:");
            String daoDien = input.nextLine();

            System.out.println("Nhap vao ngay cong chieu:");
            String ngayCongChieu = input.nextLine();

            System.out.println("Nhap vao the loai phim:");
            String theLoai = input.nextLine();
            
            System.out.println("Nhap vao thoi luong phim:");
            String thoiLuongPhim = input.nextLine();
            
            // Tạo đối tượng Phim
            Phim p = new Phim(maPhim, tenPhim, daoDien, ngayCongChieu, theLoai, thoiLuongPhim);
            
            System.out.println("Thong tin phim:");
            p.hienThiThongTinPhim();  // ✅ Đúng tên hàm
        }
    }
}