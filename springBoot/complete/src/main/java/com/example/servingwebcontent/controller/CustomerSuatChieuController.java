package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.SuatChieuService;
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

@Controller
public class CustomerSuatChieuController {
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
        Model model) {
    SuatChieu sc = suatChieuService.getSuatChieuById(maSuatChieu);
    if (sc == null) {
        model.addAttribute("message", "Suất chiếu không tồn tại");
        return "list-suat-chieu-customer";
    }
    int maPhong = sc.getMaPhong();
    int maPhim = sc.getMaPhim();
    Phim phim = phimService.getPhimById(maPhim);
    List<Ghe> listGhe = gheService.getByMaPhong(maPhong);

    model.addAttribute("selectedPhim", phim);
    model.addAttribute("selectedSuatChieu", sc);
    model.addAttribute("maPhong", maPhong);
    model.addAttribute("listGhe", listGhe);
    return "list-ghe-customer";
}
}