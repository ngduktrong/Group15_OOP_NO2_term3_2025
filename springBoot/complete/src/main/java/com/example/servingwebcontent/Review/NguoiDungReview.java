package com.example.servingwebcontent.Review;
import java.util.ArrayList;
import com.example.servingwebcontent.models.NguoiDung;
public class NguoiDungReview {
    ArrayList<NguoiDung> ND = new ArrayList<NguoiDung>();
    public ArrayList<NguoiDung> addNguoiDung(NguoiDung NguoiDung){
        ND.add(NguoiDung);
        return ND;
    }
    public boolean getNguoiDungList(int MaNguoiDung, String HoTen, String SoDienThoai, String Email, String LoaiNguoiDung) {
        for (int i = 0; i < ND.size(); i++) {
            if (ND.get(i).getMaNguoiDung() == MaNguoiDung) {
                ND.get(i).setHoTen(HoTen);
                ND.get(i).setSoDienThoai(SoDienThoai);
                ND.get(i).setEmail(Email);
                ND.get(i).setLoaiNguoiDung(LoaiNguoiDung);
                return true;
            }
        }
        return false ;
    }
    public ArrayList<NguoiDung> getDeleteNguoiDung(int MaNguoiDung) {
        for (int i = 0; i < ND.size(); i++) {
            if (ND.get(i).getMaNguoiDung() == MaNguoiDung) {
                ND.remove(i);
                System.out.println("Xoa thanh cong nguoi dung co ma: " + MaNguoiDung);
                break;
            }
        }
        return ND;
    }
    public ArrayList<NguoiDung> getNguoiDungList() {
        return ND;
    }
    public void HienThiNguoiDung() {
        for (int i = 0; i < ND.size(); i++) {
            NguoiDung nd = ND.get(i);
            System.out.println("Ma Nguoi Dung: " + nd.getMaNguoiDung());
            System.out.println("Ho Ten: " + nd.getHoTen());
            System.out.println("So Dien Thoai: " + nd.getSoDienThoai());
            System.out.println("Email: " + nd.getEmail());
            System.out.println("Loai Nguoi Dung: " + nd.getLoaiNguoiDung());
            System.out.println("--------------------------");
        }
    }


}
