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

    // Lấy tất cả vé
    public List<Ve> getAllVes() {
        return veDao.getAll();
    }

    // Lấy vé theo ID
    public Ve getVeById(int id) {
        return veDao.getById(id);
    }

    // Tạo mới vé
    public void createVe(Ve ve) {
        veDao.create(ve);
    }

    // Cập nhật vé
    public void updateVe(Ve ve) {
        veDao.update(ve);
    }

    // Xoá vé
    public void deleteVe(int id) {
        veDao.delete(id);
    }
}
// VeService.java