package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer/ghe")
public class CustomerGheController {

   @Autowired
private GheService gheService;

/**
 * Truy cập trực tiếp theo phòng (tuỳ chọn)
 */
@GetMapping("/{maPhong}")
public String listGheByPhong(
        @PathVariable int maPhong,
        Model model) {
    List<Ghe> list = gheService.getByMaPhong(maPhong);
    model.addAttribute("listGhe", list);
    model.addAttribute("maPhong", maPhong);
    return "list-ghe-customer";
}
}