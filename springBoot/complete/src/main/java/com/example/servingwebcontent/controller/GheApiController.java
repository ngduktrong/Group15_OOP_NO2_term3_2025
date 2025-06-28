package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ghe")
public class GheApiController {

    @Autowired
    private GheService gheService;

    @GetMapping
    public List<Ghe> getAllGhes() {
        return gheService.getAll();
    }

    @GetMapping("/{maPhong}/{soGhe}")
    public Ghe getGheById(@PathVariable String maPhong,
                          @PathVariable String soGhe) {
        return gheService.getById(soGhe, maPhong); // ✅ sửa đúng thứ tự
    }
}
