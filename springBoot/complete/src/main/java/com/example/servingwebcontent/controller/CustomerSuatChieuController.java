package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerSuatChieuController {
    @Autowired
    private SuatChieuService suatChieuService;

    @GetMapping("/customer/phongchieu/select/{maPhong}")
    public String showSuatChieuTheoPhong(@PathVariable int maPhong, Model model) {
        model.addAttribute("listSuatChieu", suatChieuService.getByMaPhong(maPhong));
        model.addAttribute("maPhong", maPhong);
        return "list-suat-chieu-customer";
    }
}

