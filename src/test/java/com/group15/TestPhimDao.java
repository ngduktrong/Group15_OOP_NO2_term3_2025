package com.group15;

import com.group15.models.Phim;

public class TestPhimDao {
    public static void main(String[] args) {
        PhimDao phimDao = new PhimDao();
        Phim phim = new Phim("Ten phim", 1, 120, "2024-01-01", "VN", "2D", "Mo ta", "Dao dien", "poster.jpg");
        phimDao.insert(phim);
        phimDao.delete(1);
        for (Phim p : phimDao.getAll()) {
            System.out.println(p);
        }
    }
}