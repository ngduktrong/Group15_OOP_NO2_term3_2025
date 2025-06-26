package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.TaiKhoan;
import com.example.servingwebcontent.service.TaiKhoanService;
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

    @GetMapping
    public String showTaiKhoanPage(Model model,
                                   @RequestParam(value = "edit", required = false) String tenDangNhap,
                                   @RequestParam(value = "message", required = false) String message) {
        List<TaiKhoan> list = taiKhoanService.getAllTaiKhoan();
        model.addAttribute("taiKhoans", list);

        if (tenDangNhap != null) {
            TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByUsername(tenDangNhap);
            if (taiKhoan != null) {
                model.addAttribute("taiKhoan", taiKhoan);
                model.addAttribute("editMode", true);
            } else {
                model.addAttribute("taiKhoan", new TaiKhoan());
                model.addAttribute("editMode", false);
                model.addAttribute("message", "Không tìm thấy tài khoản để chỉnh sửa!");
            }
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
        tk.setTenDangNhap(username); // đảm bảo không thay đổi username
        taiKhoanService.updateTaiKhoan(tk);
        return "redirect:/taikhoan";
    }

    @PostMapping("/delete/{username}")
    public String deleteTaiKhoan(@PathVariable("username") String username) {
        taiKhoanService.deleteTaiKhoan(username);
        return "redirect:/taikhoan";
    }
}
