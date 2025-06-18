package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.Ve30Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/ve")
public class Ve30Controller {

    @Autowired
    private Ve30Service veService;

    /**
     * Hiển thị form nhập mã khách hàng để kiểm tra vé sắp chiếu.
     */
    @GetMapping("/check")
    public String showCheckForm(Model model) {
        return "ve/check-form"; // templates/ve/check-form.html
    }

    /**
     * Xử lý submit form: lấy maKhachHang, truy vấn và kiểm tra.
     */
    @PostMapping("/check")
    public String processCheck(@RequestParam("maKhachHang") String maKhachHangStr, Model model) {
        int maKhachHang;
        try {
            maKhachHang = Integer.parseInt(maKhachHangStr);
        } catch (NumberFormatException ex) {
            model.addAttribute("error", "Mã khách hàng không hợp lệ");
            return "ve/check-form";
        }

        // 1. Lấy danh sách vé của khách
        List<Ve> danhSachVe = veService.getVeListByKhachHang(maKhachHang);
        model.addAttribute("danhSachVe", danhSachVe);

        // 2. Kiểm tra vé sắp chiếu
        List<Ve> sapChieuList = veService.kiemTraVeSapChieu(danhSachVe);
        model.addAttribute("sapChieuList", sapChieuList);

        // Thêm giờ hiện tại để so sánh/hiển thị
        model.addAttribute("now", java.time.LocalDateTime.now());
        model.addAttribute("maKhachHang", maKhachHang);

        return "ve/check-result"; // templates/ve/check-result.html
    }

    /**
     * Nếu muốn GET trực tiếp với path variable:
     */
    @GetMapping("/check/{maKhachHang}")
    public String processCheckGet(@PathVariable("maKhachHang") int maKhachHang, Model model) {
        List<Ve> danhSachVe = veService.getVeListByKhachHang(maKhachHang);
        model.addAttribute("danhSachVe", danhSachVe);
        List<Ve> sapChieuList = veService.kiemTraVeSapChieu(danhSachVe);
        model.addAttribute("sapChieuList", sapChieuList);
        model.addAttribute("now", java.time.LocalDateTime.now());
        model.addAttribute("maKhachHang", maKhachHang);
        return "ve/check-result";
    }
}
