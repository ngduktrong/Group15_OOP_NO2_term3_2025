package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien/suatchieu")
public class AdminSuatChieuController {
    @Autowired
    private SuatChieuService suatChieuService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listSuatChieu", suatChieuService.getAllSuatChieu());
        return "list-suat-chieu";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("suatChieu", new SuatChieu());
        return "add-suat-chieu";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute SuatChieu sc, Model model) {
        suatChieuService.createSuatChieu(sc);
        model.addAttribute("listSuatChieu", suatChieuService.getAllSuatChieu());
        return "list-suat-chieu";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        model.addAttribute("suatChieu", suatChieuService.getSuatChieuById(id));
        return "edit-suat-chieu";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute SuatChieu sc, Model model) {
        suatChieuService.updateSuatChieu(sc);
        model.addAttribute("listSuatChieu", suatChieuService.getAllSuatChieu());
        return "list-suat-chieu";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        suatChieuService.deleteSuatChieu(id);
        model.addAttribute("listSuatChieu", suatChieuService.getAllSuatChieu());
        return "list-suat-chieu";
    }
}
