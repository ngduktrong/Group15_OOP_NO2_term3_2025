package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/phongchieu")
public class PhongChieuController {

    @Autowired
    private PhongChieuService phongChieuService;

    
    @GetMapping
    public List<PhongChieu> getAll() {
        return phongChieuService.getAllPhongChieu();
    }

    
    @GetMapping("/{id}")
    public PhongChieu getById(@PathVariable int id) {
        PhongChieu p = phongChieuService.getPhongChieuById(id);
        if (p == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phòng chiếu không tìm thấy");
        }
        return p;
    }

   
    @PostMapping
    public PhongChieu create(@RequestBody PhongChieu p) {
        phongChieuService.createPhongChieu(p);
        return p;
    }

  
    @PutMapping("/{id}")
    public PhongChieu update(@PathVariable int id, @RequestBody PhongChieu p) {
        PhongChieu existing = phongChieuService.getPhongChieuById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phòng chiếu không tìm thấy");
        }
        p.setMaPhong(id);
        phongChieuService.updatePhongChieu(p);
        return p;
    }

   
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        PhongChieu existing = phongChieuService.getPhongChieuById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phòng chiếu không tìm thấy");
        }
        phongChieuService.deletePhongChieu(id);
        return "Đã xóa phòng chiếu!";
    }
}
