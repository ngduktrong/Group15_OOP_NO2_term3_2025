package com.group15.Review;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.group15.models.KhachHang;

public class KhachHangReview {
    ArrayList<KhachHang> KH = new ArrayList<KhachHang>();
    
    public ArrayList<KhachHang> addKhachHang(KhachHang khachHang) {
        KH.add(khachHang);
        return KH;
    }
    public boolean editKhachHang(int maKhachHang, String ten, String soDienThoai, String email) {
        for (int i = 0; i < KH.size(); i++) {
            if (KH.get(i).getMaKhachHang() == maKhachHang) {
                KH.get(i).setTen(ten);
                KH.get(i).setSoDienThoai(soDienThoai);
                KH.get(i).setEmail(email);
                return true;
            }
        }
        return false;
    }
    public ArrayList<KhachHang> getDeleteKhachHang(int maKhachHang) {
        for (int i = 0; i < KH.size(); i++) {
            if (KH.get(i).getMaKhachHang() == maKhachHang) {
                KH.remove(i);
                System.out.println("Xoa thanh cong khach hang co ma: " + maKhachHang);
                break;
            }
        }
        return KH;
    }
    public ArrayList<KhachHang> getKhachHangList() {
        return KH;
    }
    public void HienThiKhachHang() {
        for (int i = 0; i < KH.size(); i++) {
            KhachHang kh = KH.get(i);
            System.out.println("Ma Khach Hang: " + kh.getMaKhachHang());
            System.out.println("Ten: " + kh.getTen());
            System.out.println("So Dien Thoai: " + kh.getSoDienThoai());
            System.out.println("Email: " + kh.getEmail());
            System.out.println("--------------------------");
        }
    }

}
