package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ve")
public class VeController {

    private final VeService veService;

    @Autowired
    public VeController(VeService veService) {
        this.veService = veService;
    }

    // ✅ Hiển thị danh sách vé và form thêm mới
    @GetMapping({"", "/", "/view"})
    public String viewVe(Model model,
                         @RequestParam(value = "message", required = false, defaultValue = "") String message) {
        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("ve", new Ve());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "ve";
    }

    // ✅ Hiển thị form sửa vé
    @GetMapping("/edit/{id}")
    public String editVe(@PathVariable("id") int maVe, Model model) {
        Ve ve = veService.getVeById(maVe);
        String message = "";

        if (ve == null) {
            message = "❌ Không tìm thấy vé để sửa!";
            ve = new Ve();
        }

        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("ve", ve);
        model.addAttribute("editMode", true);
        model.addAttribute("message", message);
        return "ve";
    }

    // ✅ Thêm vé mới (kiểm tra hợp lệ + trùng ghế)
    @PostMapping("/add")
    public String addVe(@ModelAttribute Ve ve, Model model) {
        boolean success = veService.createVe(ve);
        String message;

        if (success) {
            message = "✅ Thêm vé thành công!";
            model.addAttribute("ve", new Ve()); // reset form
        } else {
            // Trường hợp thất bại sẽ tự log bên service → thông báo chung
            message = "❌ Không thể thêm vé: Ghế đã được đặt hoặc mã không hợp lệ!";
            model.addAttribute("ve", ve); // giữ lại dữ liệu người dùng
        }

        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "ve";
    }

    // ✅ Cập nhật vé
    @PostMapping("/edit")
    public String updateVe(@ModelAttribute Ve ve, Model model) {
        boolean success = veService.updateVe(ve);
        String message;

        if (success) {
            message = "✅ Cập nhật vé thành công!";
            model.addAttribute("ve", new Ve());
        } else {
            message = "❌ Không thể cập nhật vé: Không được sửa mã suất chiếu hoặc vé đã thanh toán!";
            model.addAttribute("ve", ve);
        }

        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "ve";
    }

    // ✅ Xoá vé
    @PostMapping("/delete/{id}")
    public String deleteVe(@PathVariable("id") int maVe, Model model) {
        boolean success = veService.deleteVe(maVe);
        String message = success
                ? "✅ Xoá vé thành công!"
                : "❌ Không thể xoá vé: Mã vé không tồn tại!";

        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("ve", new Ve());
        model.addAttribute("editMode", false);
        model.addAttribute("message", message);
        return "ve";
    }
}
