package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.HoaDonService;
import com.example.servingwebcontent.service.VeService;
import com.example.servingwebcontent.service.SuatChieuService;
import com.example.servingwebcontent.service.PhimService;
import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer/hoadon")
public class CustomerHoaDonController {

    @Autowired
    private HoaDonService hoaDonService;
    
    @Autowired
    private VeService veService;

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private PhimService phimService;

    @GetMapping("/thanhtoan")
    public String showThanhToanForm(@RequestParam("maKhachHang") int maKhachHang, 
                                  @RequestParam("tongTien") double tongTien,
                                  @RequestParam("veIds") List<Integer> veIds,
                                  Model model) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaKhachHang(maKhachHang);
        hoaDon.setTongTien(tongTien);
        
        // Get ticket details to show in payment form
        List<Ve> ves = veService.getVesByIds(veIds);
        if (!ves.isEmpty()) {
            Ve firstVe = ves.get(0);
            SuatChieu suatChieu = suatChieuService.getSuatChieuById(firstVe.getMaSuatChieu());
            Phim phim = phimService.getPhimById(suatChieu.getMaPhim());
            
            model.addAttribute("selectedPhim", phim);
            model.addAttribute("selectedSuatChieu", suatChieu);
            model.addAttribute("maPhong", firstVe.getMaPhong());
        }
        
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("ves", ves);
        model.addAttribute("veIds", veIds);
        return "list-hoadon-customer";
    }

    @PostMapping("/thanhtoan")
    public String processThanhToan(@ModelAttribute HoaDon hoaDon,
                                 @RequestParam("veIds") List<Integer> veIds,
                                 Model model) {
        // Save the invoice
        hoaDonService.createHoaDon(hoaDon);
        
        // Update tickets with invoice ID
        List<Ve> ves = veService.getVesByIds(veIds);
        for (Ve ve : ves) {
            ve.setMaHoaDon(hoaDon.getMaHoaDon());
            ve.setTrangThai("confirmed");
            veService.updateVe(ve);
        }
        
        // Prepare data for ticket display
        if (!ves.isEmpty()) {
            Ve firstVe = ves.get(0);
            SuatChieu suatChieu = suatChieuService.getSuatChieuById(firstVe.getMaSuatChieu());
            Phim phim = phimService.getPhimById(suatChieu.getMaPhim());
            
            model.addAttribute("selectedPhim", phim);
            model.addAttribute("selectedSuatChieu", suatChieu);
            model.addAttribute("maPhong", firstVe.getMaPhong());
        }
        
        model.addAttribute("ves", ves);
        return "list-ve-customer";
    }

    @GetMapping("/ve")
    public String showVe(@RequestParam int maHoaDon, Model model) {
        List<Ve> ves = veService.getVeByMaHoaDon(maHoaDon);
        
        if (!ves.isEmpty()) {
            Ve firstVe = ves.get(0);
            SuatChieu suatChieu = suatChieuService.getSuatChieuById(firstVe.getMaSuatChieu());
            Phim phim = phimService.getPhimById(suatChieu.getMaPhim());
            
            model.addAttribute("selectedPhim", phim);
            model.addAttribute("selectedSuatChieu", suatChieu);
            model.addAttribute("maPhong", firstVe.getMaPhong());
        }
        
        model.addAttribute("ves", ves);
        return "list-ve-customer";
    }
}