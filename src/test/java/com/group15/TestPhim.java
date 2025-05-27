package com.group15;

import java.util.Scanner;

import com.group15.Review.PhimReview;
import com.group15.models.Phim;

public class TestPhim {
    PhimReview pr = new PhimReview(); 

    public void addSamplePhim() {
        Phim p1 = new Phim("Avengers: Endgame", 1001, 181, "2019-04-26", "USA", "IMAX", "Superhero movie", "Anthony Russo", "endgame.jpg");
        Phim p2 = new Phim("Parasite", 1002, 132, "2019-05-30", "Korea", "2D", "Drama thriller", "Bong Joon-ho", "parasite.jpg");
        Phim p3 = new Phim("Inception", 1003, 148, "2010-07-16", "USA", "IMAX", "Sci-fi thriller", "Christopher Nolan", "inception.jpg");

        pr.addPhim(p1);
        pr.addPhim(p2);
        pr.addPhim(p3);
    }

    public void addPhimTuNguoiDung() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong phim muon them: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("=== Nhap phim thu " + (i + 1) + " ===");
            System.out.print("Ten phim: ");
            String tenPhim = scanner.nextLine();
            System.out.print("Ma phim: ");
            int maPhim = scanner.nextInt(); scanner.nextLine();
            System.out.print("Thoi luong: ");
            int thoiLuong = scanner.nextInt(); scanner.nextLine();
            System.out.print("Ngay khoi chieu: ");
            String ngayKC = scanner.nextLine();
            System.out.print("Nuoc san xuat: ");
            String nuocSX = scanner.nextLine();
            System.out.print("Dinh Dang: ");
            String dinhDang = scanner.nextLine();
            System.out.print("Mo ta: ");
            String moTa = scanner.nextLine();
            System.out.print("Dao dien: ");
            String daoDien = scanner.nextLine();
            System.out.print("Poster: ");
            String poster = scanner.nextLine();

            Phim phimMoi = new Phim(tenPhim, maPhim, thoiLuong, ngayKC, nuocSX, dinhDang, moTa, daoDien, poster);
            pr.addPhim(phimMoi);
        }

        System.out.println("=== Danh sach sau khi them ===");
        pr.printPhimList();
    }

    public void findPhim() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Danh sach phim hien tai ===");
        pr.printPhimList();
        System.out.println("\n--- Tim Phim ---");
        System.out.print("Nhap ma phim muon tim: ");
        int maPhim = scanner.nextInt();
        scanner.nextLine();
        Phim phimTim = pr.getPhimByMa(maPhim);
        if (phimTim == null) {
            System.out.println("Khong tim thay phim voi ma: " + maPhim);
        } else {
            System.out.println("Da tim thay phim:");
            System.out.println(phimTim);
        }
    }

    public void testEditDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Danh sach phim hien tai ===");
        pr.printPhimList();
        System.out.println("\n--- Sua Phim ---");
        System.out.print("Nhap ma phim muon sua: ");
        int maPhim = scanner.nextInt();
        scanner.nextLine();
        Phim phimOld = pr.getPhimByMa(maPhim);
        if (phimOld == null) {
            System.out.println("Khong tim thay phim.");
        } else {
            System.out.print("Nhap ten phim moi: ");
            String tenMoi = scanner.nextLine();
            System.out.print("Thoi Luong phim moi: ");
            int thoiLuong = scanner.nextInt(); scanner.nextLine();
            System.out.print("Ngay Khoi Chieu Moi: ");
            String ngayKC = scanner.nextLine();
            System.out.print("Nuoc san xuat moi: ");
            String nuocSX = scanner.nextLine();
            System.out.print("Dinh dang moi: ");
            String dinhDang = scanner.nextLine();
            System.out.print("Mo ta moi: ");
            String moTa = scanner.nextLine();
            System.out.print("Dao dien moi: ");
            String daoDien = scanner.nextLine();
            System.out.print("Poster moi: ");
            String poster = scanner.nextLine();
            Phim phimMoi = new Phim(tenMoi, maPhim, thoiLuong, ngayKC, nuocSX, dinhDang, moTa, daoDien, poster);
            pr.deletePhimById(maPhim);
            pr.addPhim(phimMoi);
        }

        System.out.println("\nDanh sach phim sau khi sua:");
        pr.printPhimList();

        System.out.println("\n--- Xoa Phim ---");
        System.out.print("Nhap ma phim muon xoa: ");
        int maPhimXoa = scanner.nextInt();
        pr.deletePhimById(maPhimXoa);

        System.out.println("\nDanh sach phim sau khi xoa:");
        pr.printPhimList();
    }

    public static void main(String[] args) {
        TestPhim test = new TestPhim();
        test.addSamplePhim(); 
        test.addPhimTuNguoiDung(); 
        test.findPhim(); 
        test.testEditDelete(); 
    }
}
