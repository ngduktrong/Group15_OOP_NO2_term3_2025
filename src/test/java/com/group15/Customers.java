package com.group15;

import java.util.Scanner;

import com.group15.models.Customer;

public class Customers {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhap ma khach hang: ");
            int ma = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhap ten khach hang: ");
            String ten = scanner.nextLine();

            System.out.print("Nhap so đien thoai: ");
            String sdt = scanner.nextLine();

            System.out.print("Nhap email: ");
            String email = scanner.nextLine();
            Customer kh = new Customer(ma, ten, sdt, email);
            System.out.println("\n--- Thong tin khach hang ---");
            kh.hienThiThongTin();
        }
    }
}