package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;

import jakarta.servlet.http.HttpSession;

import com.example.servingwebcontent.service.GheService;
import com.example.servingwebcontent.service.PhimService;
import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.models.Phim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CustomerSuatChieuController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSuatChieuController.class);

    @Autowired
private SuatChieuService suatChieuService;

@Autowired
private GheService gheService;

@Autowired
private PhimService phimService;

/**
 * 4. Chọn suất chiếu -> hiển thị ghế và thông tin phim, suất chiếu
 */
@GetMapping("/customer/suatchieu/book/{maSuatChieu}")
public String bookGheTheoSuat(
        @PathVariable int maSuatChieu,
        HttpSession session,
        Model model) {
    
    try {
        // 1. Kiểm tra đăng nhập
        Integer maKhachHang = (Integer) session.getAttribute("maKhachHang");
        if (maKhachHang == null) {
            return "redirect:/login";
        }

        // 2. Lấy thông tin suất chiếu
        SuatChieu sc = suatChieuService.getSuatChieuById(maSuatChieu);
        if (sc == null) {
            logger.error("Không tìm thấy suất chiếu với ID: {}", maSuatChieu);
            model.addAttribute("error", "Suất chiếu không tồn tại");
            return "error";
        }

        // 3. Lấy thông tin phòng và ghế
        int maPhong = sc.getMaPhong();
        int maPhim = sc.getMaPhim();
        
        logger.info("Đang xử lý suất chiếu: {}", sc);
        logger.info("Mã phòng: {}, Mã phim: {}", maPhong, maPhim);
        
        Phim phim = phimService.getPhimById(maPhim);
        if (phim == null) {
            logger.error("Không tìm thấy phim với ID: {}", maPhim);
            model.addAttribute("error", "Thông tin phim không hợp lệ");
            return "error";
        }
        
        List<Ghe> listGhe = gheService.getByMaPhong(maPhong);
        logger.info("Số ghế tìm thấy: {}", listGhe.size());

        // 4. Truyền dữ liệu đầy đủ
        model.addAttribute("selectedPhim", phim);
        model.addAttribute("selectedSuatChieu", sc);
        model.addAttribute("maPhong", maPhong);
        model.addAttribute("listGhe", listGhe);
        model.addAttribute("maKhachHang", maKhachHang);
        model.addAttribute("username", session.getAttribute("username"));

        return "list-ghe-customer";
        
    } catch (Exception e) {
        // Ghi log lỗi
        logger.error("Lỗi khi xử lý đặt ghế: {}", e.getMessage());
        logger.debug("Chi tiết lỗi:", e);
        model.addAttribute("error", "Đã xảy ra lỗi hệ thống: " + e.getMessage());
        return "error";
    }
}
}