package com.group15;
import java.util.Scanner;

import com.group15.models.KhachHang;

public class TestKhachHang {
    public static void main(String[] args){
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Nhap vao ma khach hang:");
            int MaKhachHang = input.nextInt();
            input.nextLine();
            System.out.println("Nhap vao ten khach hang:");
            String Ten = input.nextLine();
            System.out.println("Nhap vao so dien thoai khach hang:");
            String SoDienThoai = input.nextLine();
            System.out.println("Nhap vao email khach hang:");
            String Email = input.nextLine();
            KhachHang KH = new KhachHang(MaKhachHang, Ten, SoDienThoai, Email);
            System.out.println("Thong tin khach hang:");
            KH.hienthithongtinkhachhang(); 
        }
    }
}