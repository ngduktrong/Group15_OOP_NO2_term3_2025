package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.TaiKhoan;
import com.example.servingwebcontent.service.TaiKhoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taikhoan")
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @GetMapping
    public String showTaiKhoanPage(Model model,
                                   @RequestParam(value = "edit", required = false) String tenDangNhap,
                                   @RequestParam(value = "message", required = false) String message) {
        List<TaiKhoan> list = taiKhoanService.getAllTaiKhoan();
        model.addAttribute("taiKhoans", list);

        if (tenDangNhap != null) {
            model.addAttribute("taiKhoan", taiKhoanService.getTaiKhoanByUsername(tenDangNhap));
            model.addAttribute("editMode", true);
        } else {
            model.addAttribute("taiKhoan", new TaiKhoan());
            model.addAttribute("editMode", false);
        }

        if (message != null) {
            model.addAttribute("message", message);
        }

        return "taikhoan";
    }

    @PostMapping("/add")
    public String addTaiKhoan(@ModelAttribute TaiKhoan tk) {
        boolean success = taiKhoanService.createTaiKhoan(tk);
        if (!success) {
            return "redirect:/taikhoan?message=Tên đăng nhập đã tồn tại hoặc lỗi cơ sở dữ liệu!";
        }
        return "redirect:/taikhoan";
    }

    @PostMapping("/edit/{username}")
    public String editTaiKhoan(@PathVariable("username") String username,
                               @ModelAttribute TaiKhoan tk) {
        tk.setTenDangNhap(username); // khóa chính không thay đổi
        taiKhoanService.updateTaiKhoan(tk);
        return "redirect:/taikhoan";
    }

    @PostMapping("/delete/{username}")
    public String deleteTaiKhoan(@PathVariable("username") String username) {
        taiKhoanService.deleteTaiKhoan(username);
        return "redirect:/taikhoan";
    }
}
