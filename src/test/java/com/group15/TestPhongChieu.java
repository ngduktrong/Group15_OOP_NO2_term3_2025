package com.group15;
import java.util.ArrayList;
import java.util.Scanner;

import com.group15.Review.PhongChieuReview;
import com.group15.models.PhongChieu;

public class TestPhongChieu {

    ArrayList<PhongChieu> pcList = new ArrayList<>();

    public ArrayList<PhongChieu> addList() {
        PhongChieu p1 = new PhongChieu(1, "Phòng 1", 100, "2D");
        PhongChieu p2 = new PhongChieu(2, "Phòng 2", 80, "3D");
        PhongChieu p3 = new PhongChieu(3, "Phòng VIP", 50, "IMAX");

        pcList.add(p1);
        pcList.add(p2);
        pcList.add(p3);

        return pcList;
    }

    public void testEditDelete() {

        // Tạo danh sách phòng

        PhongChieu p1 = new PhongChieu(1, "Phòng 1", 100, "2D");
        PhongChieu p2 = new PhongChieu(2, "Phòng 2", 80, "3D");
        PhongChieu p3 = new PhongChieu(3, "Phòng VIP", 50, "IMAX");

        PhongChieuReview phongChieuReview = new PhongChieuReview();
        phongChieuReview.addPhongChieu(p1);
        phongChieuReview.addPhongChieu(p2);
        phongChieuReview.addPhongChieu(p3);

        // In danh sách ban đầu

        System.out.println("Danh sách ban đầu:");
        phongChieuReview.printPhongChieuList();

        // Sửa thông tin phòng

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phòng cần sửa: ");
        int maPhong = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập tên phòng mới: ");
        String tenPhong = sc.nextLine();
        System.out.print("Nhập số lượng ghế mới: ");
        int soLuongGhe = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập loại phòng mới: ");
        String loaiPhong = sc.nextLine();
        PhongChieu newPhong = new PhongChieu(maPhong, tenPhong, soLuongGhe, loaiPhong);
        phongChieuReview.editPhongChieuById(maPhong, newPhong);
        System.out.println("Danh sách sau khi sửa:");
        phongChieuReview.printPhongChieuList();

        // Xóa phòng

        System.out.print("Nhập mã phòng cần xóa: ");
        int maXoa = sc.nextInt();
        phongChieuReview.deletePhongChieuById(maXoa);
        System.out.println("Danh sách sau khi xóa:");
        phongChieuReview.printPhongChieuList();
    }
    public void testDelete(PhongChieuReview phongChieuReview, int maPhong) {
        phongChieuReview.deletePhongChieuById(maPhong);
        phongChieuReview.printPhongChieuList();
    }
    public static void main(String[] args) {
        TestPhongChieu t = new TestPhongChieu();
        t.testEditDelete();
    }
}
