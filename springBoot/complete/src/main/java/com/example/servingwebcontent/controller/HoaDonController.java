package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    
    @GetMapping
    public List<HoaDon> getAllHoaDon() {
        return hoaDonService.getAllHoaDon();
    }

    
    @GetMapping("/{id}")
    public HoaDon getHoaDonById(@PathVariable int id) {
        HoaDon hd = hoaDonService.getHoaDonById(id);
        if (hd == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hóa đơn không tìm thấy");
        }
        return hd;
    }

    @PostMapping
    public HoaDon createHoaDon(@RequestBody HoaDon hd) {
        
        hoaDonService.createHoaDon(hd);
        return hd;
    }

    
    @PutMapping("/{id}")
    public HoaDon updateHoaDon(@PathVariable int id, @RequestBody HoaDon hd) {
        HoaDon existing = hoaDonService.getHoaDonById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hóa đơn không tìm thấy");
        }
        hd.setMaHoaDon(id);
        hoaDonService.updateHoaDon(hd);
        return hd;
    }

   
    @DeleteMapping("/{id}")
    public String deleteHoaDon(@PathVariable int id) {
        HoaDon existing = hoaDonService.getHoaDonById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hóa đơn không tìm thấy");
        }
        hoaDonService.deleteHoaDon(id);
        return "Đã xóa hóa đơn!";
    }
}
