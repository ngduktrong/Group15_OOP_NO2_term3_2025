package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suatchieu")
public class SuatChieuPageController {

    @Autowired
    private SuatChieuService suatChieuService;

    // Hiển thị danh sách (admin)
    @GetMapping("/admin")
    public String adminList(Model model) {
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        return "admin/suatchieu";
    }

    // Hiển thị form thêm
    @GetMapping("/admin/add")
    public String showAddForm(Model model) {
        model.addAttribute("suatchieu", new SuatChieu());
        return "admin/addsuatchieu";
    }

    // Xử lý form thêm (form-data)
    @PostMapping("/admin/add")
    public String createSuatChieu(@ModelAttribute SuatChieu suatchieu, Model model) {
        suatChieuService.createSuatChieu(suatchieu);
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        return "admin/suatchieu";
    }

    // Hiển thị form sửa
    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        SuatChieu sc = suatChieuService.getSuatChieuById(id);
        model.addAttribute("suatchieu", sc);
        return "admin/editsuatchieu";
    }

    // Xử lý form sửa (form-data)
    @PostMapping("/admin/edit/{id}")
    public String updateSuatChieu(@PathVariable("id") int id,
                                  @ModelAttribute SuatChieu suatchieu,
                                  Model model) {
        suatchieu.setMaSuatChieu(id);
        suatChieuService.updateSuatChieu(suatchieu);
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        return "admin/suatchieu";
    }

    // Xử lý xóa
    @GetMapping("/admin/delete/{id}")
    public String deleteSuatChieu(@PathVariable("id") int id, Model model) {
        suatChieuService.deleteSuatChieu(id);
        model.addAttribute("scs", suatChieuService.getAllSuatChieu());
        return "admin/suatchieu";
    }

    // JSON endpoint cho JS khách hàng
    @GetMapping("/api/all")
    @ResponseBody
    public List<SuatChieu> getAllJson() {
        return suatChieuService.getAllSuatChieu();
    }

    // Trang khách hàng
    @GetMapping("/customer")
    public String customerPage() {
        return "customer/suatchieu";
    }
}
