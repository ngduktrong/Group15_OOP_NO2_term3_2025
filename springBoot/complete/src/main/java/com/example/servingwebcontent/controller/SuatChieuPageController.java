package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suatchieu")
public class SuatChieuPageController {

    @Autowired
    private SuatChieuService suatChieuService;

    // Trang quản lý (Nhân viên)
    @GetMapping("/nhanvien")
    public String staffPage(Model model) {
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        return "nhanvien/suatchieu";
    }

    @GetMapping("/nhanvien/add")
    public String showAddForm(Model model) {
        model.addAttribute("suatChieu", new SuatChieu());
        return "nhanvien/addsuatchieu";
    }

    @PostMapping("/nhanvien/add")
    public String addSuatChieu(@ModelAttribute SuatChieu suatChieu) {
        suatChieuService.createSuatChieu(suatChieu);
        return "redirect:/suatchieu/nhanvien";
    }

    @GetMapping("/nhanvien/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        SuatChieu sc = suatChieuService.getSuatChieuById(id);
        model.addAttribute("suatChieu", sc);
        return "nhanvien/editsuatchieu";
    }

    @PostMapping("/nhanvien/edit/{id}")
    public String editSuatChieu(@PathVariable("id") int id,
                                @ModelAttribute SuatChieu suatChieu) {
        suatChieu.setMaSuatChieu(id);
        suatChieuService.updateSuatChieu(suatChieu);
        return "redirect:/suatchieu/nhanvien";
    }

    @GetMapping("/nhanvien/delete/{id}")
    public String deleteSuatChieu(@PathVariable("id") int id) {
        suatChieuService.deleteSuatChieu(id);
        return "redirect:/suatchieu/nhanvien";
    }

    // Trang khách hàng
    @GetMapping("/customer")
    public String customerPage(Model model) {
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        return "customer/suatchieu";
    }

    @PostMapping("/customer/select")
    public String selectSuatChieu(@RequestParam("maSuatChieu") int maSuatChieu,
                                  Model model) {
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        model.addAttribute("selectedId", maSuatChieu);
        return "customer/suatchieu";
    }
}