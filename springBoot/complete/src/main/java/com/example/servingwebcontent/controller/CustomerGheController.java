package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.GheService;
import com.example.servingwebcontent.service.SuatChieuService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Arrays;

@Controller
@RequestMapping("/customer/ghe")
public class CustomerGheController {

    @Autowired
    private GheService gheService;

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private com.example.servingwebcontent.service.PhimService phimService;
    @Autowired
    private com.example.servingwebcontent.service.VeService veService; // Thêm service

    @GetMapping("/{maPhong}")
    public String listGheByPhong(
        @PathVariable int maPhong,
        @RequestParam int maSuatChieu,
        Model model) {
    
    List<Ghe> list = gheService.getByMaPhong(maPhong);
    SuatChieu suatChieu = suatChieuService.getSuatChieuById(maSuatChieu);

    // 👉 Lấy danh sách ghế đã được đặt (với TrangThai = 'paid') cho suất chiếu này
    List<String> gheDaDat = veService.getSoGheDaDat(maSuatChieu); // Dạng List<String>

    model.addAttribute("listGhe", list);
    model.addAttribute("gheDaDat", gheDaDat); // 👈 truyền sang view
    model.addAttribute("maPhong", maPhong);
    model.addAttribute("suatChieu", suatChieu);

    return "list-ghe-customer";
    }


    @PostMapping("/chon-ghe")
        public String processSeatSelection(
        @RequestParam int maSuatChieu,
        @RequestParam String[] soGhe,
        @RequestParam int maPhong,
        HttpSession session,
        Model model) {
    
    // Kiểm tra đăng nhập
    if (session.getAttribute("maKhachHang") == null) {
        return "redirect:/login";
    }

    // Kiểm tra có chọn ghế
    if (soGhe == null || soGhe.length == 0) {
        model.addAttribute("error", "Vui lòng chọn ít nhất 1 ghế");
        return "redirect:/customer/ghe/" + maPhong + "?maSuatChieu=" + maSuatChieu;
    }

    // Lấy thông tin cần thiết
    SuatChieu suatChieu = suatChieuService.getSuatChieuById(maSuatChieu);
    Phim phim = phimService.getPhimById(suatChieu.getMaPhim());
    double tongTien = soGhe.length * 50000;
    int maKhachHang = (int) session.getAttribute("maKhachHang");
    
    // Tạo đối tượng hóa đơn
    HoaDon hoaDon = new HoaDon();
    hoaDon.setMaKhachHang(maKhachHang);
    hoaDon.setTongTien(tongTien);
    
    // Thêm dữ liệu vào model
    model.addAttribute("hoaDon", hoaDon);
    model.addAttribute("selectedSeats", Arrays.asList(soGhe));
    model.addAttribute("suatChieu", suatChieu);
    model.addAttribute("phim", phim);
    model.addAttribute("maPhong", maPhong);
    model.addAttribute("tongTien", tongTien);
    
    
    
    return "list-hoadon-customer"; // Trả về trực tiếp view hóa đơn
}
}