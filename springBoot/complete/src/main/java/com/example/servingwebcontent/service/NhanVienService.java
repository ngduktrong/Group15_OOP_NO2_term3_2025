package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.NhanVienDao;
import com.example.servingwebcontent.models.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    private final NhanVienDao nhanVienDao = new NhanVienDao();

    public List<NhanVien> getAllNhanVien() {
        return nhanVienDao.getAll();
    }

    public NhanVien getNhanVienById(int id) {
        return nhanVienDao.getById(id);
    }

    public void addNhanVien(NhanVien nv) {
        nhanVienDao.create(nv);
    }

    public void updateNhanVien(NhanVien nv) {
        nhanVienDao.update(nv);
    }

    public void deleteNhanVien(int id) {
        nhanVienDao.delete(id);
    }
}
