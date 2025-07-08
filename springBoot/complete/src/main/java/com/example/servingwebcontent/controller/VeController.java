package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ve")
public class VeController {

    @Autowired
    private VeService veService;

    // Kiểm tra đã đăng nhập chưa
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("username") != null;
    }

    //  Kiểm tra quyền admin
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "admin".equalsIgnoreCase(role);
    }

    // Trả về nếu không có quyền
    private String noAccess(Model model) {
        model.addAttribute("message", " Bạn cần đăng nhập với quyền ADMIN để truy cập chức năng này.");
        return "login";
    }

    // Hiển thị danh sách + form thêm
    @GetMapping
    public String viewVe(Model model, HttpSession session) {
        if (!isLoggedIn(session) || !isAdmin(session)) return noAccess(model);

        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("ve", new Ve());
        model.addAttribute("editMode", false);
        model.addAttribute("tongVeDaBan", veService.getSoVeDaThanhToan());
        return "ve";
    }

    // Thêm vé mới
    @PostMapping("/add")
    public String addVe(@ModelAttribute Ve ve, Model model, HttpSession session) {
        if (!isLoggedIn(session) || !isAdmin(session)) return noAccess(model);

        if (ve.getTrangThai() == null || ve.getTrangThai().isEmpty()) {
            ve.setTrangThai("Chưa thanh toán");
        }

        boolean success = veService.createVe(ve);
        String message = success
                ? "Thêm vé thành công!"
                : "Không thể thêm vé: Ghế đã được đặt hoặc mã không hợp lệ!";

        model.addAttribute("message", message);
        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("ve", new Ve());
        model.addAttribute("editMode", false);
        model.addAttribute("tongVeDaBan", veService.getSoVeDaThanhToan());
        return "ve";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String editVe(@PathVariable("id") int maVe, Model model, HttpSession session) {
        if (!isLoggedIn(session) || !isAdmin(session)) return noAccess(model);

        Ve ve = veService.getVeById(maVe);
        String message = "";

        if (ve == null) {
            message = "Không tìm thấy vé để sửa!";
            ve = new Ve();
        }

        model.addAttribute("message", message);
        model.addAttribute("ve", ve);
        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("editMode", true);
        model.addAttribute("tongVeDaBan", veService.getSoVeDaThanhToan());
        return "ve";
    }

    // Cập nhật vé
    @PostMapping("/edit")
    public String updateVe(@ModelAttribute Ve ve, Model model, HttpSession session) {
        if (!isLoggedIn(session) || !isAdmin(session)) return noAccess(model);

        boolean success = veService.updateVe(ve);
        String message = success
                ? "Cập nhật vé thành công!"
                : "Không thể cập nhật vé: Không được sửa mã suất chiếu hoặc vé đã thanh toán!";

        model.addAttribute("message", message);
        model.addAttribute("ve", new Ve());
        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("editMode", false);
        model.addAttribute("tongVeDaBan", veService.getSoVeDaThanhToan());
        return "ve";
    }

    // Xoá vé
    @PostMapping("/delete/{id}")
    public String deleteVe(@PathVariable("id") int maVe, Model model, HttpSession session) {
        if (!isLoggedIn(session) || !isAdmin(session)) return noAccess(model);

        boolean success = veService.deleteVe(maVe);
        String message = success
                ? "Xoá vé thành công!"
                : "Không thể xoá vé: Mã vé không tồn tại!";

        model.addAttribute("message", message);
        model.addAttribute("ve", new Ve());
        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("editMode", false);
        model.addAttribute("tongVeDaBan", veService.getSoVeDaThanhToan());
        return "ve";
    }

    //  Thanh toán vé
    @PostMapping("/pay")
    public String payVe(@RequestParam("maVe") int maVe, Model model, HttpSession session) {
        if (!isLoggedIn(session) || !isAdmin(session)) return noAccess(model);

        boolean success = veService.markVeAsPaid(maVe);
        String message = success
                ? "Thanh toán vé thành công! Ngày đặt và hóa đơn đã được cập nhật."
                : "Thanh toán thất bại: Không tìm thấy vé hoặc lỗi hệ thống!";

        model.addAttribute("message", message);
        model.addAttribute("ve", new Ve());
        model.addAttribute("veList", veService.getAllVe());
        model.addAttribute("editMode", false);
        model.addAttribute("tongVeDaBan", veService.getSoVeDaThanhToan());
        return "ve";
    }
}
