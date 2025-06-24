package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.NguoiDungDao;
import com.example.servingwebcontent.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungDao nguoiDungDao;

    // Lấy tất cả người dùng
    public List<NguoiDung> getAllNguoiDungs() {
        return nguoiDungDao.getAll();
    }

    // Lấy người dùng theo ID
    public NguoiDung getNguoiDungById(int id) {
        return nguoiDungDao.getByID(id);
    }

    // Tạo mới người dùng
    public void createNguoiDung(NguoiDung nguoiDung) {
        nguoiDungDao.creat(nguoiDung);
    }

    // Cập nhật người dùng
    public void updateNguoiDung(NguoiDung nguoiDung) {
        nguoiDungDao.update(nguoiDung);
    }

    // Xoá người dùng
    public void deleteNguoiDung(int id) {
        nguoiDungDao.delete(id);
    }
    
}
