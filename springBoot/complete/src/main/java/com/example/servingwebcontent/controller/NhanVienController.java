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

    // Hiển thị danh sách nhân viên và form trống
    @GetMapping({"", "/", "/view"})
    public String viewNhanVien(Model model) {
        List<NhanVien> ds = nhanVienService.getAllNhanVien();
        model.addAttribute("listNhanVien", ds);
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien";
    }

    // Sửa nhân viên
    @GetMapping("/edit/{id}")
    public String editNhanVien(@PathVariable int id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);
        List<NhanVien> ds = nhanVienService.getAllNhanVien();
        model.addAttribute("listNhanVien", ds);
        model.addAttribute("nhanVien", nhanVien);
        return "nhanvien";
    }

    // Lưu nhân viên (nếu chưa tồn tại thì thêm mới, nếu có thì cập nhật)
    @PostMapping("/save")
    public String saveNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        NhanVien existing = nhanVienService.getNhanVienById(nhanVien.getMaNguoiDung());
        if (existing == null) {
            nhanVienService.createNhanVien(nhanVien); // ✅ Tự cập nhật LoaiNguoiDung trong DAO
        } else {
            nhanVienService.updateNhanVien(nhanVien);
        }
        return "redirect:/nhanvien/view";
    }

    // Xoá nhân viên
    @PostMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable int id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhanvien/view";
    }
}
