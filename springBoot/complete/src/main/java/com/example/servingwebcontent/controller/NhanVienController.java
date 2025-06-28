package com.example.servingwebcontent.controller;
import com.example.servingwebcontent.models.NhanVien;
import com.example.servingwebcontent.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String listNhanVien(Model model) {
        List<NhanVien> list = nhanVienService.getAllNhanVien();
        model.addAttribute("listNhanVien", list);
        return "nhanvien"; // Thymeleaf view: nhanvien.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien-form";
    }

    @PostMapping("/save")
    public String saveNhanVien(@ModelAttribute("nhanVien") NhanVien nv, RedirectAttributes ra) {
        if (nhanVienService.getNhanVienById(nv.getMaNguoiDung()) == null) {
            nhanVienService.addNhanVien(nv);
            ra.addFlashAttribute("success", "Thêm nhân viên thành công!");
        } else {
            nhanVienService.updateNhanVien(nv);
            ra.addFlashAttribute("success", "Cập nhật nhân viên thành công!");
        }
        return "redirect:/nhanvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        NhanVien nv = nhanVienService.getNhanVienById(id);
        model.addAttribute("nhanVien", nv);
        return "nhanvien-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable("id") int id, RedirectAttributes ra) {
        nhanVienService.deleteNhanVien(id);
        ra.addFlashAttribute("success", "Xóa nhân viên thành công!");
        return "redirect:/nhanvien";
    }
}

