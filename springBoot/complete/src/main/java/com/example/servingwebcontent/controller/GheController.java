package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhanvien/ghe") // quản lý ghế cho admin
public class GheController {

    @Autowired
    private GheService gheService;

    @GetMapping
    public String showGheList(Model model) {
        List<Ghe> danhSachGhe = gheService.getAll();
        model.addAttribute("dsGhe", danhSachGhe);
        return "list-ghe"; // file: templates/list-ghe.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ghe", new Ghe());
        return "add-ghe";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute Ghe ghe, Model model) {
        gheService.create(ghe);
        return "redirect:/nhanvien/ghe";
    }

    @GetMapping("/edit/{soGhe}")
    public String showEdit(@PathVariable String soGhe, Model model) {
        Ghe ghe = gheService.getBySoGhe(soGhe);
        model.addAttribute("ghe", ghe);
        return "edit-ghe";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute Ghe ghe) {
        gheService.update(ghe);
        return "redirect:/nhanvien/ghe";
    }

    @GetMapping("/delete/{soGhe}")
    public String delete(@PathVariable String soGhe) {
        gheService.delete(soGhe);
        return "redirect:/nhanvien/ghe";
    }
}
