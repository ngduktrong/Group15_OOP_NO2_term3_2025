package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.NhanVien;
import com.example.servingwebcontent.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
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

    // Kiểm tra đăng nhập và role
    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return (username != null && role != null && (role.equals("admin") || role.equals("user")));
    }

    @GetMapping({"", "/", "/view"})
    public String viewNhanVien(HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            return "login";
        }
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();

        model.addAttribute("listNhanVien", nhanVienList);
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("message", "");
        model.addAttribute("totalEmployees", nhanVienList.size()); // Tổng số nhân viên

        return "nhanvien";
    }

    @GetMapping("/edit/{id}")
    public String editNhanVien(@PathVariable int id, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();

        model.addAttribute("listNhanVien", nhanVienList);
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("message", "");
        model.addAttribute("totalEmployees", nhanVienList.size());

        return "nhanvien";
    }

    @PostMapping("/save")
    public String saveNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        boolean success;
        String message;

        NhanVien existing = nhanVienService.getNhanVienById(nhanVien.getMaNguoiDung());
        if (existing == null) {
            success = nhanVienService.createNhanVien(nhanVien);
            message = success ? "Thêm nhân viên thành công!" : "Thêm nhân viên thất bại!";
        } else {
            success = nhanVienService.updateNhanVien(nhanVien);
            message = success ? "Cập nhật nhân viên thành công!" : " Cập nhật thất bại!";
        }

        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();

        model.addAttribute("listNhanVien", nhanVienList);
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("message", message);
        model.addAttribute("totalEmployees", nhanVienList.size());

        return "nhanvien";
    }

    @PostMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable int id, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        boolean success = nhanVienService.deleteNhanVien(id);
        String message = success ? "🗑 Đã xoá nhân viên." : " Xoá thất bại!";

        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();

        model.addAttribute("listNhanVien", nhanVienList);
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("message", message);
        model.addAttribute("totalEmployees", nhanVienList.size());

        return "nhanvien";
    }
}
