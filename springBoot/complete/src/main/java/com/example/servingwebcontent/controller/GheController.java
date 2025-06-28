package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ghe;
import com.example.servingwebcontent.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhanvien/ghe")
public class GheController {

    @Autowired
    private GheService gheService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("dsGhe", gheService.getAll());
        return "list-ghe";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ghe", new Ghe());
        return "add-ghe";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute Ghe ghe, Model model) {
        gheService.create(ghe);
        model.addAttribute("dsGhe", gheService.getAll());
        return "list-ghe";
    }

    @GetMapping("/edit/{SoGhe}")
    public String showEdit(@PathVariable String SoGhe, Model model) {
        model.addAttribute("ghe", gheService.getBySoGhe(SoGhe));
        return "edit-ghe";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute Ghe ghe, Model model) {
        gheService.update(ghe);
        model.addAttribute("dsGhe", gheService.getAll());
        return "list-ghe";
    }

    @GetMapping("/delete/{SoGhe}")
    public String delete(@PathVariable String SoGhe, Model model) {
        gheService.delete(SoGhe);
        model.addAttribute("dsGhe", gheService.getAll());
        return "list-ghe";
    }
}
