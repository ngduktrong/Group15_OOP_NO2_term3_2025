package com.group15.Review;
import java.util.ArrayList;
import com.group15.models.NhanVien;

public class NhanVienReview {
    ArrayList<NhanVien> NV = new ArrayList<NhanVien>();
    public ArrayList<NhanVien> addNhanVien(NhanVien NVien){
        NV.add(NVien);
        return NV;
    } 
    public ArrayList<NhanVien> getEditNhanVien(int MaNhanVien, String Ten, String ChucVu, String CaLam, String SoDienThoai, String Email) {
        for (int i=0;i<NV.size();i++){
            if(NV.get(i).getMaNhanVien() == MaNhanVien){
                System.out.println("TRUE");
                NV.get(i).setTen(Ten);
                NV.get(i).setChucVu(ChucVu);
                NV.get(i).setCaLam(CaLam);
                NV.get(i).setSoDienThoai(SoDienThoai);
                NV.get(i).setEmail(Email);
            }
        }
        return NV;
    }
    public ArrayList<NhanVien> getDeleteNhanvien(int MaNhanVien){
        for(int i=0;i<NV.size();i++){
            if(NV.get(i).getMaNhanVien()==MaNhanVien){
                NV.remove(i);
                System.out.println("Xoa thanh cong nhan vien co ma:" + MaNhanVien);
            }
        }
        return NV;
    }
    public void HienThiNhanVien(){
        for(int i=0;i<NV.size();i++){
            System.out.println("Ma Nhan Vien: " + NV.get(i).getMaNhanVien());
            System.out.println("Ten: " + NV.get(i).getTen());
            System.out.println("Chuc Vu: " + NV.get(i).getChucVu());
            System.out.println("Ca Lam: " + NV.get(i).getCaLam());
            System.out.println("So Dien Thoai: " + NV.get(i).getSoDienThoai());
            System.out.println("Email: " + NV.get(i).getEmail());
        }
    }
}