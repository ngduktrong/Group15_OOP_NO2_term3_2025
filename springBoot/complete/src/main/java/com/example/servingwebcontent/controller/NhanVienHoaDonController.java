package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien/hoadon")
public class NhanVienHoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return username != null && role != null && (role.equals("admin") || role.equals("user"));
    }

    @GetMapping
    public String listHoaDon(HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            return "login";
        }
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        model.addAttribute("message", "");
        return "hoadon";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon-add";
    }

    @PostMapping("/add")
    public String addHoaDon(HttpSession session, @ModelAttribute HoaDon hoaDon, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        int maHoaDon = hoaDonService.createHoaDon(hoaDon);
        String message = (maHoaDon != -1)
                ? "Thêm hóa đơn thành công với mã: " + maHoaDon
                : "Thêm hóa đơn thất bại!";

        model.addAttribute("message", message);
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(HttpSession session, @PathVariable int id, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        if (hoaDon == null) {
            model.addAttribute("message", "Không tìm thấy hóa đơn.");
            model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
            model.addAttribute("hoaDon", new HoaDon());
            return "hoadon";
        }
        model.addAttribute("hoaDon", hoaDon);
        return "hoadon-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateHoaDon(HttpSession session, @PathVariable int id, @ModelAttribute HoaDon hoaDon, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        hoaDon.setMaHoaDon(id);
        boolean success = hoaDonService.updateHoaDon(hoaDon);
        String message = success ? "Cập nhật hóa đơn thành công!" : "Cập nhật hóa đơn thất bại!";

        model.addAttribute("message", message);
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }

    @GetMapping("/delete/{id}")
    public String deleteHoaDon(HttpSession session, @PathVariable int id, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        boolean success = hoaDonService.deleteHoaDon(id);
        String message = success ? "Xóa hóa đơn thành công!" : "Xóa hóa đơn thất bại!";

        model.addAttribute("message", message);
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }
}
