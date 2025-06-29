package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien/hoadon")
public class NhanVienHoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping
    public String listHoaDon(Model model) {
        model.addAttribute("listHoaDon", hoaDonService.getAllHoaDon());
        return "list-hoadon";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("hoaDon", new HoaDon());
        return "add-hoadon";
    }

    @PostMapping("/add")
    public String addHoaDon(@ModelAttribute HoaDon hoaDon, Model model) {
        hoaDonService.createHoaDon(hoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getAllHoaDon());
        return "list-hoadon";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        model.addAttribute("hoaDon", hoaDon);
        return "edit-hoadon";
    }

    @PostMapping("/edit/{id}")
    public String updateHoaDon(@PathVariable int id, @ModelAttribute HoaDon hoaDon, Model model) {
        hoaDon.setMaHoaDon(id);
        hoaDonService.updateHoaDon(hoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getAllHoaDon());
        return "list-hoadon";
    }

    @GetMapping("/delete/{id}")
    public String deleteHoaDon(@PathVariable int id, Model model) {
        hoaDonService.deleteHoaDon(id);
        model.addAttribute("listHoaDon", hoaDonService.getAllHoaDon());
        return "list-hoadon";
    }
}