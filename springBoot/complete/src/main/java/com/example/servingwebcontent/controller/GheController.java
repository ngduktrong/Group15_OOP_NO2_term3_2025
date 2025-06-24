package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // ✅ Đổi từ RestController sang Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ghe") 
public class GheController {

    @Autowired
    private GheService gheService;
    @GetMapping("/view")
    public String showGheList(Model model) {
        List<Ghe> danhSachGhe = gheService.getAllGhes();
        model.addAttribute("dsGhe", danhSachGhe);
        return "ghe/list"; // => resources/templates/ghe/list.html
    }
    @ResponseBody
    @GetMapping
    public List<Ghe> getAllGhes() {
        return gheService.getAllGhes();
    }

    @ResponseBody
    @GetMapping("/{maPhong}/{soGhe}")
    public Ghe getGheById(@PathVariable int maPhong, @PathVariable String soGhe) {
        return gheService.getGheById(maPhong, soGhe);
    }
}
