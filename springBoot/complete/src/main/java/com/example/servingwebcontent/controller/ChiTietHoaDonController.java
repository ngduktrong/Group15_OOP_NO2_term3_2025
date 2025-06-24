
package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.ChiTietHoaDon;
import com.example.servingwebcontent.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/chitiethoadon")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    
    @GetMapping
    public List<ChiTietHoaDon> getAll() {
        return chiTietHoaDonService.getAllChiTietHoaDon();
    }

    
    @GetMapping("/{maHoaDon}/{maVe}")
    public ChiTietHoaDon getById(@PathVariable int maHoaDon, @PathVariable int maVe) {
        ChiTietHoaDon ct = chiTietHoaDonService.getChiTietHoaDon(maHoaDon, maVe);
        if (ct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chi tiết hóa đơn không tìm thấy");
        }
        return ct;
    }

   
    @PostMapping
    public ChiTietHoaDon create(@RequestBody ChiTietHoaDon ct) {
       
        chiTietHoaDonService.createChiTietHoaDon(ct);
        return ct;
    }


    @PutMapping("/{oldMaHoaDon}/{oldMaVe}")
    public ChiTietHoaDon update(@PathVariable int oldMaHoaDon,
                                @PathVariable int oldMaVe,
                                @RequestBody ChiTietHoaDon ctNew) {
        ChiTietHoaDon existing = chiTietHoaDonService.getChiTietHoaDon(oldMaHoaDon, oldMaVe);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chi tiết hóa đơn không tìm thấy");
        }
        chiTietHoaDonService.updateChiTietHoaDon(oldMaHoaDon, oldMaVe, ctNew);
        return ctNew;
    }

    
    @DeleteMapping("/{maHoaDon}/{maVe}")
    public String delete(@PathVariable int maHoaDon, @PathVariable int maVe) {
        ChiTietHoaDon existing = chiTietHoaDonService.getChiTietHoaDon(maHoaDon, maVe);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chi tiết hóa đơn không tìm thấy");
        }
        chiTietHoaDonService.deleteChiTietHoaDon(maHoaDon, maVe);
        return "Đã xóa chi tiết hóa đơn!";
    }
}
