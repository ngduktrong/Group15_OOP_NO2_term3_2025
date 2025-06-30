package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.NhanVienDao;
import com.example.servingwebcontent.models.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    private final NhanVienDao nhanVienDao;

    @Autowired
    public NhanVienService(NhanVienDao nhanVienDao) {
        this.nhanVienDao = nhanVienDao;
    }

    public List<NhanVien> getAllNhanVien() {
        return nhanVienDao.getAll();
    }

    public NhanVien getNhanVienById(int id) {
        return nhanVienDao.getById(id);
    }

    public void createNhanVien(NhanVien nv) {
        nhanVienDao.create(nv);
    }

    public boolean updateNhanVien(NhanVien nv) {
        try {
            nhanVienDao.update(nv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanVien(int id) {
        try {
            nhanVienDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}