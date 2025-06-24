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

    // ============ Thymeleaf Giao diện ============

    // Trang hiển thị danh sách người dùng
    @GetMapping({"", "/", "/view"})
    public String showListNguoiDung(Model model) {
        List<NguoiDung> ds = nguoiDungService.getAllNguoiDungs();
        model.addAttribute("dsNguoiDung", ds);
        return "nguoidung/list";
    }

    // Trang thêm người dùng
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung/add";
    }

    // Xử lý form thêm
    @PostMapping("/save")
    public String saveNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungService.createNguoiDung(nguoiDung);
        return "redirect:/nguoidung";
    }

    // Trang sửa người dùng
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        model.addAttribute("nguoiDung", nguoiDung);
        return "nguoidung/edit";
    }

    // Xử lý form cập nhật
    @PostMapping("/update")
    public String updateNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungService.updateNguoiDung(nguoiDung);
        return "redirect:/nguoidung";
    }

    // ✅ Xử lý xoá người dùng (từ giao diện)
    @PostMapping("/delete/{id}")
    public String deleteNguoiDungFromWeb(@PathVariable int id) {
        nguoiDungService.deleteNguoiDung(id);
        return "redirect:/nguoidung";
    }

    // ============ REST API (JSON) ============

    @ResponseBody
    @GetMapping("/api")
    public List<NguoiDung> getAllNguoiDungsAPI() {
        return nguoiDungService.getAllNguoiDungs();
    }

    @ResponseBody
    @GetMapping("/api/{id}")
    public NguoiDung getNguoiDungByIdAPI(@PathVariable int id) {
        return nguoiDungService.getNguoiDungById(id);
    }

    @ResponseBody
    @PostMapping("/api")
    public String createNguoiDungAPI(@RequestBody NguoiDung nguoiDung) {
        nguoiDungService.createNguoiDung(nguoiDung);
        return "✅ Đã thêm người dùng!";
    }

    @ResponseBody
    @PutMapping("/api/{id}")
    public String updateNguoiDungAPI(@PathVariable int id, @RequestBody NguoiDung nguoiDung) {
        nguoiDung.setMaNguoiDung(id);
        nguoiDungService.updateNguoiDung(nguoiDung);
        return "✅ Đã cập nhật người dùng!";
    }

    @ResponseBody
    @DeleteMapping("/api/{id}")
    public String deleteNguoiDungAPI(@PathVariable int id) {
        nguoiDungService.deleteNguoiDung(id);
        return "✅ Đã xoá người dùng!";
    }
}
