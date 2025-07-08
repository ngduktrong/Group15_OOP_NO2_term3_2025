package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.service.PhimService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phim")
public class PhimController {

    private final PhimService phimService;
    private static final Logger logger = LoggerFactory.getLogger(PhimController.class);

    @Autowired
    public PhimController(PhimService phimService) {
        this.phimService = phimService;
    }

    private boolean hasAccess(HttpSession session) {
        Object roleObj = session.getAttribute("role");
        if (roleObj == null) {
            return false;
        }
        String role = roleObj.toString();
        return role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user");
    }

    private String noAccessPage(Model model) {
        model.addAttribute("message", "Bạn cần đăng nhập với quyền ADMIN hoặc USER để truy cập.");
        return "login";  // Hoặc "error/noAccess" nếu bạn có trang lỗi riêng
    }

    @GetMapping({"", "/", "/view"})
    public String viewPhim(Model model, HttpSession session) {
        if (!hasAccess(session)) {
            return noAccessPage(model);
        }

        List<Phim> list = phimService.getAllPhim();
        model.addAttribute("phims", list);
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "phim";
    }

    @GetMapping("/edit/{id}")
    public String editPhim(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!hasAccess(session)) {
            return noAccessPage(model);
        }

        Phim phim = phimService.getPhimById(id);
        if (phim == null) phim = new Phim();

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", phim);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "phim";
    }

    @PostMapping("/add")
    public String addPhim(@ModelAttribute Phim phim, Model model, HttpSession session) {
        if (!hasAccess(session)) {
            return noAccessPage(model);
        }

        boolean success = phimService.createPhim(phim);
        logger.info("[THÊM] Phim mới: {}", phim.getTenPhim());

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", success ? "Thêm phim thành công!" : "Thêm phim thất bại!");
        return "phim";
    }

    @PostMapping("/edit/{id}")
    public String updatePhim(@PathVariable("id") int id,
                             @ModelAttribute Phim phim, Model model, HttpSession session) {
        if (!hasAccess(session)) {
            return noAccessPage(model);
        }

        phim.setMaPhim(id);
        boolean success = phimService.updatePhim(phim);
        logger.info("[SỬA] Phim ID: {} - Tên mới: {}", id, phim.getTenPhim());

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", success ? "Cập nhật phim thành công!" : "Cập nhật phim thất bại!");
        return "phim";
    }

    @PostMapping("/delete/{id}")
    public String deletePhim(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!hasAccess(session)) {
            return noAccessPage(model);
        }

        Phim phim = phimService.getPhimById(id);
        boolean success = phimService.deletePhim(id);
        logger.warn("[XOÁ] Phim ID: {} - Tên: {}", id, phim != null ? phim.getTenPhim() : "Không rõ");

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", success ? "Xoá phim thành công!" : " Xoá phim thất bại!");
        return "phim";
    }
}
