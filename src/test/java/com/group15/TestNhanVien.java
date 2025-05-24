package com.group15;
import java.util.Scanner;
import java.util.ArrayList;

import com.group15.models.NhanVien;
import com.group15.Review.NhanVienReview;

public class TestNhanVien {
    
    NhanVienReview NV = new NhanVienReview();

    
    public void addNhanVien(){
        NhanVien nv1 = new NhanVien(10,"nguyen van hoang","QuanLy","ca Sang","012345","Hoang@gmial.com");
        NhanVien nv2 = new NhanVien(11,"nguyen van an","ThuNgan","ca Chieu","012346","an@gmail.com");
        NhanVien nv3 = new NhanVien(12,"nguyen van binh","Nhanvien","ca Toi","012347","binh@gmail.com");

        
        NV.addNhanVien(nv1);
        NV.addNhanVien(nv2);
        NV.addNhanVien(nv3);
        NV.HienThiNhanVien();
    }


    public void testeditNhanVien(){
        Scanner input = new Scanner(System.in);

        System.out.println("Nhap vao ma nhan vien can sua:");
        int maNhanVien = input.nextInt();
        input.nextLine();  

        System.out.println("nhap vao ten nhan vien moi:");
        String tenMoi = input.nextLine();
        System.out.println("Nhap vao chuc vu moi:");
        String chucVuMoi = input.nextLine();
        System.out.println("Nhap vao ca lam moi:");
        String caLamMoi = input.nextLine();
        System.out.println("Nhap vao so dien thoai moi:");
        String soDienThoaiMoi = input.nextLine();
        System.out.println("Nhap email moi:");
        String emailMoi = input.nextLine();

        boolean thanhCong = NV.editNhanVien(maNhanVien, tenMoi, chucVuMoi, caLamMoi, soDienThoaiMoi, emailMoi);
        if (thanhCong) {
            System.out.println("Sua thanh cong.");
        } else {
            System.out.println("Khong tim thay nhan vien co ma " + maNhanVien);
        }

        NV.HienThiNhanVien();
    }

    
    public void testDeleteNhanVien(){
        Scanner input = new Scanner(System.in);
        System.out.println("nhap vao ma nhan vien can xoa:");
        int maNhanVien = input.nextInt();

        int sizeBefore = NV.getNhanVienList().size();
        ArrayList<NhanVien> updatedList = NV.getDeleteNhanvien(maNhanVien);

        if (updatedList.size() < sizeBefore) {
            System.out.println("xoa thanh cong nhan vien co ma: " + maNhanVien);
        } else {
            System.out.println("khong tim thay nhan vien co ma " + maNhanVien);
        }

        NV.HienThiNhanVien();
    }

    public static void main(String[] args){
        TestNhanVien test = new TestNhanVien();
        test.addNhanVien();

        System.out.println("\n=== sua nhan vienvien ===");
        test.testeditNhanVien();

        System.out.println("\n=== xoa nhan vien  ===");
        test.testDeleteNhanVien();
    }
}
