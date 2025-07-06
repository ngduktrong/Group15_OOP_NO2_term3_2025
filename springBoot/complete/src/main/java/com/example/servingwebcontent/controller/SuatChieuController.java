package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suatchieu")
public class SuatChieuController {

    private final SuatChieuService suatChieuService;

    @Autowired
    public SuatChieuController(SuatChieuService suatChieuService) {
        this.suatChieuService = suatChieuService;
    }

    // ✅ Hiển thị danh sách và form thêm mới
    @GetMapping({"", "/", "/view"})
    public String viewSuatChieu(Model model) {
        List<SuatChieu> list = suatChieuService.getAllSuatChieu();
        model.addAttribute("suatchieuList", list);
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "suatchieu";
    }

    // ✅ Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String editSuatChieu(@PathVariable("id") int id, Model model) {
        SuatChieu sc = suatChieuService.getSuatChieuById(id);
        if (sc == null) sc = new SuatChieu();

        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", sc);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "suatchieu";
    }

    // ✅ Thêm mới suất chiếu (kiểm tra logic trong Service)
    @PostMapping("/add")
    public String addSuatChieu(@ModelAttribute SuatChieu suatchieu, Model model) {
        boolean success = suatChieuService.createSuatChieu(suatchieu);

        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);

        if (success) {
            model.addAttribute("message", "✅ Thêm suất chiếu thành công!");
        } else {
            model.addAttribute("message", "❌ Thêm thất bại: Mã phim/mã phòng không hợp lệ hoặc phòng đã có suất chiếu trùng giờ!");
        }

        return "suatchieu";
    }

    // ✅ Cập nhật suất chiếu
    @PostMapping("/edit")
    public String updateSuatChieu(@ModelAttribute SuatChieu suatchieu, Model model) {
        suatChieuService.updateSuatChieu(suatchieu);
        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Cập nhật suất chiếu thành công!");
        return "suatchieu";
    }

    // ✅ Xoá suất chiếu
    @PostMapping("/delete/{id}")
    public String deleteSuatChieu(@PathVariable("id") int id, Model model) {
        suatChieuService.deleteSuatChieu(id);
        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "🗑️ Xoá suất chiếu thành công!");
        return "suatchieu";
    }
}
