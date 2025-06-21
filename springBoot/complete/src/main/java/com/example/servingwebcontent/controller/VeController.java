package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.dao.VeDao;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ve")
public class VeController {

    @Autowired
    private VeService veService;

    @GetMapping
    public List<Ve> getAllVes() {
        return veService.getAllVes();
    }

    @GetMapping("/{id}")
        public Ve getVeById(@PathVariable int id) {
        return veService.getVeById(id);
    }

    @PostMapping
    public String createVe(@RequestBody Ve ve) {
        veService.createVe(ve);
        return "Đã thêm vé!";
    }

    @PutMapping("/{id}")
    public String updateVe(@PathVariable int id, @RequestBody Ve ve) {
        ve.setMaVe(id);
        veService.updateVe(ve);
        return " Đã cập nhật vé!";
    }

    @DeleteMapping("/{id}")
    public String deleteVe(@PathVariable int id) {
        veService.deleteVe(id);
        return " Đã xoá vé!";
    }
}
