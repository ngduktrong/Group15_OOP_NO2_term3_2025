package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.TaiKhoan;
import com.example.servingwebcontent.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taikhoan")
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    @Autowired
    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    // Kiểm tra đăng nhập & quyền admin
    private boolean isAdmin(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return username != null && "admin".equalsIgnoreCase(role);
    }

    private String noAccess(Model model) {
        model.addAttribute("message", "Bạn cần đăng nhập với quyền ADMIN để truy cập chức năng này.");
        return "login";
    }

    // Hiển thị danh sách tài khoản
    @GetMapping({"", "/", "/view"})
    public String viewTaiKhoan(Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        List<TaiKhoan> list = taiKhoanService.getAllTaiKhoan();
        model.addAttribute("taiKhoans", list);
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "taikhoan";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{username}")
    public String editTaiKhoan(@PathVariable("username") String username, Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        TaiKhoan tk = taiKhoanService.getAllTaiKhoan().stream()
                .filter(t -> t.getTenDangNhap().equals(username))
                .findFirst()
                .orElse(new TaiKhoan());

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", tk);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "taikhoan";
    }

    // Thêm mới
    @PostMapping("/add")
    public String addTaiKhoan(@ModelAttribute TaiKhoan taiKhoan, Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        String message;
        boolean success = taiKhoanService.createTaiKhoan(taiKhoan);
        message = success ? "Thêm tài khoản thành công!" : " Không thể thêm tài khoản!";

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "taikhoan";
    }

    // Cập nhật
    @PostMapping("/edit/{username}")
    public String updateTaiKhoan(@PathVariable("username") String username,
                                 @ModelAttribute TaiKhoan taiKhoan,
                                 Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        taiKhoan.setTenDangNhap(username);
        String message;
        boolean success = taiKhoanService.updateTaiKhoan(taiKhoan);
        message = success ? "Cập nhật tài khoản thành công!" : " Không thể cập nhật tài khoản!";

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "taikhoan";
    }

    //  Xoá
    @PostMapping("/delete/{username}")
    public String deleteTaiKhoan(@PathVariable("username") String username, Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        String message;
        boolean success = taiKhoanService.deleteTaiKhoan(username);
        message = success ? "Xoá tài khoản thành công!" : "Không thể xoá tài khoản!";

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "taikhoan";
    }
}
