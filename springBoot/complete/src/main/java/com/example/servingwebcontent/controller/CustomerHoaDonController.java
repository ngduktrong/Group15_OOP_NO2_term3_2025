package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.*;
import com.example.servingwebcontent.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/customer/hoadon")
public class CustomerHoaDonController {

    @Autowired private HoaDonService hoaDonService;
    @Autowired private VeService veService;
    @Autowired private SuatChieuService suatChieuService;
    @Autowired private PhimService phimService;

    @PostMapping("/thanh-toan")
    public String thanhToanHoaDon(
            @RequestParam("maSuatChieu") int maSuatChieu,
            @RequestParam("maPhong") int maPhong,
            @RequestParam("selectedSeats") List<String> selectedSeats,
            @RequestParam("tongTien") double tongTien,
            HttpSession session,
            Model model) {

        if (session.getAttribute("maKhachHang") == null) {
            return "redirect:/login";
        }

        int maKhachHang = (int) session.getAttribute("maKhachHang");

        // ✅ Tạo hóa đơn (chưa thanh toán)
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaKhachHang(maKhachHang);
        hoaDon.setMaNhanVien(6); // default: tại quầy
        hoaDon.setTongTien(tongTien);
        hoaDon.setNgayLap(null); // sẽ cập nhật sau khi xác nhận

        int maHoaDon = hoaDonService.createHoaDon(hoaDon);
        if (maHoaDon <= 0) {
            model.addAttribute("message", "❌ Không thể tạo hóa đơn!");
            return "error";
        }
        hoaDon.setMaHoaDon(maHoaDon); // gán lại để render sang view

        // ✅ Tạo danh sách vé (trạng thái: Chưa thanh toán)
        List<Ve> listVe = new ArrayList<>();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        for (String soGhe : selectedSeats) {
            Ve ve = new Ve();
            ve.setMaSuatChieu(maSuatChieu);
            ve.setMaPhong(maPhong);
            ve.setSoGhe(soGhe);
            ve.setMaHoaDon(maHoaDon);
            ve.setGiaVe(50000);
            ve.setTrangThai("pending");
            ve.setNgayDat(now.toString());

            boolean success = veService.createVe(ve);
            if (success) {
                listVe.add(ve);
            }
        }

        // ✅ Lấy tên phim từ suất chiếu
        String tenPhim = "Không rõ";
        SuatChieu suatChieu = suatChieuService.getSuatChieuById(maSuatChieu);
        if (suatChieu != null) {
            Phim phim = phimService.getPhimById(suatChieu.getMaPhim());
            if (phim != null) {
                tenPhim = phim.getTenPhim();
            }
        }

        // ✅ Truyền dữ liệu sang view
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listVe", listVe);
        model.addAttribute("tenPhim", tenPhim);

        // ✅ Lưu lại mã hóa đơn để sử dụng ở bước xác nhận
        session.setAttribute("maHoaDonVuaTao", maHoaDon);

        return "list-ve-customer";
    }
}
