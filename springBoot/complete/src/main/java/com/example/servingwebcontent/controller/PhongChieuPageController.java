package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PhongChieuPageController {

    @Autowired
    private PhongChieuService phongChieuService;

    /* ======== NHÂN VIÊN ======== */

    @GetMapping("/nhanvien/phongchieu")
    public String listPhongChieu(Model model) {
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "nhanvien/listphongchieu";
    }

    @GetMapping("/nhanvien/phongchieu/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongChieu", new PhongChieu());
        return "nhanvien/addphongchieu";
    }

    @PostMapping("/nhanvien/phongchieu/add")
    public String doAdd(@ModelAttribute PhongChieu phongChieu, Model model) {
        phongChieuService.createPhongChieu(phongChieu);
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "nhanvien/listphongchieu";
    }

    @GetMapping("/nhanvien/phongchieu/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        PhongChieu existing = phongChieuService.getPhongChieuById(id);
        model.addAttribute("phongChieu", existing != null ? existing : new PhongChieu());
        return "nhanvien/editphongchieu";
    }

    @PostMapping("/nhanvien/phongchieu/edit")
    public String doEdit(@ModelAttribute PhongChieu phongChieu, Model model) {
        phongChieuService.updatePhongChieu(phongChieu);
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "nhanvien/listphongchieu";
    }

    @GetMapping("/nhanvien/phongchieu/delete/{id}")
    public String deletePhongChieu(@PathVariable int id, Model model) {
        phongChieuService.deletePhongChieu(id);
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "nhanvien/listphongchieu";
    }

    /* ======== KHÁCH HÀNG ======== */

    @GetMapping("/customer/phongchieu")
    public String customerView(Model model) {
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "customer/phongchieu";
    }
}
