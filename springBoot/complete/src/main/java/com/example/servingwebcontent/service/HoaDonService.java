package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.HoaDonDao;
import com.example.servingwebcontent.models.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonDao hoaDonDao;

   
    public List<HoaDon> getAllHoaDon() {
        return hoaDonDao.getAll();
    }

    
    public HoaDon getHoaDonById(int id) {
        return hoaDonDao.getById(id);
    }

    
    public void createHoaDon(HoaDon hd) {
        hoaDonDao.create(hd);
    }

    
    public void updateHoaDon(HoaDon hd) {
        hoaDonDao.update(hd);
    }

   
    public void deleteHoaDon(int id) {
        hoaDonDao.delete(id);
    }
}
