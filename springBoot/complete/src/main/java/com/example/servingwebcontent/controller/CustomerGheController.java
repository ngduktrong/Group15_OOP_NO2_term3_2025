package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer/ghe")
public class CustomerGheController {

    @Autowired
    private GheService gheService;

    @GetMapping("/{maPhong}")
    public String listGheByPhong(@PathVariable("maPhong") String maPhong, Model model) {
        List<Ghe> list = gheService.getByMaPhong(maPhong);

        if (list.isEmpty()) {
            model.addAttribute("message", "Phòng " + maPhong + " hiện chưa có ghế nào.");
        }

        model.addAttribute("listGhe", list);
        model.addAttribute("maPhong", maPhong); // dùng chữ thường để Thymeleaf map chính xác
        return "list-ghe-customer";
    }
}
