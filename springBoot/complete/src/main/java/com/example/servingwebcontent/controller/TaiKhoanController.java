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

    // Hiển thị danh sách và form rỗng
    @GetMapping({"", "/", "/view"})
    public String viewTaiKhoan(Model model) {
        List<TaiKhoan> list = taiKhoanService.getAllTaiKhoan();
        model.addAttribute("taiKhoans", list);
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "taikhoan";
    }

    // Hiển thị lại danh sách và form đã điền khi sửa
    @GetMapping("/edit/{username}")
    public String editTaiKhoan(@PathVariable("username") String username, Model model) {
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

    // Thêm tài khoản mới
    @PostMapping("/add")
    public String addTaiKhoan(@ModelAttribute TaiKhoan taiKhoan, Model model) {
        String message;
        boolean success = taiKhoanService.createTaiKhoan(taiKhoan);
        message = success ? "✅ Thêm tài khoản thành công!" : "❌ Không thể thêm tài khoản!";

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "taikhoan";
    }

    // Cập nhật tài khoản
    @PostMapping("/edit/{username}")
    public String updateTaiKhoan(@PathVariable("username") String username,
                                 @ModelAttribute TaiKhoan taiKhoan, Model model) {
        taiKhoan.setTenDangNhap(username);
        String message;
        boolean success = taiKhoanService.updateTaiKhoan(taiKhoan);
        message = success ? "✅ Cập nhật tài khoản thành công!" : "❌ Không thể cập nhật tài khoản!";

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "taikhoan";
    }

    // Xoá tài khoản
    @PostMapping("/delete/{username}")
    public String deleteTaiKhoan(@PathVariable("username") String username, Model model) {
        String message;
        boolean success = taiKhoanService.deleteTaiKhoan(username);
        message = success ? "✅ Xoá tài khoản thành công!" : "❌ Không thể xoá tài khoản!";

        model.addAttribute("taiKhoans", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "taikhoan";
    }
}
