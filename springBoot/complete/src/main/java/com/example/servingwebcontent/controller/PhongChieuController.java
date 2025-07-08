package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhongChieuService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phongchieu")
public class PhongChieuController {

    private final PhongChieuService phongChieuService;
    private static final Logger logger = LoggerFactory.getLogger(PhongChieuController.class);

    @Autowired
    public PhongChieuController(PhongChieuService phongChieuService) {
        this.phongChieuService = phongChieuService;
    }

    // Hàm kiểm tra session và role (admin hoặc user)
    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return (username != null && role != null && (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user")));
    }

    private String noAccessPage(Model model) {
        model.addAttribute("message", "⚠️ Bạn cần đăng nhập với quyền ADMIN hoặc USER để truy cập.");
        return "login";
    }

    @GetMapping({"", "/", "/view"})
    public String viewPhongChieu(Model model, HttpSession session) {
        if (!checkSessionRole(session)) {
            return noAccessPage(model);
        }
        List<PhongChieu> list = phongChieuService.getAllPhongChieu();
        model.addAttribute("phongList", list);
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "phongchieu";
    }

    @GetMapping("/edit/{id}")
    public String editPhong(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!checkSessionRole(session)) {
            return noAccessPage(model);
        }
        PhongChieu pc = phongChieuService.getPhongChieuById(id);
        if (pc == null) pc = new PhongChieu();

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", pc);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "phongchieu";
    }

    @PostMapping("/add")
    public String addPhong(@ModelAttribute PhongChieu phongchieu, Model model, HttpSession session) {
        if (!checkSessionRole(session)) {
            return noAccessPage(model);
        }

        phongChieuService.createPhongChieu(phongchieu);
        logger.info("[ADD] Thêm phòng chiếu: {}", phongchieu.getTenPhong());

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "Thêm phòng chiếu thành công!");
        return "phongchieu";
    }

    @PostMapping("/edit")
    public String updatePhong(@ModelAttribute PhongChieu phongchieu, Model model, HttpSession session) {
        if (!checkSessionRole(session)) {
            return noAccessPage(model);
        }

        phongChieuService.updatePhongChieu(phongchieu);
        logger.info(" [UPDATE] Cập nhật phòng chiếu (ID: {})", phongchieu.getMaPhong());

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "Cập nhật phòng chiếu thành công!");
        return "phongchieu";
    }

    @PostMapping("/delete/{id}")
    public String deletePhong(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!checkSessionRole(session)) {
            return noAccessPage(model);
        }

        PhongChieu phong = phongChieuService.getPhongChieuById(id);
        phongChieuService.deletePhongChieu(id);
        logger.warn("[DELETE] Đã xoá phòng chiếu có ID: {} - Tên: {}", id, phong != null ? phong.getTenPhong() : "Không rõ");

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "Xoá phòng chiếu thành công!");
        return "phongchieu";
    }
}
