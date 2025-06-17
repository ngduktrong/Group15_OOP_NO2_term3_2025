package com.group15.Review;
import java.util.ArrayList;
import com.group15.models.NhanVien;

public class NhanVienReview {
    ArrayList<NhanVien> NV = new ArrayList<NhanVien>();

    public ArrayList<NhanVien> addNhanVien(NhanVien NVien){
        NV.add(NVien);
        return NV;
    } 


    public boolean editNhanVien(int MaNhanVien, String Ten, String ChucVu, String CaLam, String SoDienThoai, String Email) {
    for (int i = 0; i < NV.size(); i++) {
        if (NV.get(i).getMaNhanVien() == MaNhanVien) {
            NV.get(i).setTen(Ten);
            NV.get(i).setChucVu(ChucVu);
            NV.get(i).setCaLam(CaLam);
            NV.get(i).setSoDienThoai(SoDienThoai);
            NV.get(i).setEmail(Email);
            return true;
        }
    }
    return false;
}


    public ArrayList<NhanVien> getDeleteNhanvien(int MaNhanVien){
        for(int i=0;i<NV.size();i++){
            if(NV.get(i).getMaNhanVien()==MaNhanVien){
                NV.remove(i);
                System.out.println("Xoa thanh cong nhan vien co ma:" + MaNhanVien);
                break;
            }
        }
        return NV;
    }
    public ArrayList<NhanVien> getNhanVienList() {
        return NV;
    }

    public void HienThiNhanVien(){
        for (int i = 0; i < NV.size(); i++) {
            NhanVien nv = NV.get(i);  
            System.out.println("Ma Nhan Vien: " + nv.getMaNhanVien());
            System.out.println("Ten: " + nv.getTen());
            System.out.println("Chuc Vu: " + nv.getChucVu());
            System.out.println("Ca Lam: " + nv.getCaLam());
            System.out.println("So Dien Thoai: " + nv.getSoDienThoai());
            System.out.println("Email: " + nv.getEmail());
            System.out.println("--------------------------");
        }

    }
}
