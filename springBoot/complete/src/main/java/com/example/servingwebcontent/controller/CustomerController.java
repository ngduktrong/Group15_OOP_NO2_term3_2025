package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.models.SuatChieu;
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

/**
 * 1. Danh sách phim
 */
@GetMapping("/movies")
public String listMoviesForCustomer(Model model) {
    List<Phim> phims = phimService.getAllPhim();
    model.addAttribute("phims", phims);
    return "customer/movies";  
}

/**
 * 2. Sau khi chọn phim, hiển thị các phòng có suất chiếu của phim đó
 */
@GetMapping("/movies/select/{maPhim}")
public String listPhongByPhim(
        @PathVariable int maPhim,
        Model model) {
    // Lấy danh sách suất chiếu của phim
    List<SuatChieu> suatList = suatChieuService.getByMaPhim(maPhim);
    // Lấy tập phòng duy nhất
    Set<Integer> phongIds = suatList.stream()
        .map(SuatChieu::getMaPhong)
        .collect(Collectors.toSet());
    // Lấy chi tiết phòng
    var phongList = phongChieuService.getAllPhongChieu().stream()
        .filter(p -> phongIds.contains(p.getMaPhong()))
        .collect(Collectors.toList());

    model.addAttribute("phims", phimService.getAllPhim());
    model.addAttribute("selectedPhim", phimService.getPhimById(maPhim));
    model.addAttribute("listPhong", phongList);
    return "view-phong-customer";
}
}
