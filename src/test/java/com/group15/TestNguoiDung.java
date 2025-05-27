package com.group15;
import java.util.Scanner;
import java.util.ArrayList;
import com.group15.Review.NguoiDungReview;
import com.group15.models.NguoiDung;


public class TestNguoiDung {
    NguoiDungReview ND = new NguoiDungReview();

    public void addNguoiDung(){
        NguoiDung nd1 = new NguoiDung(1, "Nguyen Van An", "0123456789", "An@gmai.com","NhanVien");
        NguoiDung nd2 = new NguoiDung(2, "Tran Thi Binh", "0987654321", "Binh@gmail.com", "KhachHang");
        NguoiDung nd3 = new NguoiDung(3, "Le Van Cuong", "1234567890", "Cuong@gmail.com","NhanVien");

        ND.addNguoiDung(nd1);
        ND.addNguoiDung(nd2);
        ND.addNguoiDung(nd3);
        ND.HienThiNguoiDung();
    }
    public void EditNguoiDung(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap vao ma nguoi dung can sua:");
        int MaNguoiDung = input.nextInt();
        input.nextLine();
        System.out.println("Nhap vao ho ten moi:");
        String HoTenMoi = input.nextLine();
        System.out.println("Nhap vao so dien thoai moi:");
        String SoDienThoaiMoi = input.nextLine();
        System.out.println("Nhap vao email moi:");
        String EmailMoi = input.nextLine();
        System.out.println("Nhap vao loai nguoi dung moi (NhanVien/KhachHang):");
        String LoaiNguoiDungMoi = input.nextLine();

        boolean thanhCong = ND.getNguoiDungList(MaNguoiDung, HoTenMoi, SoDienThoaiMoi, EmailMoi, LoaiNguoiDungMoi);
        if (thanhCong) {
            System.out.println("Sua thanh cong.");
        } else {
            System.out.println("Khong tim thay nguoi dung co ma " + MaNguoiDung);
        }
        ND.HienThiNguoiDung();
    }
    public void DeleteNguoiDung(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap vao ma nguoi dung can xoa:");
        int MaNguoiDung = input.nextInt();

        int sizeBefore = ND.getNguoiDungList().size();
        ArrayList<NguoiDung> updatedList = ND.getDeleteNguoiDung(MaNguoiDung); 
        
        if(updatedList.size() < sizeBefore) {
            System.out.println("Xoa thanh cong nguoi dung co ma: " + MaNguoiDung);
        } else {
            System.out.println("Khong tim thay nguoi dung co ma: " + MaNguoiDung);
        }
        ND.HienThiNguoiDung();
    }
    public static void main(String[] args) {
        TestNguoiDung test = new TestNguoiDung();
        
        test.addNguoiDung();

        System.out.println("Sua nguoi dung:");
        test.EditNguoiDung();
        System.out.println("Xoa nguoi dung");
        test.DeleteNguoiDung();
        
        
    }
}