package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.PhongChieuDao;
import com.example.servingwebcontent.models.PhongChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongChieuService {

    @Autowired
    private PhongChieuDao phongChieuDao;

   
    public List<PhongChieu> getAllPhongChieu() {
        return phongChieuDao.getAll();
    }

    
    public PhongChieu getPhongChieuById(int id) {
        return phongChieuDao.getById(id);
    }

   
    public void createPhongChieu(PhongChieu p) {
        phongChieuDao.create(p);
    }

 
    public void updatePhongChieu(PhongChieu p) {
        phongChieuDao.update(p);
    }

   
    public void deletePhongChieu(int id) {
        phongChieuDao.delete(id);
    }
}
