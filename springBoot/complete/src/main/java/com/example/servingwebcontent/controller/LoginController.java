package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.TaiKhoan;
import com.example.servingwebcontent.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Value("${server.port}")
    private String serverPort;

    private final TaiKhoanService taiKhoanService;

    @Autowired
    public LoginController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @GetMapping("/login")
    public String loginForm(Model model,
                            @ModelAttribute("success") String successMessage,
                            @ModelAttribute("error") String errorMessage,
                            @ModelAttribute("usernameInput") String usernameInput,
                            @ModelAttribute("roleInput") String roleInput) {
        model.addAttribute("port", serverPort);
        model.addAttribute("success", successMessage);
        model.addAttribute("error", errorMessage);
        model.addAttribute("usernameInput", usernameInput);
        model.addAttribute("roleInput", roleInput);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                    @RequestParam String password,
                    @RequestParam String role,
                    Model model) {

    if (username.isEmpty() || password.isEmpty()) {
        model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin");
        model.addAttribute("usernameInput", username);
        model.addAttribute("roleInput", role);
        return "login";
    }

    TaiKhoan tk = taiKhoanService.getByUsername(username);

    if (tk != null && tk.getMatKhau().equals(password)) {
        if ("admin".equalsIgnoreCase(tk.getLoaiTaiKhoanAsString()) && "admin".equals(role)) {
            return "admin-dashboard";
        } else if ("user".equalsIgnoreCase(tk.getLoaiTaiKhoanAsString()) && "user".equals(role)) {
            return "user-dashboard";
        } else {
            model.addAttribute("error", "Vai trò không đúng.");
        }
    } else {
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu.");
    }

    model.addAttribute("usernameInput", username);
    model.addAttribute("roleInput", role);
    return "login";
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("port", serverPort);
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin");
            return "register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp");
            return "register";
        }

        if (password.length() < 6) {
            model.addAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự");
            return "register";
        }

        if ("admin".equalsIgnoreCase(username)) {
            model.addAttribute("error", "Tên đăng nhập 'admin' đã được sử dụng");
            return "register";
        }

        if (taiKhoanService.getByUsername(username) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "register";
        }

        TaiKhoan newUser = new TaiKhoan();
        newUser.setTenDangNhap(username);
        newUser.setMatKhau(password);
        newUser.setLoaiTaiKhoan("user");
        newUser.setMaNguoiDung(0);

        if (taiKhoanService.createTaiKhoan(newUser)) {
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Mời đăng nhập.");
            return "login";
        } else {
            model.addAttribute("error", "Không thể đăng ký. Vui lòng thử lại.");
            return "register";
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("username", "admin");
        model.addAttribute("role", "admin");
        return "admin-dashboard";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Model model) {
        model.addAttribute("username", "user");
        model.addAttribute("role", "user");
        return "user-dashboard";
    }
}
