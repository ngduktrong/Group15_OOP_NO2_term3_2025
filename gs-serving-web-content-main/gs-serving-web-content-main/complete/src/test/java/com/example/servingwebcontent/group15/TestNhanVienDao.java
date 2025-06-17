package com.group15;
import com.group15.dao.NhanVienDao;
import com.group15.models.NhanVien;
public class TestNhanVienDao {
    public static void main (String []args){
        NhanVienDao nhanviendao = new NhanVienDao();
        NhanVien nhanvien = new NhanVien(1, NhanVien.VaiTro.Admin, "Quan ly", 10000000);
        nhanviendao.create(nhanvien);
        System.out.println("Inserted NhanVien: " + nhanvien);
        nhanviendao.getAll().forEach(System.out::println);
        nhanvien.setChucVu("Thu ngan");
        nhanvien.setLuong(12000000);
        nhanviendao.update(nhanvien);
        System.out.println("Updated NhanVien: " + nhanvien);
        nhanviendao.getAll().forEach(System.out::println);
    }
    
}
