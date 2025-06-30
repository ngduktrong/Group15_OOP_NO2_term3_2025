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

    // Sửa nhân viên (hiển thị lại form có sẵn dữ liệu + danh sách)
    @GetMapping("/edit/{id}")
    public String editNhanVien(@PathVariable int id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);
        List<NhanVien> ds = nhanVienService.getAllNhanVien();
        model.addAttribute("listNhanVien", ds);
        model.addAttribute("nhanVien", nhanVien);
        return "nhanvien";
    }

    // Lưu nhân viên (thêm hoặc cập nhật), hiển thị lại giao diện
    @PostMapping("/save")
    public String saveNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien, Model model) {
        NhanVien existing = nhanVienService.getNhanVienById(nhanVien.getMaNguoiDung());
        if (existing == null) {
            nhanVienService.createNhanVien(nhanVien);
        } else {
            nhanVienService.updateNhanVien(nhanVien);
        }

        List<NhanVien> ds = nhanVienService.getAllNhanVien();
        model.addAttribute("listNhanVien", ds);
        model.addAttribute("nhanVien", new NhanVien()); // reset form
        return "nhanvien";
    }

    // Xóa nhân viên và hiển thị lại danh sách + form trống
    @PostMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable int id, Model model) {
        nhanVienService.deleteNhanVien(id);

        List<NhanVien> ds = nhanVienService.getAllNhanVien();
        model.addAttribute("listNhanVien", ds);
        model.addAttribute("nhanVien", new NhanVien()); // reset form
        return "nhanvien";
    }
}
