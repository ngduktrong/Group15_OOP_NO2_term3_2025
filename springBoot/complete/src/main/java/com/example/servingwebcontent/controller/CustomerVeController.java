package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import com.example.servingwebcontent.service.SuatChieuService;
import com.example.servingwebcontent.service.PhimService;
import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer/ve")
public class CustomerVeController {

    @Autowired
    private VeService veService;

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private PhimService phimService;

    /**
     * Hiển thị danh sách vé khách hàng vừa đặt (chỉ lần đặt gần nhất)
     */
    @GetMapping({"", "/"})
    public String listCustomerVes(Model model,
                                  @RequestParam(value = "veIds", required = false) List<Integer> veIds) {
        List<Ve> ves = veIds != null ? new ArrayList<>() : new ArrayList<>();
        if (veIds != null) {
            veIds.forEach(id -> ves.add(veService.getVeById(id)));
        }
        model.addAttribute("ves", ves);
        return "list-ve-customer";
    }

    /**
     * Sau khi khách submit form chọn ghế, tạo vé với giá cố định 50000 và hiển thị chúng
     */
    @PostMapping("/book")
    public String bookVe(
            @RequestParam("maSuatChieu") int maSuatChieu,
            @RequestParam("maPhong") int maPhong,
            @RequestParam("maPhim") int maPhim,
            @RequestParam("soGhe") List<String> dsGhe,
            Model model) {
        SuatChieu sc = suatChieuService.getSuatChieuById(maSuatChieu);
        Phim phim = phimService.getPhimById(maPhim);

        List<Ve> createdVes = new ArrayList<>();
        dsGhe.forEach(soGhe -> {
            Ve ve = new Ve(0, maSuatChieu, maPhong, soGhe, 0,
                    50000.0, Ve.TrangThaiVe.pending.name(), LocalDateTime.now().toString());
            ve.setNgayGioChieu(sc.getNgayGioChieu());
            veService.createVe(ve);
            createdVes.add(ve);
        });

        model.addAttribute("ves", createdVes);
        model.addAttribute("selectedPhim", phim);
        model.addAttribute("selectedSuatChieu", sc);
        model.addAttribute("maPhong", maPhong);
        return "list-ve-customer";
    }
}

