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

/**
 * Controller cho Admin quản lý Vé (CRUD).
 */
@Controller
@RequestMapping("/admin/ve")
public class AdminVeController {

    @Autowired
    private VeService veService;

    @GetMapping({"", "/"})
    public String listVes(Model model) {
        model.addAttribute("ves", veService.getAllVes());
        return "list-ve";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ve", new Ve(0,0,0,"",0,0.0,Ve.TrangThaiVe.available.name(),""));
        return "create-ve";
    }

    @PostMapping("/create")
    public String createVe(@ModelAttribute Ve ve, Model model) {
        veService.createVe(ve);
        model.addAttribute("ves", veService.getAllVes());
        model.addAttribute("success", "Tạo vé thành công");
        return "list-ve";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("ve", veService.getVeById(id));
        return "edit-ve";
    }

    @PostMapping("/edit")
    public String updateVe(@ModelAttribute Ve ve, Model model) {
        veService.updateVe(ve);
        model.addAttribute("ves", veService.getAllVes());
        model.addAttribute("success", "Cập nhật vé thành công");
        return "list-ve";
    }

    @GetMapping("/delete/{id}")
    public String deleteVe(@PathVariable int id, Model model) {
        veService.deleteVe(id);
        model.addAttribute("ves", veService.getAllVes());
        model.addAttribute("success", "Xóa vé thành công");
        return "list-ve";
    }
}
