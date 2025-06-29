package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer/ghe")
public class CustomerGheController {

   @Autowired
private GheService gheService;

/**
 * Truy cập trực tiếp theo phòng (tuỳ chọn)
 */
@GetMapping("/{maPhong}")
public String listGheByPhong(
        @PathVariable int maPhong,
        Model model) {
    List<Ghe> list = gheService.getByMaPhong(maPhong);
    model.addAttribute("listGhe", list);
    model.addAttribute("maPhong", maPhong);
    return "list-ghe-customer";
}
@PostMapping("/chon-ghe")
public String chonGhe(@RequestParam int maKhachHang, 
                     @RequestParam int maSuatChieu,
                     @RequestParam String soGhe,
                     @RequestParam double giaVe,
                     Model model) {
    
    // Tạo vé tạm thời (chưa lưu vào DB)
    Ve ve = new Ve();
    ve.setMaSuatChieu(maSuatChieu);
    ve.setSoGhe(soGhe);
    ve.setGiaVe(giaVe);
    // Các thông tin khác...
    
    model.addAttribute("ve", ve);
    model.addAttribute("maKhachHang", maKhachHang);
    return "redirect:/customer/hoadon/thanhtoan";
}
}