package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.KhachHang;
import com.example.servingwebcontent.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping
    public String listKhachHang(Model model) {
        model.addAttribute("listKhachHang", khachHangService.getAllKhachHang());
        model.addAttribute("khachHang", new KhachHang());
        return "khachhang";
    }

    @PostMapping("/add")
    public String addKhachHang(@ModelAttribute("khachHang") KhachHang kh) {
        khachHangService.createKhachHang(kh);
        return "redirect:/khachhang";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("khachHang", khachHangService.getKhachHangById(id));
        model.addAttribute("listKhachHang", khachHangService.getAllKhachHang());
        return "khachhang";
    }

    @PostMapping("/update")
    public String updateKhachHang(@ModelAttribute("khachHang") KhachHang kh) {
        khachHangService.updateKhachHang(kh);
        return "redirect:/khachhang";
    }

    @GetMapping("/delete/{id}")
    public String deleteKhachHang(@PathVariable int id) {
        khachHangService.deleteKhachHang(id);
        return "redirect:/khachhang";
    }
}
