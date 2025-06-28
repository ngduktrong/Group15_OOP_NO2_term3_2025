package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.models.SuatChieu;
import com.example.servingwebcontent.service.PhimService;
import com.example.servingwebcontent.service.PhongChieuService;
import com.example.servingwebcontent.service.SuatChieuService;

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

/**
 * 3. Chọn phòng sau khi đã chọn phim
 */
@GetMapping("/customer/phongchieu/select/{maPhim}/{maPhong}")
public String showSuatChieuByPhongAndPhim(
        @PathVariable int maPhim,
        @PathVariable int maPhong,
        Model model) {
    List<SuatChieu> suatList = suatChieuService.getByMaPhongAndPhim(maPhong, maPhim);
    model.addAttribute("listSuatChieu", suatList);
    model.addAttribute("maPhong", maPhong);
    model.addAttribute("selectedPhim", phimService.getPhimById(maPhim));
    return "list-suat-chieu-customer";
}
}

