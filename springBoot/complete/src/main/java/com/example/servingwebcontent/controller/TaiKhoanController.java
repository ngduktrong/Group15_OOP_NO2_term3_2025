package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.TaiKhoan;
import com.example.servingwebcontent.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/taikhoan")
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    @Autowired
    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "edit", required = false) String username,
                        @RequestParam(value = "message", required = false) String message) {

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);

        if (username != null) {
            model.addAttribute("taiKhoan", taiKhoanService.getAllTaiKhoan().stream()
                    .filter(t -> t.getTenDangNhap().equals(username))
                    .findFirst()
                    .orElse(new TaiKhoan()));
            model.addAttribute("editMode", true);
        }

        if (message != null) {
            model.addAttribute("message", message);
        }

        return "taikhoan";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TaiKhoan taiKhoan) {
        if (!taiKhoanService.createTaiKhoan(taiKhoan)) {
            return "redirect:/taikhoan?message=Không thể thêm tài khoản!";
        }
        return "taikhoan";
    }

    @PostMapping("/edit/{username}")
    public String edit(@PathVariable("username") String username, @ModelAttribute TaiKhoan taiKhoan) {
        taiKhoan.setTenDangNhap(username);
        if (!taiKhoanService.updateTaiKhoan(taiKhoan)) {
            return "redirect:/taikhoan?message=Không thể cập nhật tài khoản!";
        }
        return "redirect:/taikhoan";
    }

    @PostMapping("/delete/{username}")
    public String delete(@PathVariable("username") String username) {
        if (!taiKhoanService.deleteTaiKhoan(username)) {
            return "redirect:/taikhoan?message=Không thể xóa tài khoản!";
        }
        return "redirect:/taikhoan";
    }
}
