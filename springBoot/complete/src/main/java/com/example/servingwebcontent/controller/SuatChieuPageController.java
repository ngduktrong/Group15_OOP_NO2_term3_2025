package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suatchieu")
public class SuatChieuPageController {

    // Khi truy cập /suatchieu/admin sẽ load file
    // src/main/resources/templates/admin/SuatChieu.html
    @GetMapping("/admin")
    public String adminPage() {
        return "admin/SuatChieu";
    }

    // Khi truy cập /suatchieu/customer sẽ load file
    // src/main/resources/templates/customer/SuatChieu.html
    @GetMapping("/customer")
    public String customerPage() {
        return "customer/SuatChieu";
    }
}
