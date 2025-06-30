package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.VeDao;
import com.example.servingwebcontent.models.Ve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeService {

    @Autowired
    private VeDao veDao;

    public List<Ve> getAllVe() {
        return veDao.getAll();
    }

    public Ve getVeById(int id) {
        return veDao.getById(id);
    }

    public void createVe(Ve ve) {
        veDao.create(ve);
    }

    public void updateVe(Ve ve) {
        veDao.update(ve);
    }

    public void deleteVe(int id) {
        veDao.delete(id);
    }

    public List<Ve> getVesByIds(List<Integer> veIds) {
        return veDao.getVesByIds(veIds);
    }

    public List<Ve> getVeByMaHoaDon(int maHoaDon) {
        return veDao.getVeByMaHoaDon(maHoaDon);
    }
}

