package com.group15;

import com.group15.dao.GheDao;
import com.group15.dao.PhimDao;
import com.group15.dao.PhongChieuDao;
import com.group15.dao.SuatChieuDao;
import com.group15.models.Ghe;
import com.group15.models.Phim;
import com.group15.models.PhongChieu;
import com.group15.models.SuatChieu;

import java.util.List;

public class ReviewCRUD {
    public static void main(String[] args) {
       
        PhimDao phimDAO = new PhimDao();
        GheDao gheDAO = new GheDao();
        PhongChieuDao phongDAO = new PhongChieuDao();
        SuatChieuDao suatDAO = new SuatChieuDao();

        
        System.out.println("--- PHIM CRUD ---");
       
        Phim p = new Phim( "ma da",10, 120, "2025-06-01", "Vietnam", "2D", "Phim ma", "Nguyen van an", "/posters/kieuhung.jpg");
        phimDAO.create(p);

        List<Phim> allPhim = phimDAO.getAll();
        System.out.println("danh sach sau khi them:");
        allPhim.forEach(System.out::println);

       
        p.setMoTa("phim hanh dong ");
        phimDAO.update(p);
        System.out.println("phim sau khi cap nhat:");
        System.out.println(phimDAO.getById(101));

        
        phimDAO.delete(101);
        System.out.println("danh sahc phim sau khi xoa:");
        phimDAO.getAll().forEach(System.out::println);

       
        System.out.println("\n--- ghe crud ---");
       
        Ghe g = new Ghe(1, "P01");
        gheDAO.create(g);

        
        List<Ghe> allGhe = gheDAO.getAll();
        System.out.println("sanh sach ghe sau khi them:");
        allGhe.forEach(System.out::println);

        
        g.setMaPhong("P02");
        gheDAO.update(g);
        System.out.println("ghe sau khi cap nhat:");
        System.out.println(gheDAO.getById(1, "P02"));

       
        gheDAO.delete(1);
        System.out.println("sanh sach sau khi xoa:");
        gheDAO.getAll().forEach(System.out::println);

       
        System.out.println("\n--- phong chieu crud ---");
        
        PhongChieu pc = new PhongChieu(10, "Phong VIP", 50, "VIP");
        phongDAO.create(pc);

    
        List<PhongChieu> allPhong = phongDAO.getAll();
        System.out.println("danh sach phong chieu sau khi them:");
        allPhong.forEach(System.out::println);

      
        pc.setLoaiPhong("Deluxe");
        phongDAO.update(pc);
        System.out.println("phong chieu sau khi cap nhat:");
        System.out.println(phongDAO.getById(10));

        
        phongDAO.delete(10);
        System.out.println("danh sach phong chieu sau khi xoa:");
        phongDAO.getAll().forEach(System.out::println);

       
        System.out.println("\n--- suat chieu crud ---");
        
        SuatChieu sc = new SuatChieu(1001, 102, 11, "2025-06-15 14:30:00");
        suatDAO.create(sc);

        
        List<SuatChieu> allSuat = suatDAO.getAll();
        System.out.println("sanh sach xuat chieu sau khi them:");
        allSuat.forEach(System.out::println);

       
        sc.setNgayGioChieu("2025-06-15 16:00:00");
        suatDAO.update(sc);
        System.out.println("suat chieu sau khi cap nhat:");
        System.out.println(suatDAO.getById(1001));

        
        suatDAO.delete(1001);
        System.out.println("sanh sach xuat chieu sau khi xoa:");
        suatDAO.getAll().forEach(System.out::println);
    }
}
