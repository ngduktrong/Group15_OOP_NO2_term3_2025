// CustomerVeController.java
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

    @GetMapping({"", "/"})
    public String listCustomerVes(Model model,
                                  @RequestParam(value = "veIds", required = false) List<Integer> veIds) {
        List<Ve> ves = veIds != null ? veService.getVesByIds(veIds) : new ArrayList<>();
        model.addAttribute("ves", ves);
        return "list-ve-customer";
    }

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
        List<Integer> veIds = new ArrayList<>();
        dsGhe.forEach(soGhe -> {
            Ve ve = new Ve(0, maSuatChieu, maPhong, soGhe, 0,
                    50000.0, Ve.TrangThaiVe.pending.name(), LocalDateTime.now().toString());
            ve.setNgayGioChieu(sc.getNgayGioChieu());
            veService.createVe(ve);
            createdVes.add(ve);
            veIds.add(ve.getMaVe());
        });

        model.addAttribute("ves", createdVes);
        model.addAttribute("selectedPhim", phim);
        model.addAttribute("selectedSuatChieu", sc);
        model.addAttribute("maPhong", maPhong);
        model.addAttribute("veIds", veIds);
        return "list-ve-customer";
    }
    @PostMapping("/pay")
    public String payForTickets(@RequestParam("veIds") List<Integer> veIds, Model model) {
    // Lấy cùng lúc tất cả vé, DAO sẽ query IN (?,?,...)
    List<Ve> ves = veService.getVesByIds(veIds);

    if (ves == null || ves.isEmpty()) {
        model.addAttribute("message", "Không có vé hợp lệ để thanh toán.");
        return "payment-success";
    }

    // Cập nhật trạng thái tất cả vé tìm được
    ves.forEach(ve -> {
        ve.setTrangThai(Ve.TrangThaiVe.paid.name());
        veService.updateVe(ve);
    });

    model.addAttribute("message", "Thanh toán thành công!");
    return "forward:/customer/payment/success";
}

}
