package com.group15;
import java.util.Scanner;
import java.util.ArrayList;

import com.group15.models.NhanVien;
import com.group15.Review.NhanVienReview;

public class TestNhanVien {
    ArrayList<NhanVien> NhanVienList = new ArrayList<NhanVien>();

    public ArrayList<NhanVien> addNhanVien(){
        NhanVien nv1= new NhanVien(10,"nguyen van hoang","QuanLy","ca Sang","012345","Hoang@gmial.com");
        NhanVien nv2= new NhanVien(11,"nguyen van an","ThuNgan","ca Chieu","012346","an@gmail.com");
        NhanVien nv3= new NhanVien(12,"nguyen van binh","Nhanvien","ca Toi","012347","binh@gmail.com");

        NhanVienList.add(nv1);
        NhanVienList.add(nv2);
        NhanVienList.add(nv3);
        return NhanVienList;
    }
    public void testeditDeleteNhanVien(){
    
    NhanVienReview NV = new NhanVienReview();

    NhanVien nv1= new NhanVien(10,"nguyen van hoang","QuanLy","ca Sang","012345","Hoang@gmail.com");
    NhanVien nv2= new NhanVien(11,"nguyen van an","ThuNgan","ca Chieu","012346","an@gmail.com");
    NhanVien nv3= new NhanVien(12,"nguyen van binh","Nhanvien","ca Toi","012347","binh@gmail.com");

    NV.addNhanVien(nv1);
    NV.addNhanVien(nv2);
    NV.addNhanVien(nv3);

    Scanner input = new Scanner(System.in);
    System.out.println("Nhap vao ma nhan vien:");
    int MaNhanVien = input.nextInt();
    input.nextLine();  

    System.out.println("Nhap vao ten nhan vien moi:");
    String TenMoi = input.nextLine();
    System.out.println("Nhap vao chuc vu:");
    String ChucVuMoi = input.nextLine();
    System.out.println("Nhap vao ca lam:");
    String CaLamMoi = input.nextLine();
    System.out.println("Nhap vao so dien thoai:");
    String SoDienThoaiMoi = input.nextLine();
    System.out.println("Nhap vao email:");
    String EmailMoi = input.nextLine();

    
    boolean success = NV.getEditNhanVien(MaNhanVien, TenMoi,ChucVuMoi,CaLamMoi,SoDienThoaiMoi,EmailMoi);
    if(success){
        System.out.println("sua thanh cong");
    } else {
        System.out.println("khong tim thay nhan vien co ma " + MaNhanVien);
    }

    NV.HienThiNhanVien();
}

}