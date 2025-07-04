package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/ve")
public class CustomerVeController {

    @GetMapping("/hoan-tat")
    public String hoanTat(Model model) {
        model.addAttribute("message", "Thanh toán thành công!");
        return "thanh-toan-thanh-cong";
    }
}