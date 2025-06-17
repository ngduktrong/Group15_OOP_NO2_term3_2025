package com.group15;

import java.util.List;
import java.util.Scanner;

import com.group15.Review.KiemTraVeSapChieu;
import com.group15.models.Ve;

public class TestKiemTraVeSapChieu {
    /*
    nếu muốn test thì hãy nhập mã khách hàng bất kì trong velist
    sau đó nhâp số lượng ngày muốn xem có vé nào sắp chiếu không
    ví dụ nhâp 2 thì sẽ hiện số lượng vé sắp chiếu trong 2 ngày tới
    nếu không có vé nào thì sẽ thông báo không có vé sắp chiếu trong khoảng thời gian đó
    ví dụ nhâp mã khach hàng 101 và số ngày là 2 thì sẽ hiện ra các vé sắp chiếu trong 2 ngày tới
    nếu không có vé nào thì sẽ thông báo không có vé nào sắp chiếu trong 2 ngày tới
    nếu ghi số ngày là 10 thì sẽ hiện 2 vé sắp chiếu trong 10 ngày tới kể từ ngày 6/7 

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma khach hang: ");
        int maKh = sc.nextInt();
        System.out.print("nhap so ngay : ");
        int ngay = sc.nextInt();
        
        List<Ve> ds = KiemTraVeSapChieu.layVeSapChieu(maKh, ngay);
        if (ds.isEmpty()) {
            System.out.println("khong co ve nao chieu trong " + ngay + " ngay toi.");
        } else {
            System.out.println("Cac ve sap chieu:");
            for (Ve ve : ds) {
                System.out.println(
                    "Ma ve: " + ve.getMaVe() +
                    ", Ma suat chieu: " + ve.getMaSuatChieu() +
                    ", So ghe: " + ve.getSoGhe() +
                    ", Ngay dat (tam lam ngay chieu): " + ve.getNgayDat()
                );
            }
        }
        sc.close();
    }
}

