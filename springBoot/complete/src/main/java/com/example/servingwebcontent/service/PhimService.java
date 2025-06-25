package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.PhimDao;
import com.example.servingwebcontent.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhimService {
    @Autowired
    private PhimDao phimDao;
    public List<Phim> getAllPhim() {
        return phimDao.getAll();
    }
    public Phim getPhimById(int id) {
        return phimDao.getById(id);
    }
    public void createPhim(Phim phim) {
        phimDao.create(phim);
    }
    public void updatePhim(Phim phim) {
        phimDao.update(phim);
    }
    public void deletePhim(int id) {
        phimDao.delete(id);
    }
}
