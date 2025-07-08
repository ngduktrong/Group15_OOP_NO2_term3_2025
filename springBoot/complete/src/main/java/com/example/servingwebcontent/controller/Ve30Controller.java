package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.Ve30Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ve30")
public class Ve30Controller {

    @Autowired
    private Ve30Service ve30Service;

    // Kiểm tra quyền ADMIN
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "admin".equalsIgnoreCase(role);
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("username") != null;
    }

    private String noAccess(Model model) {
        model.addAttribute("message", "Bạn cần đăng nhập với quyền ADMIN để truy cập chức năng này.");
        return "login";
    }

    // Giao diện nhập mã KH
    @GetMapping("")
    public String redirectToCheckForm(Model model) {
        model.addAttribute("veSapChieu", null);
        model.addAttribute("maKhachHang", null);
        return "ve30";
    }

    // Hiển thị form nhập mã KH
    @GetMapping("/check")
    public String showCheckForm(Model model) {
        model.addAttribute("veSapChieu", null);
        model.addAttribute("maKhachHang", null);
        return "ve30";
    }

    // Xử lý form nhập mã KH
    @PostMapping("/check")
    public String processCheckForm(@RequestParam("maKhachHang") int maKhachHang, Model model) {
        List<Ve> danhSachVe = ve30Service.getVeListByKhachHang(maKhachHang);
        List<Ve> veSapChieu = ve30Service.locVeSapChieuTrong30Phut(danhSachVe);

        model.addAttribute("veSapChieu", veSapChieu);
        model.addAttribute("maKhachHang", maKhachHang);
        return "ve30";
    }

    // Admin xem toàn bộ vé sắp chiếu
    @GetMapping("/all")
    public String showAllVeSapChieu(Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        List<Ve> allVeSapChieu = ve30Service.getDanhSachVeSapChieuToanBo();
        model.addAttribute("veSapChieu", allVeSapChieu);
        model.addAttribute("maKhachHang", -1);
        return "ve30";
    }

    // Thông báo đến toàn bộ KH có vé sắp chiếu
    @PostMapping("/thongbao")
    public String thongBaoToanBoKhachHang(Model model, HttpSession session) {
        if (!isAdmin(session)) return noAccess(model);

        List<Ve> veSapChieu = ve30Service.getDanhSachVeSapChieuToanBo();
        for (Ve ve : veSapChieu) {
            System.out.println("Gửi thông báo đến khách có vé mã: " + ve.getMaVe());
            System.out.println("""
🎬 [T&M Cinema] - Thông Báo Dễ Thương 🍿
Xin chào bạn iu 💌

⏰ Bing bong~ Chiếc vé xem phim xinh xắn của bạn sắp tới giờ chiếu rồi đó nha!
🎟️ Hãy chuẩn bị thật nhanh, mang theo tâm trạng thật chill để cùng TrọMạKaa Cinema hòa mình vào thế giới điện ảnh nhé!

📍Địa điểm: Rạp T&M Cinema thân quen
🕒 Thời gian chiếu: Hãy mở lịch sử đặt vé của bạn iuuu iuu nhé !

💡 Nhớ đến sớm một chút để chọn vị trí ngồi ưng ý và thưởng thức bắp rang bơ thơm nức mũi nha~

Cảm ơn bạn đã chọn T&M Cinema — nơi cảm xúc lên ngôi 💖
Hẹn gặp bạn tại rạp nhé!
""");
        }

        model.addAttribute("veSapChieu", veSapChieu);
        model.addAttribute("maKhachHang", -1);
        model.addAttribute("message", "Đã gửi thông báo thành công đến toàn bộ khách hàng!");
        return "ve30";
    }

    // API trả danh sách vé dạng JSON
    @GetMapping("/api/ve-sapchieu")
    @ResponseBody
    public List<Ve> getAllVeSapChieuApi() {
        return ve30Service.getDanhSachVeSapChieuToanBo();
    }

    // API trả danh sách mã khách hàng có vé sắp chiếu
    @GetMapping("/api/khachhang-sapchieu")
    @ResponseBody
    public List<Integer> getMaKhachHangSapChieuApi() {
        return ve30Service.getDanhSachMaKhachHangSapChieu();
    }
}
