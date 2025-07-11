package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ghe")
public class GheController {

    private final GheService gheService;

    @Autowired
    public GheController(GheService gheService) {
        this.gheService = gheService;
    }

    // Kiểm tra session để đảm bảo bảo mật
    private boolean checkSessionRole(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        return (username != null && role != null && (role.equals("admin") || role.equals("user")));
    }

    // Hiển thị danh sách ghế và form thêm mới
    @GetMapping({"", "/", "/view"})
    public String viewGhe(HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            return "login";
        }
        model.addAttribute("gheList", gheService.getAllGhe());
        model.addAttribute("ghe", new Ghe()); // form thêm từng ghế
        model.addAttribute("message", "");
        return "ghe";
    }

    // Thêm ghế đơn
    @PostMapping("/add")
    public String addGhe(@ModelAttribute Ghe ghe, HttpSession session, Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        boolean success = gheService.createGhe(ghe);
        String message = success
                ? "Thêm ghế thành công!"
                : "Thêm thất bại: Phòng không hợp lệ, ghế đã tồn tại, hoặc đã đủ ghế tối đa!";

        model.addAttribute("gheList", gheService.getAllGhe());
        model.addAttribute("ghe", new Ghe());
        model.addAttribute("message", message);
        return "ghe";
    }

    //  Thêm nhiều ghế theo hàng và số ghế mỗi hàng
    @PostMapping("/add-auto")
    public String addAutoGhe(@RequestParam("maPhong") int maPhong,
                             @RequestParam("soHang") int soHang,
                             @RequestParam("soGheMoiHang") int soGheMoiHang,
                             HttpSession session,
                             Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }

        gheService.createAuto(maPhong, soHang, soGheMoiHang);

        model.addAttribute("gheList", gheService.getAllGhe());
        model.addAttribute("ghe", new Ghe());
        model.addAttribute("message", "Đã thêm ghế hàng loạt (nếu hợp lệ).");
        return "ghe";
    }

    // Xoá ghế theo soGhe và maPhong
    @PostMapping("/delete")
    public String deleteGhe(@RequestParam("soGhe") String soGhe,
                            @RequestParam("maPhong") int maPhong,
                            HttpSession session,
                            Model model) {
        if (!checkSessionRole(session)) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "login";
        }
        boolean success = gheService.deleteGhe(soGhe, maPhong);
        String message = success
                ? "Xoá ghế thành công!"
                : "Không thể xoá ghế (ghế không tồn tại)!";

        model.addAttribute("gheList", gheService.getAllGhe());
        model.addAttribute("ghe", new Ghe());
        model.addAttribute("message", message);
        return "ghe";
    }
}
