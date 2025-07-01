package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.NhanVien;
import com.example.servingwebcontent.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping({"", "/", "/view"})
    public String viewNhanVien(Model model) {
        model.addAttribute("listNhanVien", nhanVienService.getAllNhanVien());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("message", "");
        return "nhanvien";
    }

    @GetMapping("/edit/{id}")
    public String editNhanVien(@PathVariable int id, Model model) {
        model.addAttribute("listNhanVien", nhanVienService.getAllNhanVien());
        model.addAttribute("nhanVien", nhanVienService.getNhanVienById(id));
        model.addAttribute("message", "");
        return "nhanvien";
    }

    @PostMapping("/save")
    public String saveNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien, Model model) {
        boolean success;
        String message;

        NhanVien existing = nhanVienService.getNhanVienById(nhanVien.getMaNguoiDung());
        if (existing == null) {
            success = nhanVienService.createNhanVien(nhanVien);
            message = success ? "✅ Thêm nhân viên thành công!" : "❌ Thêm nhân viên thất bại!";
        } else {
            success = nhanVienService.updateNhanVien(nhanVien);
            message = success ? "✅ Cập nhật nhân viên thành công!" : "❌ Cập nhật thất bại!";
        }

        model.addAttribute("listNhanVien", nhanVienService.getAllNhanVien());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("message", message);
        return "nhanvien";
    }

    @PostMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable int id, Model model) {
        boolean success = nhanVienService.deleteNhanVien(id);
        String message = success ? "🗑 Đã xoá nhân viên." : "❌ Xoá thất bại!";

        model.addAttribute("listNhanVien", nhanVienService.getAllNhanVien());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("message", message);
        return "nhanvien";
    }
}
