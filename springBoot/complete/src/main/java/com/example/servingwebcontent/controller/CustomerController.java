package com.example.servingwebcontent.controller;

import jakarta.servlet.http.HttpSession;
import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhimService;
import com.example.servingwebcontent.service.PhongChieuService;
import com.example.servingwebcontent.service.SuatChieuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private PhimService phimService;

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private PhongChieuService phongChieuService;

    // 1. Hiển thị danh sách phim
    @GetMapping("/movies")
    public String listMoviesForCustomer(Model model, HttpSession session) {
        // Kiểm tra đăng nhập
        if (session.getAttribute("username") == null || 
            session.getAttribute("maKhachHang") == null) {
            return "redirect:/login";
        }

        // +++ THÊM: lấy mã khách hàng từ session +++
        Integer maKhachHang = (Integer) session.getAttribute("maKhachHang");

        // Thông tin user
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("maKhachHang", maKhachHang);

        // Danh sách phim
        List<Phim> phims = phimService.getAllPhim();
        model.addAttribute("phims", phims);
        
        return "customer/movies";
    }

    // 2. Chọn phim -> Hiển thị phòng chiếu
    @GetMapping("/movies/select/{maPhim}")
    public String listPhongByPhim(
            @PathVariable int maPhim,
            HttpSession session,
            Model model) {
        
        // Kiểm tra đăng nhập
        if (session.getAttribute("username") == null || 
            session.getAttribute("maKhachHang") == null) {
            return "login";
        }

        // +++ THÊM: lấy mã khách hàng từ session +++
        Integer maKhachHang = (Integer) session.getAttribute("maKhachHang");

        // Thông tin user
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("maKhachHang", maKhachHang);

        // Lấy thông tin phim đã chọn
        Phim selectedPhim = phimService.getPhimById(maPhim);
        if (selectedPhim == null) {
            return "customer/movies";
        }
        model.addAttribute("selectedPhim", selectedPhim);
        session.setAttribute("selectedPhim", selectedPhim);

        // Lấy danh sách phòng có chiếu phim này
        List<SuatChieu> suatList = suatChieuService.getByMaPhim(maPhim);
        Set<Integer> phongIds = suatList.stream()
            .map(SuatChieu::getMaPhong)
            .collect(Collectors.toSet());
        
        List<PhongChieu> phongList = phongChieuService.getAllPhongChieu().stream()
            .filter(p -> phongIds.contains(p.getMaPhong()))
            .collect(Collectors.toList());

        model.addAttribute("listPhong", phongList);
        return "view-phong-customer";
    }
}
