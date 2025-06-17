package com.group15;

import com.group15.dao.PhimDao;
import com.group15.models.Phim;

public class TestPhimDao {
    public static void main(String[] args) {
        PhimDao phimDao = new PhimDao();
        Phim phim = new Phim("Ten phim", 99, 120, "2024-01-01", "VN", "2D", "Mo ta", "Dao dien", "poster.jpg");
        phimDao.create(phim);
        System.out.println("Inserted Phim: " + phim);
        phimDao.update(phim);
        for (Phim p : phimDao.getAll()) {
            System.out.println(p);
        }
    }
}