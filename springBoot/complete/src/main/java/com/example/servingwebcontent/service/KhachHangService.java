package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.KhachHangDao;
import com.example.servingwebcontent.models.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangDao khachHangDao;

    public List<KhachHang> getAllKhachHang() {
        return khachHangDao.getAll();
    }

    public void createKhachHang(KhachHang kh) {
        khachHangDao.create(kh);
    }

    public KhachHang getKhachHangById(int id) {
        return khachHangDao.getByID(id);
    }

    public void updateKhachHang(KhachHang kh) {
        khachHangDao.update(kh);
    }

    public void deleteKhachHang(int id) {
        khachHangDao.delete(id);
    }
}
