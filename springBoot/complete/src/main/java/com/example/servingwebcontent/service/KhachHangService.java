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

    public KhachHang getKhachHangById(int id) {
        return khachHangDao.getByID(id);
    }

    public boolean createKhachHang(KhachHang kh) {
        try {
            khachHangDao.create(kh);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKhachHang(KhachHang kh) {
        try {
            khachHangDao.update(kh);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKhachHang(int id) {
        try {
            khachHangDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
