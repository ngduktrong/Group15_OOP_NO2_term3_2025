package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.GheDao;
import com.example.servingwebcontent.models.Ghe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GheService {

    @Autowired
    private GheDao gheDao;

    
    public List<Ghe> getAllGhes() {
        return gheDao.getAll();
    }

    // Lấy ghế theo ID
    public Ghe getGheById(int soGhe, String maPhong) {
        return gheDao.getById(soGhe, maPhong);
    }
}
