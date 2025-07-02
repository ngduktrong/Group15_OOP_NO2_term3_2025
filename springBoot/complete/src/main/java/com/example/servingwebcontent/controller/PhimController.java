package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.service.PhimService;
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

    // Hiển thị danh sách phim + form thêm mới
    @GetMapping({"", "/", "/view"})
    public String viewPhim(Model model) {
        List<Phim> list = phimService.getAllPhim();
        model.addAttribute("phims", list);
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "phim";
    }

    // Hiển thị form cập nhật phim
    @GetMapping("/edit/{id}")
    public String editPhim(@PathVariable("id") int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        if (phim == null) phim = new Phim();

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", phim);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "phim";
    }

    // Xử lý thêm mới phim
    @PostMapping("/add")
    public String addPhim(@ModelAttribute Phim phim, Model model) {
        phimService.createPhim(phim);
        logger.info("🟢 [THÊM] Phim mới: {}", phim.getTenPhim());

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Thêm phim thành công!");
        return "phim";
    }

    // Xử lý cập nhật phim
    @PostMapping("/edit/{id}")
    public String updatePhim(@PathVariable("id") int id,
                             @ModelAttribute Phim phim, Model model) {
        phim.setMaPhim(id);
        phimService.updatePhim(phim);
        logger.info("🟡 [SỬA] Phim ID: {} - Tên mới: {}", id, phim.getTenPhim());

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Cập nhật phim thành công!");
        return "phim";
    }

    // Xử lý xoá phim
    @PostMapping("/delete/{id}")
    public String deletePhim(@PathVariable("id") int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        phimService.deletePhim(id);
        logger.warn("🔴 [XOÁ] Phim ID: {} - Tên: {}", id, phim != null ? phim.getTenPhim() : "Không rõ");

        model.addAttribute("phims", phimService.getAllPhim());
        model.addAttribute("phim", new Phim());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Xoá phim thành công!");
        return "phim";
    }
}
