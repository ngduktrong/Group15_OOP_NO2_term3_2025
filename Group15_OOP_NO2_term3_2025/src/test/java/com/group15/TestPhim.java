package com.group15;
import Javaproject.src.models.Phim;
import java.util.Scanner;

public class TestPhim {
    public static void main(String[] args){
        Scanner input =new Scanner(System.in);
        System.out.println("Nhap vao ma phim:");
        int MaPhim =input.nextInt();
        input.nextLine();
        System.out.println("Nhap vao ten phim:");
        String TenPhim =input.nextLine();

        System.out.println("nhap vao ten dao dien:");
        String DaoDien=input.nextLine();

        System.out.println("nhap vao ngay cong chieu:");
        String NgayCongChieu=input.nextLine();

        System.out.println("Nhap vao the loai phim:");
        String TheLoai=input.nextLine();
        
        System.out.println("Nhap vao thoi luong phim:");
        String ThoiLuongPhim=input.nextLine();
        
        
        Phim P = new Phim(MaPhim,TenPhim,DaoDien,NgayCongChieu,TheLoai,ThoiLuongPhim);
        
        System.out.println("thong tin phim:");
        P.hienthithongtinphim();
        
        input.close();
    }
}
