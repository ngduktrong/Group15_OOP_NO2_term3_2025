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

    public List<Ghe> getAll() {
        return gheDao.getAll();
    }

    public Ghe getBySoGhe(String soGhe) {
        return gheDao.getBySoGhe(soGhe);
    }

    public void create(Ghe ghe) {
        gheDao.create(ghe);
    }

    public void update(Ghe ghe) {
        gheDao.update(ghe);
    }

    public void delete(String soGhe) {
        gheDao.delete(soGhe);
    }

    public List<Ghe> getByMaPhong(int maPhong) {
        return gheDao.getByMaPhong(maPhong);
    }
}
