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
public class CustomerPhongChieuController {

    @Autowired
    private SuatChieuService suatChieuService;
    
    @Autowired
    private PhimService phimService;
    
    @Autowired
    private PhongChieuService phongChieuService;

    @GetMapping("/customer/phong/select/{maPhim}/{maPhong}")
public String showSuatChieuByPhongAndPhim(
        @PathVariable int maPhim,
        @PathVariable int maPhong,
        HttpSession session,
        Model model) {
    
    // Kiểm tra đăng nhập
    if (session.getAttribute("username") == null || 
        session.getAttribute("maKhachHang") == null) {
        return "login";
    }

    // Lấy thông tin phim và phòng
    Phim selectedPhim = phimService.getPhimById(maPhim);
    PhongChieu selectedPhong = phongChieuService.getPhongChieuById(maPhong);
    
    if (selectedPhim == null || selectedPhong == null) {
        return "customer/movies";
    }

    // Lưu mã phim và mã phòng vào session
    session.setAttribute("selectedPhim", selectedPhim);
    session.setAttribute("selectedPhong", selectedPhong);
    session.setAttribute("maPhim", maPhim); // Thêm dòng này
    session.setAttribute("maPhong", maPhong); // Thêm dòng này

    // Lấy danh sách suất chiếu theo phòng và phim
    List<SuatChieu> suatList = suatChieuService.getByMaPhongAndPhim(maPhong, maPhim);
    
    // Thêm dữ liệu vào model
    model.addAttribute("username", session.getAttribute("username"));
    model.addAttribute("maKhachHang", session.getAttribute("maKhachHang"));
    model.addAttribute("selectedPhim", selectedPhim);
    model.addAttribute("selectedPhong", selectedPhong);
    model.addAttribute("listSuatChieu", suatList);
    
    return "list-suat-chieu-customer";
}
}