package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhimController {

    @Autowired
    private PhimService phimService;

    
    @GetMapping("/list")
    public List<Phim> listPhim() {
        return phimService.getAllPhim();
    }

    @GetMapping("/phim/{id}")
    public Phim getPhimById(@PathVariable int id) {
        Phim p = phimService.getPhimById(id);
        if (p == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Phim khong tim thay");
        }
        return p;
    }
}
