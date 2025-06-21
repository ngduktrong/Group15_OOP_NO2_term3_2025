package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.dao.PhimDao;
import com.example.servingwebcontent.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/phim")
public class PhimController {

    @Autowired
    private PhimDao phimDao;

    
    @GetMapping
    public List<Phim> getAllPhim() {
        return phimDao.getAll();
    }

   
    @GetMapping("/{id}")
    public Phim getPhimById(@PathVariable int id) {
        Phim p = phimDao.getById(id);
        if (p == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phim không tìm thấy");
        }
        return p;
    }

    
    @PostMapping
    public Phim createPhim(@RequestBody Phim phim) {
       
        phimDao.create(phim);
        return phim;
    }

    
    @PutMapping("/{id}")
    public Phim updatePhim(@PathVariable int id, @RequestBody Phim phim) {
        Phim existing = phimDao.getById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phim không tìm thấy");
        }
        phim.setMaPhim(id);
        phimDao.update(phim);
        return phim;
    }

    
    @DeleteMapping("/{id}")
    public String deletePhim(@PathVariable int id) {
        Phim existing = phimDao.getById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phim không tìm thấy");
        }
        phimDao.delete(id);
        return "Đã xóa phim!";
    }
}
