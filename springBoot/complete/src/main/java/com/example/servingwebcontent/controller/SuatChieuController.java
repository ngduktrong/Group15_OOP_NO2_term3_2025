package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/suatchieu")
public class SuatChieuController {

    @Autowired
    private SuatChieuService suatChieuService;

    
    @GetMapping
    public List<SuatChieu> getAll() {
        return suatChieuService.getAllSuatChieu();
    }

    
    @GetMapping("/{id}")
    public SuatChieu getById(@PathVariable int id) {
        SuatChieu s = suatChieuService.getSuatChieuById(id);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Suất chiếu không tìm thấy");
        }
        return s;
    }


    @PostMapping
    public SuatChieu create(@RequestBody SuatChieu s) {
        suatChieuService.createSuatChieu(s);
        return s;
    }

   
    @PutMapping("/{id}")
    public SuatChieu update(@PathVariable int id, @RequestBody SuatChieu s) {
        SuatChieu existing = suatChieuService.getSuatChieuById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Suất chiếu không tìm thấy");
        }
        s.setMaSuatChieu(id);
        suatChieuService.updateSuatChieu(s);
        return s;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        SuatChieu existing = suatChieuService.getSuatChieuById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Suất chiếu không tìm thấy");
        }
        suatChieuService.deleteSuatChieu(id);
        return "Đã xóa suất chiếu!";
    }
}
