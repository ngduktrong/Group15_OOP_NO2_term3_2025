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

    @GetMapping("/{soGhe}")
    public Ghe getGheBySoGhe(@PathVariable String soGhe) {
        return gheService.getBySoGhe(soGhe);
    }

    @GetMapping("/phong/{maPhong}")
    public List<Ghe> getGheByMaPhong(@PathVariable int maPhong) {
        return gheService.getByMaPhong(maPhong);
    }

    @PostMapping
    public void createGhe(@RequestBody Ghe ghe) {
        gheService.create(ghe);
    }

    @PutMapping("/{soGhe}")
    public void updateGhe(@PathVariable String soGhe, @RequestBody Ghe ghe) {
        ghe.setSoGhe(soGhe); // Đảm bảo số ghế được set đúng
        gheService.update(ghe);
    }

    @DeleteMapping("/{soGhe}")
    public void deleteGhe(@PathVariable String soGhe) {
        gheService.delete(soGhe);
    }
}