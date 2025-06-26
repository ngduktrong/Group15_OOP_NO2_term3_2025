package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.service.UserAiven;

@Controller
public class LoginController {

    @Value("${server.port}")
    private String serverPort;

    private final UserAiven userService;

    // Sử dụng dependency injection thay vì khởi tạo trực tiếp
    @Autowired
    public LoginController(UserAiven userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("port", serverPort);
        return "login"; // Đổi thành login.html để rõ ràng hơn
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        // Thêm kiểm tra input rỗng
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập tên đăng nhập và mật khẩu");
            model.addAttribute("port", serverPort);
            return "login";
        }

        // Kiểm tra tài khoản admin
        if ("admin".equals(username) && "123456".equals(password)) {
            return "redirect:/admin/dashboard"; // Sử dụng redirect thay vì trả về template trực tiếp
        }

        // Kiểm tra người dùng thông thường
        if (userService.authenticateUser(username, password)) {
            return "redirect:/user/dashboard"; // Sử dụng redirect
        }

        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        model.addAttribute("port", serverPort);
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
                           Model model) {

        // Kiểm tra input rỗng
        if (username == null || username.isEmpty() || 
            password == null || password.isEmpty() || 
            confirmPassword == null || confirmPassword.isEmpty()) {
            
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

        if (userService.registerUser(username, password)) {
            model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "login"; // Trả về trang login thay vì index
        } else {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "register";
        }
    }

    // Thêm các endpoint cho dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("username", "admin");
        model.addAttribute("role", "admin");
        return "admin-dashboard";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Model model) {
        // Lấy thông tin người dùng từ session hoặc security context
        model.addAttribute("username", "user");
        model.addAttribute("role", "user");
        return "user-dashboard";
    }
}