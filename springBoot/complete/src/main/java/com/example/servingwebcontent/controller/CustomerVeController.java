package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer/ve")
public class CustomerVeController {

    @Autowired
    private VeService veService;

    // ✅ Xác nhận thanh toán
    @PostMapping("/xac-nhan")
    public String xacNhanThanhToan(@RequestParam("maHoaDon") int maHoaDon, Model model) {
        // Lấy danh sách vé theo hóa đơn
        List<Ve> danhSachVe = veService.getVeByMaHoaDon(maHoaDon);

        if (danhSachVe == null || danhSachVe.isEmpty()) {
            model.addAttribute("message", "❌ Không tìm thấy vé nào để xác nhận.");
            return "thanh-toan-thanh-cong";
        }

        // Cập nhật tất cả vé sang trạng thái "paid"
        for (Ve ve : danhSachVe) {
            veService.markVeAsPaid(ve.getMaVe());
        }

        // ✅ Hiển thị thông báo thành công
        model.addAttribute("message", "✅ Thanh toán thành công!");
        return "thanh-toan-thanh-cong";
    }

    // ✅ GET dùng cho việc chuyển hướng sau khi xác nhận
    @GetMapping("/hoan-tat")
    public String hoanTat(Model model) {
        model.addAttribute("message", "✅ Thanh toán thành công!");
        return "thanh-toan-thanh-cong";
    }
}
