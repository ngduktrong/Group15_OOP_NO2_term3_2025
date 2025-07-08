package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
import jakarta.servlet.http.HttpSession;
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

    // Kiểm tra session và role
    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return username != null && role != null && (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user"));
    }

    private String noAccessPage(Model model) {
        model.addAttribute("message", " Bạn cần đăng nhập với quyền ADMIN hoặc USER để truy cập chức năng này.");
        return "login";
    }

    // Hiển thị danh sách và form thêm mới
    @GetMapping({"", "/", "/view"})
    public String viewSuatChieu(Model model, HttpSession session) {
        if (!checkSessionRole(session)) return noAccessPage(model);

        List<SuatChieu> list = suatChieuService.getAllSuatChieu();
        model.addAttribute("suatchieuList", list);
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "suatchieu";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String editSuatChieu(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!checkSessionRole(session)) return noAccessPage(model);

        SuatChieu sc = suatChieuService.getSuatChieuById(id);
        if (sc == null) sc = new SuatChieu();

        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", sc);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "suatchieu";
    }

    // Thêm mới suất chiếu (kiểm tra logic trong Service)
    @PostMapping("/add")
    public String addSuatChieu(@ModelAttribute SuatChieu suatchieu, Model model, HttpSession session) {
        if (!checkSessionRole(session)) return noAccessPage(model);

        boolean success = suatChieuService.createSuatChieu(suatchieu);

        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message",
                success ? "Thêm suất chiếu thành công!"
                        : "Thêm thất bại: Mã phim/mã phòng không hợp lệ hoặc phòng đã có suất chiếu trùng giờ!");
        return "suatchieu";
    }

    // Cập nhật suất chiếu
    @PostMapping("/edit")
    public String updateSuatChieu(@ModelAttribute SuatChieu suatchieu, Model model, HttpSession session) {
        if (!checkSessionRole(session)) return noAccessPage(model);

        suatChieuService.updateSuatChieu(suatchieu);

        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "Cập nhật suất chiếu thành công!");
        return "suatchieu";
    }

    // Xoá suất chiếu
    @PostMapping("/delete/{id}")
    public String deleteSuatChieu(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!checkSessionRole(session)) return noAccessPage(model);

        suatChieuService.deleteSuatChieu(id);

        model.addAttribute("suatchieuList", suatChieuService.getAllSuatChieu());
        model.addAttribute("suatchieu", new SuatChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "Xoá suất chiếu thành công!");
        return "suatchieu";
    }
}
