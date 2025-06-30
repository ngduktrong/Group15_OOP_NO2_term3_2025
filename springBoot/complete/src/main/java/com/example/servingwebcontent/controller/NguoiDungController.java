package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.NguoiDung;
import com.example.servingwebcontent.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    // Hiển thị danh sách người dùng và form trống
    @GetMapping({"", "/", "/view"})
    public String viewNguoiDung(Model model) {
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung";
    }

    // Sửa người dùng (gửi dữ liệu và hiển thị lại danh sách + form điền sẵn)
    @GetMapping("/edit/{id}")
    public String editNguoiDung(@PathVariable int id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", nguoiDung);
        return "nguoidung";
    }

    // Lưu người dùng (thêm hoặc cập nhật), và hiển thị lại danh sách
    @PostMapping("/save")
    public String saveNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, Model model) {
        if (nguoiDung.getMaNguoiDung() == 0) {
            nguoiDungService.createNguoiDung(nguoiDung);
        } else {
            nguoiDungService.updateNguoiDung(nguoiDung);
        }
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung";
    }

    // Xoá người dùng và hiển thị lại danh sách
    @PostMapping("/delete/{id}")
    public String deleteNguoiDung(@PathVariable int id, Model model) {
        nguoiDungService.deleteNguoiDung(id);
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung";
    }
} 
