package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.*;
import com.example.servingwebcontent.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CustomerSuatChieuController {

    @Autowired
    private SuatChieuService suatChieuService;
    
    @Autowired
    private GheService gheService;
    
    @Autowired
    private PhimService phimService;

    @GetMapping("/customer/suatchieu/book/{maSuatChieu}")
public String showSeatSelection(
        @PathVariable int maSuatChieu,
        HttpSession session,
        Model model) {
    
    // Kiểm tra đăng nhập
    if (session.getAttribute("maKhachHang") == null) {
        return "redirect:/login";
    }

    // Lấy thông tin suất chiếu
    SuatChieu suatChieu = suatChieuService.getSuatChieuById(maSuatChieu);
    if (suatChieu == null) {
        return "redirect:/customer/movies";
    }

    // Lấy danh sách ghế theo phòng
    List<Ghe> listGhe = gheService.getByMaPhong(suatChieu.getMaPhong());
    
    // Thêm dữ liệu vào model
    model.addAttribute("suatChieu", suatChieu);
    model.addAttribute("listGhe", listGhe);
    model.addAttribute("maPhong", suatChieu.getMaPhong());
    model.addAttribute("giaVe", 50000); // Giá vé cố định
    model.addAttribute("username", session.getAttribute("username"));
    model.addAttribute("maKhachHang", session.getAttribute("maKhachHang"));
    
    return "list-ghe-customer";
}
}