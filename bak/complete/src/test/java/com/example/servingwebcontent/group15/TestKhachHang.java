package com.example.servingwebcontent.group15;

import java.util.Scanner;
import java.util.ArrayList;

import com.group15.models.KhachHang;
import com.group15.Review.KhachHangReview;

public class TestKhachHang {

    KhachHangReview KH = new KhachHangReview();

    public void addKhachHang(){
        KhachHang kh1 = new KhachHang(1, "Nguyen Van An", "0123456789", "an@gmail.com");
        KhachHang kh2 = new KhachHang(2, "Nguyen Thi Van", "0123456788", "van@gmail.com");
        KhachHang kh3 = new KhachHang(3, "Nguyen Kieu Trinh", "0123456787", "trinh@gmail.com");

        KH.addKhachHang(kh1);
        KH.addKhachHang(kh2);
        KH.addKhachHang(kh3);
        KH.HienThiKhachHang();
    }

    public void testEditKhachHang(){
        Scanner input = new Scanner(System.in);

        System.out.println("Nhap vao ma khach hang can sua:");
        int maKhachHang = input.nextInt();
        input.nextLine();  

        System.out.println("Nhap vao ten khach hang moi:");
        String tenMoi = input.nextLine();
        System.out.println("Nhap vao so dien thoai moi:");
        String soDienThoaiMoi = input.nextLine();
        System.out.println("Nhap email moi:");
        String emailMoi = input.nextLine();

        boolean thanhCong = KH.editKhachHang(maKhachHang, tenMoi, soDienThoaiMoi, emailMoi);
        if (thanhCong) {
            System.out.println("Sua thanh cong.");
        } else {
            System.out.println("Khong tim thay khach hang co ma " + maKhachHang);
        }

        KH.HienThiKhachHang();
    }

    public void testDeleteKhachHang(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap vao ma khach hang can xoa:");
        int maKhachHang = input.nextInt();

        int sizeBefore = KH.getKhachHangList().size();
        ArrayList<KhachHang> updatedList = KH.getDeleteKhachHang(maKhachHang);

        if (updatedList.size() < sizeBefore) {
            System.out.println("Xoa thanh cong khach hang co ma: " + maKhachHang);
        } else {
            System.out.println("Khong tim thay khach hang co ma: " + maKhachHang);
        }

        KH.HienThiKhachHang();
    }

    public static void main(String[] args){
        TestKhachHang test = new TestKhachHang();
        test.addKhachHang();

        System.out.println("\n=== Sua khach hang ===");
        test.testEditKhachHang();

        System.out.println("\n=== Xoa khach hang ===");
        test.testDeleteKhachHang();
    }
}
