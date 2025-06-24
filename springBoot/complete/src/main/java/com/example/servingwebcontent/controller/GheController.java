package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ghe")
public class GheController {

    @Autowired
    private GheService gheService;
    // Lấy tất cả ghế
    @GetMapping
    public List<Ghe> getAllGhes() {
        return gheService.getAllGhes();
    }

    // Lấy ghế theo soGhe và maPhong
    @GetMapping("/{soGhe}/{maPhong}")
    public Ghe getGheById(@PathVariable int soGhe, @PathVariable String maPhong) {
        return gheService.getGheById(soGhe, maPhong);
    }
}
