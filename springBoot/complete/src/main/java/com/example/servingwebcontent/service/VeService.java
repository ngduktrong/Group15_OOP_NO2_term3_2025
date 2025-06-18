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

    public List<Ve> getAll() {
        return veDao.getAll();
    }

    public Ve getById(int id) {
        return veDao.getById(id);
    }

    public void create(Ve ve) {
        veDao.create(ve);
    }

    public void update(Ve ve) {
        veDao.update(ve);
    }

    public void delete(int id) {
        veDao.delete(id);
    }
}
