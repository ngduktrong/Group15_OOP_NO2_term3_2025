
package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.ChiTietHoaDonDao;
import com.example.servingwebcontent.models.ChiTietHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietHoaDonService {

    @Autowired
    private ChiTietHoaDonDao chiTietHoaDonDao;

  
    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        return chiTietHoaDonDao.getAll();
    }

    
    public ChiTietHoaDon getChiTietHoaDon(int maHoaDon, int maVe) {
        return chiTietHoaDonDao.getById(maHoaDon, maVe);
    }

  
    public void createChiTietHoaDon(ChiTietHoaDon ct) {
        chiTietHoaDonDao.create(ct);
    }

  
    public void updateChiTietHoaDon(int oldMaHoaDon, int oldMaVe, ChiTietHoaDon ctNew) {
        chiTietHoaDonDao.update(oldMaHoaDon, oldMaVe, ctNew);
    }

 
    public void deleteChiTietHoaDon(int maHoaDon, int maVe) {
        chiTietHoaDonDao.delete(maHoaDon, maVe);
    }
}
