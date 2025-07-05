package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.Ve30Service;
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

    /**
     * ✅ Truy cập /ve30 -> hiện giao diện nhập mã KH
     */
    @GetMapping("")
    public String redirectToCheckForm(Model model) {
        System.out.println("\n🔽 [GET] /ve30 - Truy cập giao diện kiểm tra vé sắp chiếu");
        model.addAttribute("veSapChieu", null);
        model.addAttribute("maKhachHang", null);
        return "ve30";
    }

    /**
     * ✅ Hiển thị form nhập mã khách hàng
     */
    @GetMapping("/check")
    public String showCheckForm(Model model) {
        System.out.println("\n🔽 [GET] /ve30/check - Hiển thị form nhập mã khách hàng");
        model.addAttribute("veSapChieu", null);
        model.addAttribute("maKhachHang", null);
        return "ve30";
    }

    /**
     * ✅ Xử lý form nhập mã khách hàng để lọc vé sắp chiếu
     */
    @PostMapping("/check")
    public String processCheckForm(@RequestParam("maKhachHang") int maKhachHang, Model model) {
        System.out.println("\n📤 [POST] /ve30/check - Nhập mã khách hàng: " + maKhachHang);

        List<Ve> danhSachVe = ve30Service.getVeListByKhachHang(maKhachHang);
        List<Ve> veSapChieu = ve30Service.locVeSapChieuTrong30Phut(danhSachVe);

        System.out.println("🎯 Kết quả lọc: " + veSapChieu.size() + " vé sắp chiếu");

        model.addAttribute("veSapChieu", veSapChieu);
        model.addAttribute("maKhachHang", maKhachHang);

        return "ve30";
    }

    /**
     * ✅ Admin xem toàn bộ vé sắp chiếu trong hệ thống
     */
    @GetMapping("/all")
    public String showAllVeSapChieu(Model model) {
        System.out.println("\n🧑‍💼 [GET] /ve30/all - Admin xem toàn bộ vé sắp chiếu");

        List<Ve> allVeSapChieu = ve30Service.getDanhSachVeSapChieuToanBo();
        model.addAttribute("veSapChieu", allVeSapChieu);
        model.addAttribute("maKhachHang", -1); // -1 để xác định là admin

        return "ve30";
    }

    /**
     * ✅ API trả về danh sách vé sắp chiếu dạng JSON
     */
    @GetMapping("/api/ve-sapchieu")
    @ResponseBody
    public List<Ve> getAllVeSapChieuApi() {
        System.out.println("\n📡 [API] /ve30/api/ve-sapchieu");
        return ve30Service.getDanhSachVeSapChieuToanBo();
    }

    /**
     * ✅ API trả về danh sách mã khách hàng có vé sắp chiếu
     */
    @GetMapping("/api/khachhang-sapchieu")
    @ResponseBody
    public List<Integer> getMaKhachHangSapChieuApi() {
        System.out.println("\n📡 [API] /ve30/api/khachhang-sapchieu");
        return ve30Service.getDanhSachMaKhachHangSapChieu();
    }
    @PostMapping("/thongbao")
    public String thongBaoToanBoKhachHang(Model model) {
        List<Ve> veSapChieu = ve30Service.getDanhSachVeSapChieuToanBo();

        for (Ve ve : veSapChieu) {
            System.out.println("🎯 Gửi thông báo đến khách có vé mã: " + ve.getMaVe());
            System.out.println("""
🎬 [TrọMạKaa Cinema] - Thông Báo Dễ Thương 🍿
Xin chào bạn iu 💌

⏰ Bing bong~ Chiếc vé xem phim xinh xắn của bạn sắp tới giờ chiếu rồi đó nha!
🎟️ Hãy chuẩn bị thật nhanh, mang theo tâm trạng thật chill để cùng TrọMạKaa Cinema hòa mình vào thế giới điện ảnh nhé!

📍Địa điểm: Rạp TrọMạKaa thân quen
🕒 Thời gian chiếu: Hãy mở lịch sử đặt vé của bạn iuuu iuu nhé !

💡 Nhớ đến sớm một chút để chọn vị trí ngồi ưng ý và thưởng thức bắp rang bơ thơm nức mũi nha~

Cảm ơn bạn đã chọn TrọMạKaa — nơi cảm xúc lên ngôi 💖
Hẹn gặp bạn tại rạp nhé!
""");
        }

        model.addAttribute("veSapChieu", veSapChieu);
        model.addAttribute("maKhachHang", -1);
        model.addAttribute("message", "✅ Đã Thông Báo Thành Công!");
        return "ve30";
    }

    


}
