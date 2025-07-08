package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.NguoiDung;
import com.example.servingwebcontent.service.NguoiDungService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    // Hàm kiểm tra session và role để bảo mật
    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return (username != null && role != null && (role.equals("admin") || role.equals("user")));
    }

    // Hiển thị danh sách người dùng và form trống
    @GetMapping({"", "/", "/view"})
    public String viewNguoiDung(HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            return "login";
        }
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung";
    }

    // Sửa người dùng (gửi dữ liệu và hiển thị lại danh sách + form điền sẵn)
    @GetMapping("/edit/{id}")
    public String editNguoiDung(@PathVariable int id, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", nguoiDung);
        return "nguoidung";
    }

    // Lưu người dùng (thêm hoặc cập nhật), và hiển thị lại danh sách
    @PostMapping("/save")
    public String saveNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        if (nguoiDung.getMaNguoiDung() == 0) {
            nguoiDungService.createNguoiDung(nguoiDung);
        } else {
            nguoiDungService.updateNguoiDung(nguoiDung);
        }
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung";
    }

    // Xoá người dùng và hiển thị lại danh sách
    @PostMapping("/delete/{id}")
    public String deleteNguoiDung(@PathVariable int id, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        nguoiDungService.deleteNguoiDung(id);
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung";
    }
}
