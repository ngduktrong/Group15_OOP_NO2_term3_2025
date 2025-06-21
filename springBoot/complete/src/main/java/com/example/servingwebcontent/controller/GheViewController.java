package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GheViewController {

    @Autowired
    private GheService gheService;

    @GetMapping("/ghe/view")
    public String hienThiDanhSachGhe(Model model) {
        List<Ghe> danhSachGhe = gheService.getAllGhes();
        model.addAttribute("dsGhe", danhSachGhe);
        return "ghe/list"; // sẽ render file templates/ghe/list.html
    }
}
