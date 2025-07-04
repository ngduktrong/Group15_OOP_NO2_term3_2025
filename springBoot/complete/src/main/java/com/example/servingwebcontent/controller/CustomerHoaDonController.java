package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.HoaDon;
import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.HoaDonService;
import com.example.servingwebcontent.service.VeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/customer/hoadon")
public class CustomerHoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private VeService veService;

    @PostMapping("/thanh-toan")
    public String thanhToanHoaDon(
            @RequestParam("maSuatChieu") int maSuatChieu,
            @RequestParam("maPhong") int maPhong,
            @RequestParam("selectedSeats") List<String> selectedSeats,
            @RequestParam("tongTien") double tongTien,
            HttpSession session,
            Model model) {

        // Kiểm tra đăng nhập
        if (session.getAttribute("maKhachHang") == null) {
            return "redirect:/login";
        }

        int maKhachHang = (int) session.getAttribute("maKhachHang");
        
        // Tạo mã hóa đơn ngẫu nhiên
        Random rand = new Random();
        int maHoaDon = 100000 + rand.nextInt(900000); // Số 6 chữ số
        
        // Tạo hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(maHoaDon);
        hoaDon.setMaKhachHang(maKhachHang);
        hoaDon.setMaNhanVien(6); // Mã nhân viên mặc định
        hoaDon.setTongTien(tongTien);
        
        // Tạo ngày lập (hiện tại)
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        hoaDon.setNgayLap(now.toString());
        
        // Lưu hóa đơn
        hoaDonService.createHoaDon(hoaDon);
        
        // Tạo các vé
        List<Ve> listVe = new ArrayList<>();
        for (String soGhe : selectedSeats) {
            // Tạo mã vé ngẫu nhiên
            int maVe = 1000 + rand.nextInt(9000); // Số 4 chữ số
            
            Ve ve = new Ve();
            ve.setMaVe(maVe);
            ve.setMaSuatChieu(maSuatChieu);
            ve.setMaPhong(maPhong);
            ve.setSoGhe(soGhe);
            ve.setMaHoaDon(maHoaDon);
            ve.setGiaVe(50000); // Giá vé cố định
            ve.setTrangThai("paid"); // Đã thanh toán
            ve.setNgayDat(now.toString()); // Ngày đặt vé là ngày lập hóa đơn
            
            // Lưu vé
            veService.createVe(ve);
            listVe.add(ve);
        }
        
        // Thêm dữ liệu vào model để hiển thị trang vé
        model.addAttribute("listVe", listVe);
        model.addAttribute("hoaDon", hoaDon);
        return "list-ve-customer";
    }
}