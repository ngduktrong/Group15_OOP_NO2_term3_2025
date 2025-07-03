package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    /**
     * Hiển thị danh sách hóa đơn với các chức năng tìm kiếm:
     * - Theo khoảng ngày (từ ngày - đến ngày)
     * - Theo ngày lập
     * - Theo mã khách hàng
     * Mỗi điều kiện hoạt động riêng biệt (chỉ cần thỏa 1 là được)
     */
    @GetMapping
    public String listHoaDon(
            @RequestParam(value = "ngay", required = false) String ngayLap,
            @RequestParam(value = "maKH", required = false) Integer maKH,
            @RequestParam(value = "tuNgay", required = false) String tuNgay,
            @RequestParam(value = "denNgay", required = false) String denNgay,
            Model model) {

        List<HoaDon> list = new ArrayList<>();
        double tongDoanhThu = 0;

        if (tuNgay != null && !tuNgay.isEmpty() && denNgay != null && !denNgay.isEmpty()) {
            list = hoaDonService.getHoaDonByKhoangNgay(tuNgay, denNgay);
            tongDoanhThu = hoaDonService.getTongDoanhThuTheoKhoangNgay(tuNgay, denNgay);
            model.addAttribute("tuNgay", tuNgay);
            model.addAttribute("denNgay", denNgay);
            model.addAttribute("tongDoanhThuKhoang", tongDoanhThu);

        } else if (ngayLap != null && !ngayLap.isEmpty()) {
            list = hoaDonService.getHoaDonByNgayLap(ngayLap);
            tongDoanhThu = hoaDonService.getTongDoanhThuTheoNgay(ngayLap);
            model.addAttribute("searchDate", ngayLap);
            model.addAttribute("doanhThu", tongDoanhThu);

        } else if (maKH != null && maKH > 0) {
            list = hoaDonService.getHoaDonByMaKhachHang(maKH);
            model.addAttribute("maKH", maKH);

        } else {
            list = hoaDonService.getAllHoaDon();
        }

        model.addAttribute("hoaDonList", list);
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }

    /**
     * Thêm hóa đơn mới
     */
    @PostMapping("/add")
    public String addHoaDon(@ModelAttribute HoaDon hoaDon, Model model) {
        boolean success = hoaDonService.createHoaDon(hoaDon);
        String message = success ? "✅ Thêm hóa đơn thành công!" : "❌ Thêm hóa đơn thất bại!";
        model.addAttribute("message", message);
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }

    /**
     * Cập nhật hóa đơn
     */
    @PostMapping("/update")
    public String updateHoaDon(@ModelAttribute HoaDon hoaDon, Model model) {
        boolean success = hoaDonService.updateHoaDon(hoaDon);
        String message = success ? "✅ Cập nhật hóa đơn thành công!" : "❌ Cập nhật hóa đơn thất bại!";
        model.addAttribute("message", message);
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }

    /**
     * Xóa hóa đơn
     */
    @PostMapping("/delete")
    public String deleteHoaDon(@RequestParam("maHoaDon") int id, Model model) {
        boolean success = hoaDonService.deleteHoaDon(id);
        String message = success ? "✅ Xóa hóa đơn thành công!" : "❌ Xóa hóa đơn thất bại!";
        model.addAttribute("message", message);
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon";
    }

    /**
     * Hiển thị form sửa hóa đơn
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        HoaDon hd = hoaDonService.getHoaDonById(id);
        if (hd == null) {
            model.addAttribute("message", "❌ Không tìm thấy hóa đơn để sửa!");
            model.addAttribute("hoaDon", new HoaDon());
        } else {
            model.addAttribute("hoaDon", hd);
        }
        model.addAttribute("hoaDonList", hoaDonService.getAllHoaDon());
        return "hoadon";
    }
}
