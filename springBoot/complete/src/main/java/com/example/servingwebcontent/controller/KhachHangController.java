package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.KhachHang;
import com.example.servingwebcontent.service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    // Hàm kiểm tra session và vai trò (admin hoặc user) cho bảo mật
    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return (username != null && role != null && (role.equals("admin") || role.equals("user")));
    }

    // Hiển thị danh sách và form thêm, kiểm tra session
    @GetMapping({"", "/", "/view"})
    public String viewKhachHang(HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            return "login";
        }

        List<KhachHang> list = khachHangService.getAllKhachHang();
        model.addAttribute("listKhachHang", list);
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "khachhang";
    }

    // Hiển thị form sửa, kiểm tra session
    @GetMapping("/edit/{id}")
    public String editKhachHang(@PathVariable int id, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            return "login";
        }

        KhachHang kh = khachHangService.getKhachHangById(id);
        model.addAttribute("khachHang", kh);
        model.addAttribute("listKhachHang", khachHangService.getAllKhachHang());
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "khachhang";
    }

    // Thêm khách hàng (có xử lý lỗi nếu không đúng vai trò)
    @PostMapping("/add")
    public String addKhachHang(@ModelAttribute("khachHang") KhachHang khachHang, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }

        String message;
        try {
            boolean success = khachHangService.createKhachHang(khachHang);
            message = success ? "Thêm khách hàng thành công!" : " Không thể thêm khách hàng!";
        } catch (IllegalArgumentException e) {
            message = "❌ " + e.getMessage();
        } catch (Exception e) {
            message = "Lỗi không xác định khi thêm khách hàng!";
        }

        model.addAttribute("listKhachHang", khachHangService.getAllKhachHang());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "khachhang";
    }

    // Cập nhật khách hàng
    @PostMapping("/update")
    public String updateKhachHang(@ModelAttribute("khachHang") KhachHang khachHang, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }

        boolean success = khachHangService.updateKhachHang(khachHang);
        String message = success ? " Cập nhật khách hàng thành công!" : " Không thể cập nhật khách hàng!";
        model.addAttribute("listKhachHang", khachHangService.getAllKhachHang());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "khachhang";
    }

    // Xoá khách hàng
    @PostMapping("/delete/{id}")
    public String deleteKhachHang(@PathVariable int id, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }

        boolean success = khachHangService.deleteKhachHang(id);
        String message = success ? " Xóa khách hàng thành công!" : " Không thể xóa khách hàng!";
        model.addAttribute("listKhachHang", khachHangService.getAllKhachHang());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "khachhang";
    }
}
