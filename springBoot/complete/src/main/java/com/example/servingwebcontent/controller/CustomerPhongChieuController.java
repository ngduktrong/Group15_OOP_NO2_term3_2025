package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerPhongChieuController {

    @Autowired
    private PhongChieuService phongChieuService;

    @GetMapping("/customer/phongchieu")
    public String viewCustomer(Model model) {
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "view-phong-customer";
    }
}

