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

    public Ghe getGheById(int maPhong, String soGhe) {
        return gheDao.getById(String.valueOf(maPhong), soGhe);
    }
}
// GheService.java
